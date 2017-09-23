package start.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entities.Tweet;
import start.entities.TweetLike;
import start.miscellaneous.Response;
import start.repos.TweetLikeRepo;
import start.repos.TweetRepo;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    TweetRepo tweetRepo;

    @Autowired
    UserService userService;

    @Autowired
    TweetLikeRepo tweetLikeRepo;

    //Tested OK
    public Response saveTweet(Tweet tweet){
        String message = "Success";
        try{
            tweetRepo.save(tweet);
        }catch (Exception e){
            message = "Error";
        }
        return new Response(message);
    }

    /*public Response retweet(Tweet tweet){
        tweetRepo.save(new Tweet(tweet.getUserID(),tweet.getHashTag(),tweet.getContent(),tweet.getHandle()));
        return new Response(("Success"));
    }*/
    //Tested OK
    public Response likeTweet(Tweet tweet){

        String message = "Success";
        int likerID = tweet.getUserID();
        try{
            int userID = tweetRepo.getUserIDByTweetID(tweet.getTweetID());
            tweet.setUserID(userID);
            tweetRepo.save(tweet);
            tweetLikeRepo.save(new TweetLike(likerID,tweet.getTweetID()));
        }catch (Exception e){
            message = "Error";
        }

        return new Response(message);
    }

    public Response unLikeTweet(Tweet tweet){
        int likerID = tweet.getUserID();
        String message = "Success";
        try{
            int userID = tweetRepo.getUserIDByTweetID(tweet.getTweetID());
            tweet.setUserID(userID);
            tweetLikeRepo.deleteTweetLike(likerID, tweet.getTweetID());
            tweetRepo.save(tweet);
        }catch (Exception e){
            message = "Error";
        }
        return new Response("Success");
    }

    public List<Tweet> getTweetByTag(String tag){
        return tweetRepo.getTweetByTag(tag);
    }

    public List<Tweet> getTweetByUserName(String username){
        int userID = userService.getUserByUserName(username).getUserID();
        return getTweetByUserID(userID);
    }

    public List<Tweet> getTweetByUserID(int userID){
        return tweetRepo.getTweetByUserID(userID);
    }
}
