package start.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import start.entities.Message;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    @Query(value = "select m from message m where m.receiverID=?1")
    List<Message> getReceivedMessages(int userID);
}
