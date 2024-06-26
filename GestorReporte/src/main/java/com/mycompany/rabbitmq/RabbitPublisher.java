/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rabbitmq;

import Cifrado.Desencriptar;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Elkur
 */
public class RabbitPublisher {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;

    public RabbitPublisher() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();

    }

    public String call(String methodName, String methodArgs) throws Exception {
        final String corrId = UUID.randomUUID().toString();
        replyQueueName = channel.queueDeclare().getQueue();
        String message = methodName + ":" + methodArgs;

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final CompletableFuture<String> response = new CompletableFuture<>();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                try {
                    String decryptedMessage = Desencriptar.decrypt(new String(delivery.getBody(), "UTF-8"));
                    response.complete(decryptedMessage);
                } catch (Exception e) {
                    // Aquí se maneja la excepción si el mensaje no se pudo desencriptar
                    response.completeExceptionally(e);
                }
            }
        };
        channel.basicConsume(replyQueueName, true, deliverCallback, consumerTag -> {});
        String result = response.get(); // Esto espera hasta que la promesa se complete
        return result;
    }
}
