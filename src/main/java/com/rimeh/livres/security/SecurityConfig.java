package com.rimeh.livres.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/showCreate", "/saveProduit").hasAnyAuthority("ADMIN", "AGENT")
				.requestMatchers("/modifierLivre","/supprimerLivre").hasAuthority("ADMIN")
				.requestMatchers("/ListeProduits").hasAnyAuthority("ADMIN", "AGENT", "USER")
				.requestMatchers("/login","/webjars/**").permitAll()
				.anyRequest().authenticated())
		        .formLogin((formLogin) -> formLogin
				.loginPage("/login")
				.defaultSuccessUrl("/") )
				//.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
		        .exceptionHandling((exception)-> exception.accessDeniedPage("/accessDenied"));
		return http.build();
	}
		
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder () {
	return new BCryptPasswordEncoder();
	}
	
	/*@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
	JdbcUserDetailsManager jdbcUserDetailsManager =new JdbcUserDetailsManager(dataSource);
	jdbcUserDetailsManager.setUsersByUsernameQuery("select username , password, enabled from user where username =?");
	jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.username, r.role as authority " +
	"FROM user_role ur, user u , role r " +
	"WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND u.username = ?");
	return jdbcUserDetailsManager;
	}*/
	
	
	
	
	
	/*@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		PasswordEncoder passwordEncoder = passwordEncoder ();
		UserDetails admin = User
				.withUsername("admin")
				.password(passwordEncoder.encode("123"))
				.authorities("ADMIN")
				.build();
				UserDetails userRimeh = User
				.withUsername("rimeh")
				.password(passwordEncoder.encode("123"))
				.authorities("AGENT","USER")
				.build();
				UserDetails user1 = User
				.withUsername("user1")
				.password(passwordEncoder.encode("123"))
				.authorities("USER")
				.build();
	return new InMemoryUserDetailsManager(admin, userRimeh,user1);
	}*/
}
