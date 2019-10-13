package com.revature;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.revature.models.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

   private final SimpMessagingTemplate template;

   @Autowired
   public WebSocketController(SimpMessagingTemplate temp) {
      this.template = temp;
   }

   @MessageMapping("/send/message")
   public void onReceivedMessage(@Payload Message message) {
      this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) +"- "+message.getBody());
   }

}
