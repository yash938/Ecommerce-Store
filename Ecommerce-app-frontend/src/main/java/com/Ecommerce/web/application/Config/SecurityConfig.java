        package com.Ecommerce.web.application.Config;

        import com.Ecommerce.web.application.Security.JwtFilter;
        import jakarta.servlet.http.HttpServletRequest;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.HttpMethod;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.web.AuthenticationEntryPoint;
        import org.springframework.security.web.SecurityFilterChain;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
        import org.springframework.web.cors.CorsConfiguration;
        import org.springframework.web.cors.CorsConfigurationSource;

        import java.util.List;

        @Configuration
        public class SecurityConfig {

            @Autowired
            private AuthenticationEntryPoint entryPoint;

            @Autowired
            private JwtFilter filter;
            @Bean
            public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

                security.csrf(csrf->csrf.disable());
                security.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedMethods(List.of("*"));
                        configuration.setAllowedHeaders(List.of("*"));
                        configuration.setMaxAge(4000L);
                        return configuration;
                    }
                }))
                        .authorizeHttpRequests(auth ->auth
                                        .requestMatchers(HttpMethod.GET,"/yash").authenticated()
                                        .anyRequest().permitAll()
                                );

                security.exceptionHandling(ex->ex.authenticationEntryPoint(entryPoint))
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

                return security.build();

            }


            @Bean
            public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
            }

        }
