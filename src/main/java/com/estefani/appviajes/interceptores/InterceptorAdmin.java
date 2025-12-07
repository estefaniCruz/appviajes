package com.estefani.appviajes.interceptores;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//este inteerceptor se va  aejecutar antes de atender,
//cualquier peticion sobre admin/

@Component
public class InterceptorAdmin implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		interceptar solo las rutas hacia admin/barra (ya no todas)
		if (request.getRequestURI().contains("/admin/")) {
			System.out.println("se ejecuta el interceptor de admin");
			System.out.println("aqui vamos a comprobar si en sesion esta identificado el administrador");
			
			
			//comprobar si nos llega el parametro pass-admin 
			//e identificar al usuario como admin si es correcto
			if (request.getParameter("pass-admin") != null) {
				String passAdmin = request.getParameter("pass-admin");
				if (passAdmin.equals("123")) {
					request.getSession().setAttribute("admin", "ok");
				}
			}
			
			
			
			 //redirigir al usuario sino esta identificado como admin
			if (request.getSession().getAttribute("admin") == null  || 	
					!request.getSession().getAttribute("admin").equals("ok")) {
				response.sendRedirect("../loginAdmin");
				return false;
			}
		} // end comprobar /admin/
		return true;
	}
	
	

}
