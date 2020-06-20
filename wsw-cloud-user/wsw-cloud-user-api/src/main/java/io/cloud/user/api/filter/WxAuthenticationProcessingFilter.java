//package io.cloud.user.api.filter;
//
//import io.cloud.data.constant.SecurityConstant;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @program: wsw-cloud-user
// * @description:
// * @author: wsw
// * @create: 2020-06-09 16:50
// **/
//public class WxAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
//
//    public WxAuthenticationProcessingFilter(){
//        super(new AntPathRequestMatcher(SecurityConstant.WX_PATH, "POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
//        return null;
//    }
//
//}
