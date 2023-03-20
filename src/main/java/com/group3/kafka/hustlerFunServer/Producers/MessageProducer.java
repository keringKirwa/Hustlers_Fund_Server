package com.group3.kafka.hustlerFunServer.Producers;

import com.group3.kafka.hustlerFunServer.Entities.Message;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private static final String TopicTwo = "messages_topic";

    @Autowired
    KafkaTemplate<String, Message> kafkaTemplate;

    public  String  sendMessage(Message message){
        kafkaTemplate.send(TopicTwo, message);
        return "Published Successfully";
    }

    @Bean
    public NewTopic createTopicTwo(){

        return new NewTopic(TopicTwo,3,(short) 1);
    }
}

