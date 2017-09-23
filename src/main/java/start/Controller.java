package start;

import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import start.entities.*;
import start.miscellaneous.AppUserCapture;
import start.miscellaneous.Response;
import start.services.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    TweetService tweetService;

    @Autowired
    FollowerService followerService;

    @Autowired
    TweetLikeService tweetLikeService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/user")
    public Response user(Principal user){
        return new Response(user.getName());
    }

    @RequestMapping(value = "/out")
    public Response out(){
        return new Response("Logout Successful");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Response registerUser(@RequestBody AppUserCapture appUserCapture){
        return userService.registerUser(appUserCapture);
    }

    @RequestMapping(value = "/get_user_username")
    public AppUserCapture getUserByUserName(Principal user/*@RequestParam(value = "username") String username*/){
        return userService.getUserByUserName(user.getName());
    }

    @RequestMapping(value = "/get_user_sec_username")
    public AppUserCapture getUserSecByUserName(@RequestParam(value = "username") String username){
        return userService.getUserByUserName(username);
    }

    @RequestMapping(value = "/get_user_user_id")
    public AppUserCapture getUserByUserID(@RequestParam(value = "user_id") int userID){
        return userService.getUserByUserID(userID);
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/tweet")
    public Response saveTweet(@RequestBody Tweet tweet){
        return tweetService.saveTweet(tweet);
    }

    @RequestMapping(value = "/get_tweet_tag")
    public List<Tweet> getTweetByTag(@RequestParam(value = "tag") String tag){
        return tweetService.getTweetByTag(tag);
    }

    @RequestMapping(value = "/get_tweet_username")
    public List<Tweet> getTweetByUserName(@RequestParam(value = "username") String username){
        return tweetService.getTweetByUserName(username);
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/follow")
    public Response followUser(@RequestBody Follower follower){
        return followerService.followUser(follower);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unfollow")
    public Response unfollowUser(@RequestBody Follower follower){
        return followerService.unfollowUser(follower);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Response updateUser(@RequestBody AppUserCapture appUserCapture){
        return userService.updateUser(appUserCapture);
    }

    @RequestMapping(value = "/get_interest")
    public Interest getInterestByUserName(@RequestParam(value = "username") String username){
        return userService.getInterestByUserName(username);
    }

    /*@RequestMapping(method = RequestMethod.POST,value = "/retweet")
    public Response retweet(@RequestBody Tweet tweet){
        return tweetService.retweet(tweet);
    }*/

    @RequestMapping(value = "/get_followers")
    public List<AppUserCapture> getFollowersByUserName(@RequestParam(value = "username") String username){
        return followerService.getFollowersByUserName(username);
    }

    @RequestMapping(value = "/get_following")
    public List<AppUserCapture> getFollowedByUserName(@RequestParam(value = "username") String username){
        return followerService.getFollowedByUserName(username);
    }

    @RequestMapping(value = "/like")
    public Response likeTweet(@RequestBody Tweet tweet){
        return tweetService.likeTweet(tweet);
    }

    @RequestMapping(value = "/unlike")
    public Response unLikeTweet(@RequestBody Tweet tweet){
        return tweetService.unLikeTweet(tweet);
    }

    @RequestMapping(value = "/get_tweets_liked")
    public List<TweetLike> getTweetLikesByUserID(@RequestParam(value = "user_id") int userID){
        return tweetLikeService.getTweetLikeByUserID(userID);
    }

    @RequestMapping(value = "/get_suggested_users")
    public List<AppUserCapture> getSuggestedUsers(@RequestParam(value = "user_id") int userID, HttpServletResponse httpServletResponse){
        return userService.getSuggestedUsers(userID);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send_message")
    public Response sendMessage(@RequestBody Message message){
        return messageService.sendMessage(message);
    }

    @RequestMapping(value = "/get_messages_by_username")
    public List<Message> getMessagesByUser(@RequestParam(value = "username") String username){
        return userService.getMessagesByUserName(username);
    }
}
