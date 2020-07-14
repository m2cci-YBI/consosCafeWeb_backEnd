package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.auth.filters.JwtAuthenticationFilter;
import com.example.demo.auth.filters.JwtAuthorizationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;
     /*Cette methode permet de customizer le fonctionnment de l'objet AuthenticathionManager */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
/*Cette Methode gere les authorizations : les droits d'un utilisateur de recuperer 
 * une resource depuis le back end*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/programmeurs").hasAuthority("RESP")
		.antMatchers(HttpMethod.DELETE,"/programmeurs").hasAuthority("RESP")
		.antMatchers(HttpMethod.POST,"/programmeurs","/consommations").hasAuthority("RESP")
	    .antMatchers(HttpMethod.GET,"/consommations/*","/monPdf/*").hasAnyAuthority("RESP","PROF");
		http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new JwtAuthorizationFilter(), JwtAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
