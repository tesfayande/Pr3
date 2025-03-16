package com.Project3.Backend.Controllers;

import com.Project3.Backend.Models.Message;
import com.Project3.Backend.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {


    @Autowired
    private MessageService messageService;



    @PostMapping("/send/{id}")
    public ResponseEntity<Message> saveMessage( @RequestBody Message message,@PathVariable("id") int rentalID){

        return new ResponseEntity<Message>(messageService.saveMessage(message,rentalID), HttpStatus.CREATED);


    }


    //GetAll Rest Api
    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    //Get by Id Rest Api
    @GetMapping("/show/{id}")
    // localhost:8080/api/messages/1
    public ResponseEntity<Message> getMessageById(@PathVariable("id") int messageID){
        return new ResponseEntity<Message>(messageService.getMessageById(messageID),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable("id") int id,
                                                 @RequestBody Message message){
        return new ResponseEntity<Message>(messageService.updateMessage(message,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") int id){
        //delete Message from db
        messageService.deleteMessage(id);
        return new ResponseEntity<String>("Message deleted Successfully.", HttpStatus.OK);
    }
}
