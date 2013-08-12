package com.manuv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.manuv.constant.UserConstants;
import com.manuv.persistence.entities.User;

/**
 * This interceptor adds the user to the model of every requests managed
 * @author emanuele.ivaldi@gmail.com
 *
 */
public class DefaultInterceptor extends HandlerInterceptorAdapter {

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.getPrincipal() != null) {

			if (User.class.isAssignableFrom(auth.getPrincipal().getClass())) {

				User user = (User) auth.getPrincipal();

				modelAndView.getModelMap().addAttribute(UserConstants.USER_MODEL_ATTR_NAME, user);

			}
		}

	}
}
