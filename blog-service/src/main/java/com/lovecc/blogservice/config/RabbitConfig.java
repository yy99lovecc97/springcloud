package com.lovecc.blogservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public  final static String queueName = "spring-boot";

    /**
     * 获取队列queuename
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
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
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
}
