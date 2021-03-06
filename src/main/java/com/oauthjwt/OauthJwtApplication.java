package com.oauthjwt;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ForwardedHeaderFilter;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.oauthjwt.dao.UserDAO;
import com.oauthjwt.model.Signup;

import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@RestController
public class OauthJwtApplication extends WebMvcConfigurerAdapter{
	
	
	public static void main(String[] args) {
		SpringApplication.run(OauthJwtApplication.class, args);
	}
	
	@Bean
    FilterRegistrationBean forwardedHeaderFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new ForwardedHeaderFilter());
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegBean;
    }
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }
	
	 @Bean
	    public FilterRegistrationBean corsFilters() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        return bean;
	    }
	
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@RequestMapping("/user/me")
	public Principal user(Principal user) {
		return user;
	}
	
	@Autowired
	UserDAO userDao;
	
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@PreAuthorize("#oauth2.hasScope('openid') and hasAuthority('ADMIN')")
	@PostMapping("/createUser")
	public String createVendor(@RequestBody Map<String, String> user) {
		Signup signup = new Signup();
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		signup.setPassword(bcrypt.encode(user.get("password")));
		signup.setUsername(user.get("username"));
		signup.setAuthorities(user.get("authorities"));
		return userDao.createUser(signup);
	}
}
