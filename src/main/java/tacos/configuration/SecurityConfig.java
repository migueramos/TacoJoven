package tacos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    protected InMemoryUserDetailsManager userDetailsService() {
        UserDetails buzz = User.builder()
                .username("buzz")
                .password("0")
                .authorities("ROLE_USER")
                .build();
        UserDetails woody = User.builder()
                .username("woody")
                .password("0")
                .authorities("ROLE_USER")
                .build();
        return new InMemoryUserDetailsManager(buzz, woody);
    }
}
//92