package com.estefani.appviajes.controllers.loginAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estefani.appviajes.controllers.admin.ViajesController;

import jakarta.servlet.http.HttpServletRequest;

//controlador que se encarga de identificar a un admin
@Controller
public class LoginAdminController {
	
	@Autowired
	private ViajesController viajesController;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("loginAdmin")
	public String loginAdmin() {
		return "admin/loginAdmin";
	}
	
	@RequestMapping("logoutAdmin")
	public String logoutAdmin(HttpServletRequest request) {
		
		
		String idiomaActual = 
				messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		request.getSession().invalidate(); // aqui digo que la sesion se elimine completamente
		System.out.println("idiomaActual: " + idiomaActual);
		return "tienda_" + idiomaActual;
		
	}
	

	
	
	
	
	

}
