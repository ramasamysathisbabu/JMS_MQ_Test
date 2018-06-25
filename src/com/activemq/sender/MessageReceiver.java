package com.activemq.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {

		private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
		private static String subject = "FIRST_QUEUE";
		
		public static void main(String args[]) throws JMSException{
			ConnectionFactory factory = new ActiveMQConnectionFactory(url);
			Connection connection = factory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue(subject);
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive();
			
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Received message from FIRST_QUEUE:" + textMessage.getText());
			}
			connection.close();
		}
}
