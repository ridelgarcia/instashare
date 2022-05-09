package com.ce.instashare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.h2.server.web.WebServlet;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;	

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean(name="BCryptPasswordEncoder")
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/h2-ui/*");
        return registrationBean;
    }
   
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {		
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/").permitAll().and()
				.authorizeRequests().antMatchers("/user/signin").permitAll().and()
				.authorizeRequests().antMatchers("/user/signup").permitAll().and()
				.authorizeRequests().antMatchers("/user/checkemail").permitAll().and()
				.authorizeRequests().antMatchers("/role/getdefaultrole").permitAll().and()
				.authorizeRequests().antMatchers("/h2-ui/**").permitAll()			
				.anyRequest().authenticated().and().
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);		
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);	
		httpSecurity.headers().frameOptions().disable();
	}

	
	
}
