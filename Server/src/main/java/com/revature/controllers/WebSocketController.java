package com.revature.controllers;

import com.revature.apis.EmotionAPI;
import com.revature.dtos.Letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
   public void onReceivedMessage(Letter message) throws Exception {
      System.out.println("Endpoint hit and received this: " + message);
      String emo = EmotionAPI.getInstance().emotionGuage(message.getBody());
      template.convertAndSend("/topic/"+message.getDestination(),  new Letter(message.getBody(), message.getAuthor(), emo));
   }
 
}
