package hac.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SecurityConfig - configure the security, restrict pages according to roles.
 */
@Configuration
public class SecurityConfig {

    /**
     * passwordEncoder - encrypt the password
     * @return - the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * userDetailsService - create the users
     * @param bCryptPasswordEncoder - the password encoder
     * @return - the user details service
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles("ADMIN")
                .build());
        manager.createUser(User.withUsername("idan")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("ranel")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles("USER")
                .build());
        return manager;
    }

    /**
     * filterChain - configure the security (restrict pages according to roles)
     * @param http - the http security
     * @return - the security filter chain
     * @throws Exception - exception if the configuration failed
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(withDefaults())

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login/**", "/css/**", "/images/**", "/403", "/errorPage").permitAll()
                        .requestMatchers("/game/**", "/highscores/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                )
                .formLogin((form) -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/game", true)
                                .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .accessDeniedPage("/403")
                )

        ;
        return http.build();
    }
}



