package ch.heigvd.broccoli.security;

import ch.heigvd.broccoli.application.Application;
import ch.heigvd.broccoli.application.ApplicationRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationRepository repository;

    public WebSecurityConfig(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            try{
                Application app = repository.findByApiKey(UUID.fromString(principal));
                if (app == null)
                {
                    throw new BadCredentialsException("The API key was not found or not the expected value.");
                }
            } catch (IllegalArgumentException e) {
                throw new BadCredentialsException("The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilter(filter)
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/applications").permitAll()
                        .anyRequest().authenticated();
    }

}