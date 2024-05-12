/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.notificationservice;

import Cifrado.DesencriptarERP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jctri
 */
public class SistemaERP {

//    private static final String QUEUE_NAME = "cola_Defectos";
    private static final String EXCHANGE_NAME = "defectos_exchange";
    private static final String ROUTING_KEY = "defecto";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);

        System.out.println(" [*] Esperando mensajes en " + queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                String encryptedMessage = new String(delivery.getBody(), "UTF-8");
                String message = DesencriptarERP.decrypt(encryptedMessage);
                System.out.println(" [x] Recibido: '" + ROUTING_KEY + "':'" + message + "'");
            } catch (Exception ex) {
                Logger.getLogger(SistemaERP.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
