package com.haselt.debtcollecting.config;

import com.haselt.appointapp.web.config.property.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;
    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;

    @Bean
    @Transactional
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenServices(tokenServices())
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(false)
                .tokenStore(tokenStore());
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        CustomTokenService tokenServices = new CustomTokenService();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setAuthenticationManager(createPreAuthProvider());
        tokenServices.setTokenEnhancer(accessTokenConverter());
        return tokenServices;
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.passwordEncoder(passwordEncoder).tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(securityProperties.getJwt().getKeyStore(),
                        securityProperties.getJwt().getKeyStorePassword().toCharArray());
        converter.setKeyPair(keyStoreKeyFactory
                .getKeyPair(securityProperties.getJwt().getKeyPairAlias()));

        String publicKey;
        try {
            publicKey = IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(), Charset.defaultCharset());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }

    private ProviderManager createPreAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
        return new ProviderManager(Collections.singletonList(provider));
    }

    @Slf4j
    public static class CustomTokenService extends DefaultTokenServices {
        @Override
        public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {

            try {
                return super.createAccessToken(authentication);
            }
            catch(DuplicateKeyException ex) {
                log.warn(ex.getMessage());
                return super.getAccessToken(authentication);
            }
            catch(Exception ex) {
                log.error(ex.getMessage(), ex);
                return null;
            }
        }
    }
}
