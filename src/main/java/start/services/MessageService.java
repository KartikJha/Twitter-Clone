package start.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entities.Message;
import start.miscellaneous.Response;
import start.repos.MessageRepo;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public Response sendMessage(Message message){
        String msg = "Success";
        try {
            messageRepo.save(message);
        }catch (Exception e){
            msg = "Error";
        }
        return new Response(msg);
    }

    public List<Message> getReceivedMessages(int userID){
        return messageRepo.getReceivedMessages(userID);
    }


}
