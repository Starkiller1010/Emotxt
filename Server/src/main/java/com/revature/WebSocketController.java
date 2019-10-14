package com.revature;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.revature.models.Greeting;
import com.revature.models.HelloMessage;
import com.revature.models.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

   private final SimpMessagingTemplate template;

   @Autowired
   public WebSocketController(SimpMessagingTemplate temp) {
      this.template = temp;
   }

   @MessageMapping("/hello")
   public void onReceivedMessage(HelloMessage message) throws Exception {
     // this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) +"- "+message.getBody());
   //   return message;
      System.out.println("Endpoint hit");
      //return new Greeting("Hello!");
      template.convertAndSend("/topic/greetings", "payload");
   }

}
