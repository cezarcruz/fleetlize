package com.fleetlize.fleetintegrator.config;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConfig {

  @Value("${fleetlize.broker-url}")
  private String broketUrl;

  @Bean
  public ConnectionFactory receiverActiveMQConnectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory =
        new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(broketUrl);

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
