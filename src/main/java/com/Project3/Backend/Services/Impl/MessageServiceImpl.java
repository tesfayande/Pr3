package com.Project3.Backend.Services.Impl;

import com.Project3.Backend.Models.Message;
import com.Project3.Backend.Models.Rental;
import com.Project3.Backend.Models.User;
import com.Project3.Backend.Repositories.MessageRepository;
import com.Project3.Backend.Repositories.RentalRepository;
import com.Project3.Backend.Repositories.UserRepository;
import com.Project3.Backend.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;


    @Override
    public Message saveMessage(Message message,int rentalID) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());

        Rental rental = rentalRepository.findById(rentalID).orElseThrow(()-> new RuntimeException());

        message.setSender(user);
        message.setRental(rental);

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();

    }

    @Override
    public Message getMessageById(int id) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()){
            return message.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public Message updateMessage(Message message, int id) {

        Message existingMessage = messageRepository.findById(id).orElseThrow(()-> new RuntimeException());
        //existingMessage.setRental_id(message.getRental_id());
        //existingMessage.setUser_id(message.getUser_id());
        existingMessage.setMessage(message.getMessage());
        // save
        messageRepository.save(existingMessage);
        return existingMessage;
    }

    @Override
    public void deleteMessage(int id) {

        //check
        messageRepository.findById(id).orElseThrow(()-> new RuntimeException());
        //delete
        messageRepository.deleteById(id);
    }
}
