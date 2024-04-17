/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.notificationservice;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
/**
 *
 * @author jctri
 */
public class SistemaERP {

    private static final String QUEUE_NAME = "cola_Defectos";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); 

        try {

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Esperando mensajes en " + QUEUE_NAME + ". Para salir, presione CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Recibido '" + message + "'");
            };

//            CancelCallback cancelCallback = consumerTag -> {
//                System.out.println(" [x] Cancelado '" + consumerTag + "'");
//            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback,consumerTag -> {});

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al conectarse a RabbitMQ");
        }
    }
}
