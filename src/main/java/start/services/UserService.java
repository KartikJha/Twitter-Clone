package start.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entities.AppUser;
import start.entities.Interest;
import start.entities.Message;
import start.miscellaneous.AppUserCapture;
import start.miscellaneous.InterestCapture;
import start.miscellaneous.Response;
import start.repos.AppUserRepo;
import start.repos.InterestRepo;
import start.repos.TweetRepo;

import java.util.*;

@Service
public class UserService  {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    TweetRepo tweetRepo;

    @Autowired
    InterestRepo interestRepo;

    @Autowired
    FollowerService followerService;

    @Autowired
    MessageService messageService;

    //Tested FAILED(unable to perform test correctly)
    public Response registerUser(AppUserCapture appUserCapture){

        AppUser appUser=resolveUser(appUserCapture);
        String message = "Success";
        try{
            appUserRepo.save(appUser);
            Interest interest=resolveInterest(appUserRepo.getUserByUserName(appUser.getHandle()).getUserID(), appUserCapture);
            interestRepo.save(interest);
        }catch (Exception e){
            message = "Error";
        }
        return new Response(message);
    }

    //Tested OK
    public AppUserCapture getUserByUserName(String username){

        AppUser appUser=appUserRepo.getUserByUserName(username);
        Interest interest=interestRepo.getInterestByUserID(appUser.getUserID());
        InterestCapture interestCapture=new InterestCapture(interest.getInterestID(),interest.getUserID(),interest.isCricket(),interest.isFootball(),interest.isGolf(),interest.isMma(),interest.isKabaddi());
        return new AppUserCapture(appUser.getUserID(),appUser.getFirst(),appUser.getEmail(),appUser.getLast(),null,appUser.getHandle(),interestCapture);
    }

    public AppUserCapture getUserByUserID(int userID){
        //String username=appUserRepo.getUserNameByUserID(userID);
        AppUser appUser=appUserRepo.findOne(userID);
        Interest interest=interestRepo.getInterestByUserID(appUser.getUserID());
        InterestCapture interestCapture=new InterestCapture(interest.getInterestID(),interest.getUserID(),interest.isCricket(),interest.isFootball(),interest.isGolf(),interest.isMma(),interest.isKabaddi());
        return new AppUserCapture(appUser.getUserID(),appUser.getFirst(),appUser.getEmail(),appUser.getLast(),appUser.getPassword(),appUser.getHandle(),interestCapture);
        //return getUserByUserName(username);
    }

    public List<AppUserCapture> getSuggestedUsers(int userID){

        List<AppUserCapture> followers = followerService.getFollowedByUserName(getUserByUserID(userID).getHandle());
        List<Integer> followerIDs = new ArrayList<>();
        followerIDs.add(userID);
        for (AppUserCapture a: followers) {
            followerIDs.add(a.getUserID());
        }
        List<Interest> interests=interestRepo.getInterestsNotHavingUserIDs(followerIDs);
        Interest interestPrimary=interestRepo.getInterestByUserID(userID);
        List<Integer> sortedSuggestedUserIDs=sortUserIDsUsingInterests(interestPrimary, interests);
        List<AppUserCapture> appUserCaptures = new ArrayList<>();
        for (int i = 0; i < sortedSuggestedUserIDs.size(); i++){
            appUserCaptures.add(getUserByUserID(sortedSuggestedUserIDs.get(i)));
        }
        return appUserCaptures;

    }

    //Tested OK
    public List<Integer> sortUserIDsUsingInterests(Interest interestPrimary, List<Interest> interests){
        List<Integer> sortedSuggestedUserIDs = new ArrayList<>();
        List<Integer> sortedInterest = new ArrayList<>();
        for(int i = 0, j = 0; i < interests.size(); i++){
            int count = 0;
            if(interests.get(i).isCricket() == interestPrimary.isCricket() && interestPrimary.isCricket())
                count++;
            if(interests.get(i).isFootball() == interestPrimary.isFootball() && interestPrimary.isFootball())
                count++;
            if(interests.get(i).isGolf() == interestPrimary.isGolf() && interestPrimary.isGolf())
                count++;
            if(interests.get(i).isKabaddi() == interestPrimary.isKabaddi() && interestPrimary.isKabaddi())
                count++;
            if(interests.get(i).isMma() == interestPrimary.isMma() && interestPrimary.isMma())
                count++;

            if(count!=0){

                sortedInterest.add(count);
                sortedSuggestedUserIDs.add(interests.get(i).getUserID());
                int k=sortedInterest.get(sortedInterest.size()-1);
                int l;
                for (  l = sortedInterest.size()-2; l >= 0 && sortedInterest.get(l) < k; l--) {
                    sortedSuggestedUserIDs.set(l + 1, sortedSuggestedUserIDs.get(l));
                    sortedInterest.set(l+1, sortedInterest.get(l));
                }
                sortedInterest.set(l+1, k);
                sortedSuggestedUserIDs.set(l+1, interests.get(i).getUserID());
                j++;

            }

        }

        return sortedSuggestedUserIDs;
    }

    public Response updateUser(AppUserCapture appUserCapture){
        String message = "Success";
        AppUser appUser=resolveUser(appUserCapture, 1);
        Interest interest=resolveInterest(appUserCapture,1);
        try{
            appUserRepo.save(appUser);
            interestRepo.save(interest);
        }catch (Exception e){
            message = "Error";
        }
        return new Response(message);
    }

    //Tested OK
    public AppUser resolveUser(AppUserCapture appUserCapture){
        return new AppUser(appUserCapture.getFirst(),appUserCapture.getLast(),appUserCapture.getEmail(),appUserCapture.getPassword(),appUserCapture.getHandle());
    }

    public AppUser resolveUser(AppUserCapture appUserCapture, int a){
        return new AppUser(appUserCapture.getUserID(),appUserCapture.getFirst(),appUserCapture.getLast(),appUserCapture.getEmail(),appUserCapture.getPassword(),appUserCapture.getHandle());
    }

    //Tested OK
    public Interest resolveInterest(int userID,AppUserCapture appUserCapture){
        return new Interest(userID,appUserCapture.getInterestCapture().isCricket(),appUserCapture.getInterestCapture().isFootball(),appUserCapture.getInterestCapture().isGolf(),appUserCapture.getInterestCapture().isMma(),appUserCapture.getInterestCapture().isKabaddi());
    }

    public Interest resolveInterest(AppUserCapture appUserCapture, int a){
        return new Interest(appUserCapture.getInterestCapture().getInterestID(), appUserCapture.getInterestCapture().getUserID(),appUserCapture.getInterestCapture().isCricket(),appUserCapture.getInterestCapture().isFootball(),appUserCapture.getInterestCapture().isGolf(),appUserCapture.getInterestCapture().isMma(),appUserCapture.getInterestCapture().isKabaddi());
    }

    public Interest getInterestByUserName(String username){
        int userID=getUserByUserName(username).getUserID();
        return interestRepo.getInterestByUserID(userID);
    }

    public List<Message> getMessagesByUserName(String username){
        int userID = appUserRepo.getUserIDByUserName(username);
        return messageService.getReceivedMessages(userID);
    }



}
