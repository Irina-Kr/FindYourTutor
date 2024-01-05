package by.tms.findyourtutor.configuration;
import org.springframework.http.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JWTTokenFilter tokenFilter;
    private static final String H2_URL_PATTERN ="/h2/*";
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)throws Exception{
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        RequestMatcher myMatcher = new AntPathRequestMatcher(H2_URL_PATTERN);
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/registration", "/user/login").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/student/**").hasRole("STUDENT")
                        .antMatchers("/tutor/**").hasRole("TUTOR")
                        .anyRequest().authenticated()
                        .and()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(csrfConfigurer ->
                        csrfConfigurer.ignoringRequestMatchers(myMatcher));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
