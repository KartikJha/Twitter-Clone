package start.entities;

import javax.persistence.*;

@Entity(name = "message")
public class Message {

    @Column(name = "message_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int messageID;
    @Column(name = "sender_id")
    int senderID;
    @Column(name = "receiver_id")
    int receiverID;
    @Column(name = "message")
    String message;

    public Message() {
    }


    public Message(int senderID, int receiverID, String messsage) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.message  = messsage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
}

