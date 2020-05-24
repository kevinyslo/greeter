// By Kevin
package org.jboss.as.quickstarts.greeter.domain;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class JMSUserHandler {
	
	private static final Logger log = Logger.getLogger(JMSUserHandler.class.getName());


    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext ctx;

    @Resource(lookup = "java:jboss/exported/jms/queue/test")
    private Queue queue;
   
    public void sendUserMessage(User user) {   
	   ctx.createProducer().send(queue, user);      
    }
   
    public void listenUserMessage() {   
 	   User user = ctx.createConsumer(queue).receiveBody(User.class);
 	   log.info("=========================== " + user.getUsername());  
     }
}
