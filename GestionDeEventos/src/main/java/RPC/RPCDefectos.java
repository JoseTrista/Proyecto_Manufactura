/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPC;

import Interfaces.IDefecto;
import com.google.gson.Gson;
import com.mycompany.dao_manufactura.DaoDefecto;
import com.rabbitmq.client.*;
import java.util.Arrays;
import java.util.UUID;
import java.util.List;

public class RPCDefectos {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        channel.queuePurge(RPC_QUEUE_NAME);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            System.out.println("Llego algo");
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            String response = "";

            String message = new String(delivery.getBody(), "UTF-8");
            String[] parts = message.split(":");
            String methodName = parts[0];
            String methodArgs = parts[1];

            //IDefecto defectosDAO = new DaoDefecto("localhost", "3306", "manufactura", "root", "Movagro123.,");
            System.out.println(methodName);
            if (methodName.equalsIgnoreCase("prueba")) {
                response = "sillego";
            }else if(methodName.equalsIgnoreCase("consultarNumPiezasPorDefecto")){
                //AQUI VA LA CONSULTA
                response = "600";
            }else if(methodName.equalsIgnoreCase("consultarCostosDefectos")){
                //AQUI VA LA CONSULTA
                response = "38512.32";
            }else if(methodName.equalsIgnoreCase("consultarDetallePiezas")){
                //AQUI VA LA CONSULTA
                Gson gson = new Gson();
                
                response = gson.toJson(Arrays.asList("Lorem ipsum dolor sit amet,"
                        + " consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                        + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                        + "consequat. Duis aute irure dolor in reprehenderit in voluptate velit "
                        + "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat "
                        + "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim "
                        + "id est laborum.",
                        "Lorem ipsum dolor sit amet,"
                        + " consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                        + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                        + "consequat. Duis aute irure dolor in reprehenderit in voluptate velit "
                        + "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat "
                        + "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim "
                        + "id est laborum.",
                        "Lorem ipsum dolor sit amet,"
                        + " consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                        + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo "
                        + "consequat. Duis aute irure dolor in reprehenderit in voluptate velit "
                        + "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat "
                        + "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim "
                        + "id est laborum."));
            }
            System.out.println("Paquete"+delivery.getProperties().getReplyTo()+" respuesta "+response);
            channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        };

        channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {
        }));
        System.out.println(" [x] Awaiting RPC requests");

    }
}
