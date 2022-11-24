package gt.com.tigo.workeragreementrenewmanagement.commons.rabbitmq;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageSenderRabbit {

	Boolean durable = true;
	Boolean exclusive = false;
	Boolean autoDelete = false;
	Channel channel = null;
	String exchangeType = "direct";
	String exchangeName = "";
	String host = "localhost";
	Connection connection = null;
	String virtualhost = "";
	String portString = "";
	String username;
	String password;
	String exchangeId;

	private final Logger LOGGER = Logger.getLogger(getClass());

	public MessageSenderRabbit(String virtualhost, String host, String port, String username, String password, String exchangeId) {
		createConnection(virtualhost, host, port, username, password, exchangeId);
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
			//factory.setConnectionTimeout(90000000);
			factory.setPort(port);
			factory.setVirtualHost(virtualhost);
			
			this.exchangeId = queueName;
			
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

	
	
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}	
	
	public void destroy() {
		try {
			if(channel != null) {
				channel.close();
			}
		} catch (IOException e) {
			LOGGER.error("Error closing channel", e);
		}
		
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (IOException e) {
			LOGGER.error("Error closing connection", e);
		}

	}

	public boolean sendMessageExchange(String queueName, String message, String transId) {
		if(channel == null) {
			createConnection(this.virtualhost, this.host, this.portString, this.username, this.password, this.exchangeId);
		}

		try {
			if(channel != null) {
				channel.basicPublish("", queueName, null, message.getBytes());
				//channel.basicPublish(this.exchangeName, routingKey, null, message.getBytes());
				LOGGER.debug("TransactionId: " + transId + " - Message sent: " + message);
				return true;
			}

		} catch (IOException e) {
			LOGGER.error("TransactionId: " + transId + " - Error RabbitMQ Sending message - host: '" + host + "'");
		}
		return false;

	}

}
