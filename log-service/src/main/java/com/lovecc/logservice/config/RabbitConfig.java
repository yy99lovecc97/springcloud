package com.lovecc.logservice.config;

import com.lovecc.logservice.rabbit.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String queueName = "spring-boot";
    @Bean
    Queue queue(){
        return new Queue(queueName,false);
    }

    /**
     * 设置交换机类型
     FanoutExchange: 广播，将消息分发到所有的绑定队列
     HeadersExchange ：通过添加键值对key-value匹配
     DirectExchange:按照路由Routingkey分发指定队列
     TopicExchange:topic主题模式，多关键字匹配
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    /**
     * 消息队列queuename绑定到交换机上, 路由规则是queuename
     * 一个交换机可以绑定多个消息队列，消息通过一个交换机，可以分发到不同的队列当中去。
     * @return
     */
    @Bean
    Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
    }

    /**
     * 设置消费队列监听
     *
     * 初始化connectionFactory
     * ConnectionFactory factory = new ConnectionFactory();
     * factory.setHost(ip);
     * factory.setPort(5672);
     * factory.setUsername("guest");
     * factory.setPassword("guest");
     * Connection connection = factory.newConnection();
     *
     * @param connectionFactory 连接工厂
     * @param messageListenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    /**
     * 消息监听适配器（adapter），通过反射将消息处理委托给目标监听器的处理方法，并进行灵活的消息类型转换。
     * 允许监听器方法对消息内容类型进行操作，完全独立于Rabbit API。
     * MessageListenerAdapter
     * 1.可以把一个没有实现MessageListener和ChannelAwareMessageListener接口的类适配成一个可以处理消息的处理器
     * 2.默认的方法名称为：handleMessage，可以通过setDefaultListenerMethod设置新的消息处理方法
     * 3.MessageListenerAdapter支持不同的队列交给不同的方法去执行。使用setQueueOrTagToMethodName方法设置，当根据queue名称没有找到匹配的方法的时候，就会交给默认的方法去处理。
     * @return
     */
    @Bean
    MessageListenerAdapter messageListenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }
}
