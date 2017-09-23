import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import start.entities.AppUser;
import start.entities.Interest;
import start.entities.Tweet;
import start.entities.TweetLike;
import start.miscellaneous.AppUserCapture;
import start.miscellaneous.InterestCapture;
import start.miscellaneous.Response;
import start.repos.AppUserRepo;
import start.repos.InterestRepo;
import start.repos.TweetLikeRepo;
import start.repos.TweetRepo;
import start.services.TweetLikeService;
import start.services.TweetService;
import start.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterCloneTest {

    //Created on client side
    InterestCapture interestCapture = new InterestCapture(true, false, false, true, false);
    //Client input JSON
    AppUserCapture appUserCapture = new AppUserCapture("Kartik", "Jha", "email", "12345", "kartik", interestCapture);





   // AppUser appUser = new AppUser(appUserCapture.getFirst(), appUserCapture.getLast(), appUserCapture.getEmail(), appUserCapture.getPassword(), appUserCapture.getHandle());
    //Interest interest = new Interest(interestCapture.getUserID(),interestCapture.isCricket(), interestCapture.isFootball(), interestCapture.isGolf(), interestCapture.isMma(), interestCapture.isKabaddi());

    @Mock
    AppUserRepo appUserRepo;

    @Mock
    InterestRepo interestRepo;

    @Mock
    TweetRepo tweetRepo;

    @Mock
    TweetLikeRepo tweetLikeRepo;

    @InjectMocks
    TweetLikeService tweetLikeService;

    @InjectMocks
    TweetService tweetService;

    @InjectMocks
    UserService userService;

    @Test
    public void resolveUserTest(){
        AppUser appUser = userService.resolveUser(appUserCapture);
        assertEquals("Kartik",appUser.getFirst());
        assertEquals("Jha",appUser.getLast());
        assertEquals("email",appUser.getEmail());
        assertEquals("12345",appUser.getPassword());
        assertEquals("kartik",appUser.getHandle());

    }

    @Test
    public void resolveInterestTest(){
        AppUser appUser = userService.resolveUser(appUserCapture);//Tested
        Interest interest = userService.resolveInterest(appUser.getUserID(), appUserCapture);
        assertEquals(true, interest.isCricket());
        assertEquals(false, interest.isFootball());
        assertEquals(false, interest.isGolf());
        assertEquals(true, interest.isMma());
        assertEquals(false, interest.isKabaddi());
        assertEquals(appUser.getUserID(), interest.getUserID());
    }

    @Test
    public void registerUserTest(){

        AppUser appUser = userService.resolveUser(appUserCapture);//Tested
        Interest interest = userService.resolveInterest(appUser.getUserID(),appUserCapture);//Tested

        when(appUserRepo.save(appUser)).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new AppUser();
            }
        });
        when(interestRepo.save(interest)).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Interest();
            }
        });

        Response response = userService.registerUser(appUserCapture);
        assertEquals("Success", response.getMessage());
    }

    @Test
    public void getUserByUserNameTest(){


        when(appUserRepo.getUserByUserName("kartik")).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new AppUser(1, "Kartik", "Jha", "email", "12345", "kartik");
            }
        });

        AppUser appUser = appUserRepo.getUserByUserName("kartik");

        when(interestRepo.getInterestByUserID(appUser.getUserID())).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Interest(1, 1, true, false, false, true, false);
            }
        });

        AppUserCapture appUserCapture = userService.getUserByUserName("kartik");
        assertEquals("Kartik", appUserCapture.getFirst());
        assertEquals("Jha", appUserCapture.getLast());
        assertEquals("email", appUserCapture.getEmail());
        assertEquals("kartik", appUserCapture.getHandle());
        assertEquals(null, appUserCapture.getPassword());
        assertEquals(1, appUserCapture.getUserID());
        assertEquals(1, appUserCapture.getInterestCapture().getInterestID());
        assertEquals(true, appUserCapture.getInterestCapture().isCricket());
        assertEquals(false, appUserCapture.getInterestCapture().isFootball());
        assertEquals(false, appUserCapture.getInterestCapture().isGolf());
        assertEquals(true, appUserCapture.getInterestCapture().isMma());
        assertEquals(false, appUserCapture.getInterestCapture().isKabaddi());

    }

    @Test
    public void sortUserIDsUsingInterestsTest(){
        Interest interestPrimary = new Interest(1, 1, true, false, false, true, true);
        List<Interest> interests = new ArrayList<>();
        interests.add(new Interest(6, 4, false, false, false, false, true));
        interests.add(new Interest(13, 9, true, true, false, true, false));
        interests.add(new Interest(12, 7, false, true, true, false, true));
        interests.add(new Interest(3, 2, true, false, false, false, true));
        List<Integer> sortedUserIDs = new ArrayList<>();
        sortedUserIDs = userService.sortUserIDsUsingInterests(interestPrimary, interests);
        assertEquals(9, (int)sortedUserIDs.get(0));
        assertEquals(2, (int)sortedUserIDs.get(1));
        assertEquals(4, (int)sortedUserIDs.get(2));
        assertEquals(7, (int)sortedUserIDs.get(3));

    }

    @Test
    public void saveTweetTest(){
        //Client JSON
        Tweet tweet = new Tweet(1, "test", "test content", "kartik");

        when(tweetRepo.save(tweet)).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Tweet();
            }
        });

        Response response = tweetService.saveTweet(tweet);
        assertEquals("Success", response.getMessage());
    }

    @Test
    public void likeTweet(){
        //Client JSON
        Tweet tweet = new Tweet(2, "test", "test content", "kartik", 1);
        when(tweetRepo.save(new Tweet())).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Tweet();
            }
        });

        when(tweetLikeRepo.save(new TweetLike())).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new TweetLike();
            }
        });

        Response response = tweetService.likeTweet(tweet);

        assertEquals("Success", response.getMessage());
    }

    /*


    @Test
    public void getUserByUserName(){

        when(appUserRepo.getUserByUserName("kartik")).then(new Answer<AppUser>() {
            @Override
            public AppUser answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new AppUser(1, "Kartik", "Jha", "email", "12345", "kartik");
            }
        });

        AppUser appUser = appUserRepo.getUserByUserName("kartik");

        when(interestRepo.getInterestByUserID(appUserRepo.getUserByUserName("kartik").getUserID())).then(new Answer<Interest>() {

            @Override
            public Interest answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new Interest(1, true, false, false, true, false );
            }
        });

        Interest interest = interestRepo.getInterestByUserID(appUser.getUserID());

        InterestCapture interestCapture = new InterestCapture(interest.getUserID(), interest.isCricket(), interest.isFootball(), interest.isGolf(), interest.isMma(), interest.isKabaddi());
    }

    @Test
    public void registerUserTest(){
    }

    @Test
    public void suggestionsTest(){
    }

    */

}
