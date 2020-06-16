package fsr.iao.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*@Override
protected void configure(AuthenticationManagerBuilder auth)
		throws Exception {
	// TODO Auto-generated method stub
	auth.inMemoryAuthentication().withUser("samir").password(passwordEncoder().encode("samir")).roles("OUDER");
	auth.inMemoryAuthentication().withUser("ouder").password(passwordEncoder().encode("ouder")).roles("ADMIN");

	
	
}*/
@Autowired
private void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception {
	
	auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal ,concat('{noop}',password) as credentials,true "
			+ " from users where username=?")
	.authoritiesByUsernameQuery("select username as principal,role as role from users u,users_roles ur,role r where u.id=ur.user_id"
			+ " and ur.roles_id=r.id and  username=?").rolePrefix("ROLE_");
	
	

}

@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/produits");
		
		http.authorizeRequests().antMatchers("/**Produit/**","/**User/**").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/notAuthorized").and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		http.authorizeRequests().antMatchers("/css/**","/fonts/**","/images/**","/js/**","/vendor/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}
/*@Bean
public BCryptPasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
}*/	
}
