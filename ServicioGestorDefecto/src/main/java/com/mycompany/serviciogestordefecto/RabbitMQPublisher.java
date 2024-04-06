/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciogestordefecto;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 * @author jctri
 */
public class RabbitMQPublisher {
     private final String queueName;
    private final ConnectionFactory connectionFactory;

    public RabbitMQPublisher(String host, String queueName) {
        this.queueName = queueName;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
    }

    public void publish(String message) throws Exception {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Enviado '" + message + "'");
        }
    }

    // Otros métodos si son necesarios
}
