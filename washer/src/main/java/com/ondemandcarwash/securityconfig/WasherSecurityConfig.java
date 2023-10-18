package com.ondemandcarwash.securityconfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ondemandcarwash.service.WasherService;

	@EnableWebSecurity
	public class WasherSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private WasherService washerService;
		/*
		 * @Override public void configure(WebSecurity web) throws Exception {
		 * web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
		 * "/swagger-ui.html", "/webjars/**"); }
		 */

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.userDetailsService(washerService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/washer/addwasher", "/washer/auth", "/washer/**")
			.permitAll()
			.anyRequest()
			.authenticated().and().formLogin();
		}
		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();

		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();

		}
		
		@Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedMethods(Arrays.asList("http://localhost:4200"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);

	        return source;
	    }

	}


