package com.OauthProject.OAuth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@PropertySource("classpath:application.properties")
//(AuthorizationServerConfig)main responsible is to generate a token to the client
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private Environment env;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    static final String CLIENT_ID = "oauth-client";
    //	private String CLIENT_ID = env.getProperty("security.oauth2.client.client-id");
    static final String CLIENT_SECRET = "oauth-secret";
    static final String GRANT_TYPE = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 40;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 80;







//this method is use to configure the security settings of the  authorization server,
//    such as which end points are public
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception {
//        securityConfigurer
//        .tokenKeyAccess("permitAll")
//        .checkTokenAccess("isAuthenticated()");
//    }


//    This method is use to define the client details service which is responsible
//    for managing information about register OAuth clients
    @Override
    public void configure(ClientDetailsServiceConfigurer clientsConfigure) throws Exception {
        clientsConfigure
//                .inMemory()
////                client details tika OAuth2Constant class eke store karala thiyenawa
//                .withClient(OAuth2Constant.USER_CLIENT_ID)
//                .secret(OAuth2Constant.CLIENT_SECRET)
//                .scopes(OAuth2Constant.SCOPE_READ, OAuth2Constant.SCOPE_WRITE)
//                .authorizedGrantTypes(OAuth2Constant.GRANT_TYPE_PASSWORD, OAuth2Constant.REFRESH_TOKEN)
//                .accessTokenValiditySeconds(OAuth2Constant.ACCESS_TOKEN_VALIDITY_SECONDS)
//                .refreshTokenValiditySeconds(OAuth2Constant.REFRESH_TOKEN_VALIDITY_SECONDS);
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);

    }
//this method allows you to configure various endpoints provided by the authorization server,
//    such as the token endpoint , authorization endpoint , user_info endpoint
//    http://localhost:8080/oauth/token me url eka jenarate venne methanin
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore)
                    .authenticationManager(authenticationManager);
    }



}
