package com.sparta.websocketpractice.config;

// import 생략...

import com.sparta.redistest.RedisSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * redis pub/sub 메시지를 처리하는 listener 설정
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListener(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter,
        ChannelTopic channelTopic) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 방마다 topic을 Repository에 저장할 필요 없이 channelTopic을 DI로 받아 사용가능하게 만듦
        container.addMessageListener(listenerAdapter, channelTopic);
        return container;
    }

    /**
     * 어플리케이션에서 사용할 redisTemplate 설정
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return redisTemplate;
    }

    /**
     * 실제 메시지를 처리하는 subscriber 설정 추가
     * 모든 메세지를 내가 생성한 RedisSubscriber 클래스가 처리하도록 설정함
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "sendMessage");
    }

    /**
     * 단일 Topic 사용을 위한 Bean 설정
     * 사실 중요한 부분인지 모르겠음
     * 계속해서 생성할 필요가 없어 보여 빈으로 등록함
     */
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("chatroom");
    }
}