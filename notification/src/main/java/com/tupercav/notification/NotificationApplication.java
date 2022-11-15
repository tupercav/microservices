package com.tupercav.notification;

import com.tupercav.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.sql.PreparedStatement;

@SpringBootApplication (scanBasePackages = {"com.tupercav.notification", "com.tupercav.amqp"})
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer, NotificationConfig notificationConfig) {
//        return args -> {
//            rabbitMQMessageProducer.publish(
//                    new Person("Pavel", 32),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//    record Person(String name, int age) {};
}