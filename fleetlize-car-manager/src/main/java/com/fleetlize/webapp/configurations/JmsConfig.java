package com.fleetlize.webapp.configurations;

import java.util.Collections;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfig {

  @Bean
  public ConnectionFactory receiverActiveMQConnectionFactory() {
    final ActiveMQConnectionFactory activeMQConnectionFactory =
        new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setTrustAllPackages(false);
    activeMQConnectionFactory.setTrustedPackages(Collections.singletonList("com.fleetlize.*"));
    activeMQConnectionFactory.setBrokerURL("vm://embedded-broker?broker.persistent=false");
    return activeMQConnectionFactory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(receiverActiveMQConnectionFactory());
    factory.setPubSubDomain(true);

    return factory;
  }


}
