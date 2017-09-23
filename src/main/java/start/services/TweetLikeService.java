package start.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entities.TweetLike;
import start.repos.TweetLikeRepo;

import java.util.List;

@Service
public class TweetLikeService {

    @Autowired
    TweetLikeRepo tweetLikeRepo;

    public List<TweetLike> getTweetLikeByUserID(int userID){
        return tweetLikeRepo.getTweetLikeByUserID(userID);
    }
}
