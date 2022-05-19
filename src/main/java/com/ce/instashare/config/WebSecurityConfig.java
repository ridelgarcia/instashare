package com.ce.instashare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;

import com.ce.instashare.notification.NotificationWebSocketHandler;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import org.h2.server.web.WebServlet;



@Configuration
@EnableAsync
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig  {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private SecurityContextRepository securityContextRepository;

	@Bean(name="BCryptPasswordEncoder")
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder(10);
	}
	@Bean
	public HandlerMapping handlerMapping() {
	    Map<String, WebSocketHandler> map = new HashMap<>();
	    map.put("/notification", new NotificationWebSocketHandler());

	    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
	    mapping.setUrlMap(map);
	    return mapping;
	}
	@Bean
	public WebSocketService webSocketService() {
		TomcatRequestUpgradeStrategy strategy = new TomcatRequestUpgradeStrategy();
		strategy.setMaxSessionIdleTimeout(0L);
		strategy.setMaxBinaryMessageBufferSize(1024*1024);
		strategy.setMaxTextMessageBufferSize(1024*1024);
		return new HandshakeWebSocketService(strategy);
	}
	
	
    /*@Bean
    ServletRegistrationBean<WebServlet> h2servletRegistration(){
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>( new WebServlet());
        registrationBean.addUrlMappings("/h2-ui/*");
        return registrationBean;
    }*/	
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> 
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) -> 
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .csrf().disable()                
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(this.authenticationManager)
                .securityContextRepository(this.securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/").permitAll()
                .pathMatchers("/notification").permitAll()
                .pathMatchers("/user/signin").permitAll()
                .pathMatchers("/user/signup").permitAll()
                .pathMatchers("/user/checkemail").permitAll()
                .pathMatchers("/role/getdefaultrole").permitAll()
                .pathMatchers("/h2-ui/**").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
	
	
}
