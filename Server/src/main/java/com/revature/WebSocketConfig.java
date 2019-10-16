package com.revature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * WebSocketConfig
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
@ComponentScan("com.revature")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer implements WebSocketConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        RequestUpgradeStrategy rus = new TomcatRequestUpgradeStrategy();
        registry.addEndpoint("/socket")
        .setHandshakeHandler(new DefaultHandshakeHandler(rus))
        .setAllowedOrigins("*")
        .withSockJS();

        registry.addEndpoint("/socket")
        .setHandshakeHandler(new DefaultHandshakeHandler(rus))
        .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app"); 
    }

    @Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/myHandler").withSockJS();
	}

	@Bean
	public WebSocketHandler myHandler() {
		return new MyHandler();
	}

    // @Bean
	// public ServletServerContainerFactoryBean createWebSocketContainer() {
	// 	ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
	// 	container.setMaxTextMessageBufferSize(8192);
	// 	container.setMaxBinaryMessageBufferSize(8192);
	// 	return container;
	// }
    
}