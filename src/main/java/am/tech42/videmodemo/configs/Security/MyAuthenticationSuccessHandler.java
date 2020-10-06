package am.tech42.videmodemo.configs.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class MyAuthentificationSucssesHendler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException
    {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN"))
        {
            request.getSession(false).setMaxInactiveInterval(60*10);
        }
        else
        {
            request.getSession(false).setMaxInactiveInterval(60*30);
        }
        //Your login success url goes here, currently login success url="/"
        response.sendRedirect("/");
    }
}
