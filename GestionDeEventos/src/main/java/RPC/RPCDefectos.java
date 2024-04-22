/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPC;

import Interfaces.IDefecto;
import com.google.gson.Gson;
import com.mycompany.dao_manufactura.DaoDefecto;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RPCDefectos {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws Exception{
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

                String response = null;
                Gson gson = new Gson();
                IDefecto defectosDAO = new DaoDefecto("localhost", "3306", "manufactura", "root", "Movagro123.,");

                String message = new String(delivery.getBody(), "UTF-8");
                String[] parts = message.split(":");
                String methodName = parts[0];
                String defecto = parts[1];


                System.out.println(methodName);
                if (methodName.equalsIgnoreCase("prueba")) {
                    response = "sillego";
                } else if (methodName.equalsIgnoreCase("consultarNumPiezasPorDefecto")) {
                    int numPiezas = defectosDAO.consultarNumPiezasPorDefecto(defecto);
                    response = String.valueOf(numPiezas);
                } else if (methodName.equalsIgnoreCase("consultarCostosDefectos")) {
                    double costosDefectos = defectosDAO.consultarCostosDefectos(defecto);
                    response = String.valueOf(costosDefectos);
                } else if (methodName.equalsIgnoreCase("consultarDetallePiezas")) {
                    List<String> detallePiezas = defectosDAO.consultarDetallePiezas(defecto);
                    response = gson.toJson(detallePiezas);
                }
//                try {
//                    switch (methodName.toLowerCase()) {
//                        case "consultarnumpiezaspordefecto":
//                            int numPiezas = defectosDAO.consultarNumPiezasPorDefecto(defecto);
//                            response = String.valueOf(numPiezas);
//                            break;
//                        case "consultarcostosdefectos":
//                            double costosDefectos = defectosDAO.consultarCostosDefectos(defecto);
//                            response = String.valueOf(costosDefectos);
//                            break;
//                        case "consultardetallepiezas":
//                            String detallePiezas = defectosDAO.consultarDetallePiezas(defecto);
//                            response = gson.toJson(detallePiezas);
//                            break;
//                        default:
//                            response = "MÃ©todo no reconocido: " + methodName;
//                    }
//                } catch (Exception e) {
//                    response = "Error procesando la solicitud: " + e.getMessage();
//                }
                System.out.println("Paquete"+delivery.getProperties().getReplyTo()+" respuesta "+response);
                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {}));
            System.out.println(" [x] Awaiting RPC requests");

//            // Keep the program running
//            synchronized (RPCDefectos.class) {
//                RPCDefectos.class.wait();
//            }
    }
}