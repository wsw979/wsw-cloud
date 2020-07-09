package io.cloud.job.admin.controller.interceptor;

import io.cloud.job.admin.controller.annotation.PermissionLimit;
import io.cloud.job.admin.core.model.XxlJobUser;
import io.cloud.job.admin.core.util.I18nUtil;
import io.cloud.job.admin.service.LoginService;
import io.cloud.job.core.util.XxlJobRemotingUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截
 *
 * @author xuxueli 2015-12-12 18:09:04
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	private static final String API_ADD = "/jobinfo/addApi";

	@Value("${xxl.job.accessToken:#{null}}")
	private String accessToken;

	@Resource
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}

		String servletPath = request.getServletPath();
		if(servletPath.equals(API_ADD)){
			String header = request.getHeader(XxlJobRemotingUtil.XXL_JOB_ACCESS_TOKEN);
			if(header.equals(accessToken)){
				return super.preHandle(request, response, handler);
			}
		}

		// if need login
		boolean needLogin = true;
		boolean needAdminuser = false;
		HandlerMethod method = (HandlerMethod)handler;
		PermissionLimit permission = method.getMethodAnnotation(PermissionLimit.class);
		if (permission!=null) {
			needLogin = permission.limit();
			needAdminuser = permission.adminuser();
		}

		if (needLogin) {
			XxlJobUser loginUser = loginService.ifLogin(request, response);
			if (loginUser == null) {
				response.sendRedirect(request.getContextPath() + "/toLogin");
				//request.getRequestDispatcher("/toLogin").forward(request, response);
				return false;
			}
			if (needAdminuser && loginUser.getRole()!=1) {
				throw new RuntimeException(I18nUtil.getString("system_permission_limit"));
			}
			request.setAttribute(LoginService.LOGIN_IDENTITY_KEY, loginUser);
		}

		return super.preHandle(request, response, handler);
	}

}
