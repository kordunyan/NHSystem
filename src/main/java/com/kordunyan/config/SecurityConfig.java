package com.kordunyan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT email as principal, password as credentials, true FROM users WHERE email = ?")
				.authoritiesByUsernameQuery("SELECT  user_email as principal, role_name as role FROM user_roles WHERE user_email = ?")
				.passwordEncoder(passwordEnoder())
				.rolePrefix("ROLE_");
	}

	@Bean
	public PasswordEncoder passwordEnoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/register", "/", "/about", "/login", "/css/**", "/webjars/**").permitAll()
				.antMatchers("/profile").hasAnyRole("USER", "ADMIN")
				.antMatchers("/users", "/addtask").hasRole("ADMIN")
				.and()
					.formLogin().loginPage("/login").permitAll()
					.defaultSuccessUrl("/profile")
				.and()
				.logout().logoutSuccessUrl("/login");

	}
}
