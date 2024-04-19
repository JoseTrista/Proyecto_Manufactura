/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RabbitMQ;

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
    private static final String ROUTING_KEY = "defecto";

    public RabbitMQPublisher(String host, String queueName) {
        this.queueName = queueName;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
    }

    public void publish(String message) throws Exception {
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
            String exchangeName = "defectos_exchange";
            channel.exchangeDeclare(exchangeName, "fanout");

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, ROUTING_KEY);
            channel.basicPublish(exchangeName, ROUTING_KEY, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Enviado '" + message + "' a exchange");
        }
    }

    // Otros métodos si son necesarios
}
