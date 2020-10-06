package am.tech42.videmodemo.security;

import am.tech42.videmodemo.model.User.User;
import am.tech42.videmodemo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthentificationProvider implements AuthenticationProvider {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mail = authentication.getName();
        User user = userRepo.findByMail(mail);
        if(user == null) {
            throw new UsernameNotFoundException("Bad credentials");
        }
        String password = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority grantedAuthority=new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        };
        authorities.add(grantedAuthority);
        return new UsernamePasswordAuthenticationToken(user, null ,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
