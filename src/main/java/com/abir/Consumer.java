package com.abir;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hello-world",false, false, false, null);
		
		channel.basicConsume("hello-world", true, (DeliverCallback) (consumerTag, message) -> {
			String m = new String(message.getBody(), "UTF-8");	
			System.out.println("I just received a message = "+m);
			
		}, (CancelCallback) consumerTag -> {
			// TODO Auto-generated method stub
			
		});
		
	}

}
