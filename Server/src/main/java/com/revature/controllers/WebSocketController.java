package com.revature.controllers;

import com.revature.models.Greeting;
import com.revature.models.HelloMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

   private final SimpMessagingTemplate template;

   @Autowired
   public WebSocketController(SimpMessagingTemplate temp) {
      this.template = temp;
   }

   @MessageMapping("/hello")
  // @SendTo("/topic/greetings")
   public void onReceivedMessage(String message) throws Exception {
      System.out.println("Endpoint hit" + message);
      // template.convertAndSend("/topic/greetings", "Got Message");
     // return "Got Message";
   }
 
}
