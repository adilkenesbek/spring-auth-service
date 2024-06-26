package kz.pet.eon.security;
import kz.pet.eon.domain.AppUserEntity;
import kz.pet.eon.handler.domain.ErrorCode;
import kz.pet.eon.repository.AppUserRepository;
import kz.pet.eon.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();
        AppUserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException(ErrorCode.ERROR_BAD_CREDENTIALS));

        try {
            if (validatePassword(rawPassword, user.getPassword(), Base64.getDecoder().decode(user.getSalt()))) {
                return new UsernamePasswordAuthenticationToken(username, rawPassword, user.getAuthorities());
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new BadCredentialsException("Invalid password", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean validatePassword(String rawPassword, String storedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String hashedPassword = Base64.getEncoder().encodeToString(hash);
        return hashedPassword.equals(storedPassword);
    }
}
