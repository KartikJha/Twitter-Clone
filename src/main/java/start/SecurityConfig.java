package start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import start.services.AppUserDetailService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(appUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers("/","/js/**","/templates/main.html", "/templates/register.html", "/templates/login.html", "/out", "/register", "/favicon.ico", "/send_message", "/get_messages_by_username")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .csrf().disable()
                .logout()
                    .logoutSuccessUrl("/out");

    }

}
