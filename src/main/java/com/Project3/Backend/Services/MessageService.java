package com.Project3.Backend.Services;

import com.Project3.Backend.Models.Message;


import java.util.List;

public interface MessageService {



    Message saveMessage(Message message,int rentalID);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    Message updateMessage(Message message,int id);
    void deleteMessage(int id);


}
