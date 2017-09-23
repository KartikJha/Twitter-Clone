package start.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entities.Follower;
import start.miscellaneous.AppUserCapture;
import start.miscellaneous.Response;
import start.repos.FollowerRepo;

import java.util.*;

@Service
public class FollowerService {

    @Autowired
    FollowerRepo followerRepo;

    @Autowired
    UserService userService;


    public Response followUser(Follower follower){
        followerRepo.save(follower);
        return new Response("Success");
    }

    public Response unfollowUser(Follower follower){
        followerRepo.unfollowUser(follower.getUserID(), follower.getFollowerID());
        return new Response("Success");
    }

    public List<AppUserCapture> getFollowersByUserName(String username){

        int userID = userService.getUserByUserName(username).getUserID();
        List<Integer> followerIDs = followerRepo.getFollowerIDsByUserID(userID);
        List<AppUserCapture> followers = new ArrayList<AppUserCapture>();

        for( int i = 0; i < followerIDs.size(); i++)
            followers.add(userService.getUserByUserID(followerIDs.get(i)));

        return followers;
    }

    public List<AppUserCapture> getFollowedByUserName(String username){

        int userID = userService.getUserByUserName(username).getUserID();
        List<Integer> followedIDs = followerRepo.getFollowedIDsByUserID(userID);
        List<AppUserCapture> followed = new ArrayList<AppUserCapture>();

        for( int i = 0; i < followedIDs.size(); i++)
            followed.add(userService.getUserByUserID(followedIDs.get(i)));

        return followed;
    }


}
