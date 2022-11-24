package gt.com.tigo.workeragreementrenewmanagement.commons.rabbitmq;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;


public class MessageSendReceiveRabbit {
	
	Boolean durable = true;
	Boolean exclusive = false;
	Boolean autoDelete = false;
	Channel channel = null;
	String host = "localhost";
	Connection connection = null;
	boolean autoAck = true;
	String queueName = "";
	
	private final Logger LOGGER = Logger.getLogger(getClass());
	
	public MessageSendReceiveRabbit(String virtualhost, String host, String port, String username, String password, String queueName) {
		if(connection == null) {
			createConnection(virtualhost, host, port, username, password, queueName);
			this.host = host;
			this.queueName = queueName;
		}
			
	}
	
	private void createConnection(String virtualhost, String host, String portString, String username, String password, String queueName) {
		
		if (host != null && queueName != null) {
			this.host = host;
			
			int port = (portString != null)?Integer.parseInt(portString):5672;

			LOGGER.debug("Creating connection to RabbitMQ - host: " + host + " queueName: " + queueName);

			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			factory.setUsername(username);
			factory.setPassword(password); 
			factory.setPort(port);
			factory.setVirtualHost(virtualhost);
			
			try {

				Connection connection = factory.newConnection();
				channel = connection.createChannel();
				
				channel.queueDeclare(queueName, durable, exclusive, autoDelete, null);
			} catch(Exception e) {
				LOGGER.error("Rabbit Conection Error", e);
			}
		} else {
			LOGGER.error("Configuration Error - host or queueName is not defined, please check the properties - host: "+host+" queueName: "+queueName);
			
		}
		
	}
	
	
	public String readMessage(String queueName) {
		
		String message = "";
		
		try {
		
			GetResponse response = channel.basicGet(queueName, autoAck);
			if (response != null) {
			    byte[] body = response.getBody();
			    message = new String(body);
			}
		} catch(Exception e) {
			LOGGER.error("Rabbit Error - host: "+host+" queueName: "+queueName, e);
		}
		return message;
	}
	
	public void destroy() {
		try {
			channel.close();
			connection.close();
		} catch (IOException e) {
			LOGGER.error("Rabbit Error destroying the channel and connection - host: "+host+" queueName: "+queueName, e);
		} 
	}
	
	
}
