package com.revature.controllers;

import com.revature.dtos.Letter;
import com.revature.models.Message;
import com.revature.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

   private final SimpMessagingTemplate template;

   @Autowired
   public WebSocketController(SimpMessagingTemplate temp) {
      this.template = temp;
   }

   @MessageMapping("/hello")
   @SendTo("/topic/hello")
   public Letter onReceivedMessage(Letter message) throws Exception {
      System.out.println("Endpoint hit and received this: " + message);
      return new Letter(message.getBody(), message.getAuthor());
   }
 
}
