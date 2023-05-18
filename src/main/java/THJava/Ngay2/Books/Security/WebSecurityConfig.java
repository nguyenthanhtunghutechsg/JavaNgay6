package THJava.Ngay2.Books.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import THJava.Ngay2.Books.Services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	protected WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
				.antMatchers("/");
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("filter");
		http.authorizeHttpRequests(requests -> requests
				.antMatchers("/books/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
				.antMatchers("/books/new")
				.hasAnyAuthority("ADMIN", "CREATOR").antMatchers("/books/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
				.antMatchers("/books/delete/**").hasAuthority("ADMIN").anyRequest().authenticated())
				.formLogin(login -> login.permitAll()).logout(logout -> logout.permitAll())
				.exceptionHandling(handling -> handling.accessDeniedPage("/403"));
		return http.build();
	}

}
