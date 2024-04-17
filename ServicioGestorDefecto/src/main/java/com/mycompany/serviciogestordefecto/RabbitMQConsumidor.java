
package com.mycompany.serviciogestordefecto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.dominiprueba.Defecto;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author jctri
 */
public class RabbitMQConsumidor {
    private final String queueName;
    private final ConnectionFactory connectionFactory;
    private List<String> mensajesRecibidos = Collections.synchronizedList(new ArrayList<>());

    public RabbitMQConsumidor(String host, String queueName) {
        this.queueName = queueName;
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost(host);
    }

    public List<String> getMensajesRecibidos() {
        return mensajesRecibidos;
    }
  
    public void consume() throws Exception {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);
            System.out.println(" [*] Esperando mensajes en " + queueName + ". Para salir, presione CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Recibido '" + message + "'");
                mensajesRecibidos.add(message);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al conectarse a RabbitMQ");
        }
    }
}
