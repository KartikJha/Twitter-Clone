var app=angular.module("test",["ngRoute", "ngStorage"])
.run(function($rootScope, $localStorage){
    //if($localStorage.loggedUser)
     //   $rootScope.handle = $localStorage.loggedUser;
    //else
      //  $rootScope.handle;


});

app.config(function($routeProvider, $locationProvider){

    $routeProvider
    .when("/register", {
        templateUrl : "templates/register.html?d="+new Date(),
        //controller : "registerController"
    })
    .when("/login", {
        templateUrl : "templates/login.html?d="+new Date(),
        //controller : "loginController"
    })
    .when("/home", {
        templateUrl : "templates/home.html",
        //controller : "homeController"
    })
//    .when("/login/home", {
//        templateUrl : "templates/home.html",
//        controller : "homeController"
//    })
    .when("/", {
        templateUrl: "templates/main.html",
        //controller: "testController"
    });

    //$locationProvider.hashPrefix('!');

}).controller("testController",function($scope){

    $scope.hello="Welcome to twitter clone!";

}).controller("registerController",function($scope, $http, $location){

    $scope.data = {interestCapture : {}};
    $scope.error;
    $scope.register=function(isValid){

        if(isValid){

            var url = '/register';
            $http.post(url, $scope.data, {})
            .success(function (data, status, headers, config) {
                if(data.message === 'Success')
                    $location.path("/login");
                else
                    $scope.error = "user exists";

            })
            .error(function (data, status, header, config) {

            });

        }

    }

}).controller("loginController", function($scope, $http, $location, $rootScope, $localStorage){
        console.log(1);
        var authenticate = function(credentials, callback) {

            var headers = credentials ? {authorization : "Basic "
                + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers : headers}).success(function(data) {
              if (data) {
                $rootScope.authenticated = true;
                //rootScope.handle = data.name;
              } else {
                $rootScope.authenticated = false;
              }
              callback && callback();
            }).error(function() {
              $rootScope.authenticated = false;
              //$scope.error = 'Invalid Credentials';
              //callback && callback();
            });

        }

      //authenticate();
      $scope.credentials = {};
      $scope.login = function() {
          authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {
              $location.path("/home");
              $scope.error = false;
            } else {
              $location.path("/login");
              $scope.error = 'Invalid Credentials';
            }
          });
      };


}).controller("homeController", function($scope, $http, $rootScope, $interval, $location, $localStorage, $window, $route, $anchorScroll){


    $scope.tabs = "templates/home_partials/tabs/following.html";
    $scope.updateData = {interestCapture:{}};
    $scope.error;
    $scope.success;
    $scope.user;
    $scope.following=[];
    $scope.followers=[];
    $scope.suggestions=[];
    $scope.tweets=[];
    $scope.likedTweets=[];
    $scope.homePartials="templates/home_partials/home_main.html";
    $scope.userProfile;
    $scope.index;
    $scope.retweetValue = "";
    //$scope.userProfileViewParams= { tweet : 1, following : 2, follower : 3, suggestion : 4};
    //$scope.homeMainPartials="templates/home_partials/home_main_partials/"+$scope.hMP;

    $scope.viewProfileTweet=function(index){

        $scope.homePartials="templates/home_partials/home_profile_view.html";
        $scope.tabs = "templates/home_partials/tabs/following.html";
            var x = $scope.tweets[index].handle;
            $http.get('/get_user_sec_username?username='+x, {})
            .success(function(data, status, header, config){

                //get tweets for user profile view
                $scope.userProfile = data;
                $http.get("/get_tweet_username?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.tweets=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_followers?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.followers=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_following?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.following=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });


            })
            .error(function(data, status, header, config){
                console.log(data);
            })

    }

    $scope.viewProfileFollowing=function(index){

        $scope.homePartials="templates/home_partials/home_profile_view.html";
        $scope.tabs = "templates/home_partials/tabs/following.html";

            //$http.get('/get_user_username?username'+$scope.following[index].handle, {})
            //.success(function(data, status, header, config){

                //get tweets for user profile view
                $scope.userProfile = $scope.following[index];//data;
                $http.get("/get_tweet_username?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.tweets=data;

                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_followers?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.followers=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_following?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.following=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

           // })
            //.error(function(data, status, header, config){
             //   console.log(data);
           // })
    }

    $scope.viewProfileFollower = function(index){

        $scope.homePartials="templates/home_partials/home_profile_view.html";
        $scope.tabs = "templates/home_partials/tabs/following.html";

            //$http.get('/get_user_username?username'+$scope.following[index].handle, {})
            //.success(function(data, status, header, config){

                //get tweets for user profile view
                $scope.userProfile = $scope.followers[index];//data;
                $http.get("/get_tweet_username?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.tweets=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_followers?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.followers=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_following?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.following=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });



           // })
            //.error(function(data, status, header, config){
             //   console.log(data);
           // })

    }

    $scope.viewProfileSuggestions=function(index){
        $scope.homePartials="templates/home_partials/home_profile_view.html";
        $scope.tabs = "templates/home_partials/tabs/following.html";

        $scope.userProfile = $scope.suggestions[index];//data;
        $http.get("/get_tweet_username?username="+$scope.userProfile.handle, {})
        .success(function(data, status, headers, config){
             $scope.tweets=data;

        })
        .error(function(data, status, headers, config){
             console.log(data);
        });
                $http.get("/get_followers?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.followers=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_following?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.following=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });


    }

    $scope.viewProfile=function(){
        $scope.homePartials="templates/home_partials/home_profile_view.html";
        $scope.tabs = "templates/home_partials/tabs/following.html";
        $scope.userProfile=$scope.user;
        $scope.primaryTweets();

                $http.get("/get_followers?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.followers=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

                $http.get("/get_following?username="+$scope.userProfile.handle, {})
                .success(function(data, status, headers, config){
                    $scope.following=data;
                })
                .error(function(data, status, headers, config){
                    console.log(data);
                });

    }

    $scope.viewHome=function(){
        $scope.homePartials="templates/home_partials/home_main.html";
        $scope.initializePrimary();

    }

    $scope.editProfile=function(){
        $scope.homePartials="templates/home_partials/home_profile_edit.html";
        $scope.userProfile=$scope.user;
    }

    $scope.logout = function() {

      $http.post('logout', {}).success(function() {
        $rootScope.authenticated = false;
        $rootScope.handle=null;
        $localStorage.loggedUser=null;
        $location.path("/");
      }).error(function(data) {
        $rootScope.authenticated = false;
      });

    }

    $scope.tweetData={userID: null, content: "", hashTag: "", handle: null};
    //User context initiallization
    //get primary user

    $scope.primaryTweets=function(){

        $http.get("/get_tweet_username?username="+$scope.user.handle, {})
        .success(function(data, status, headers, config){
            $scope.tweets=data;
        })
        .error(function(data, status, headers, config){
            $scope.error=data;
        });


    }

    $scope.followedTweets=function(){

            for(var i=0; i<$scope.following.length; i++){
                $http.get("/get_tweet_username?username="+$scope.following[i].handle, {})
                .success(function(data, status, headers, config){
                    $scope.tweets = $scope.tweets.concat(data);
                })
                .error(function(data, status, headers, config){
                    $scope.error=data;
                });
            }
    }

    $scope.followingTweets=function(){

            for(var i=0; i<$scope.followers.length; i++){
                $http.get("/get_tweet_username?username="+$scope.followers[i].handle, {})
                .success(function(data, status, headers, config){
                    $scope.tweets = $scope.tweets.concat(data);
                })
                .error(function(data, status, headers, config){
                    $scope.error=data;
                });
            }


    }
    /*
    $scope.tweetUpdate=function(){

        $scope.tweets=[];
        $scope.primaryTweets();
        $scope.followedTweets();

    }
    */

    //$interval($scope.tweetUpdate, 200000);

    $scope.isLikedTweet=function(tweet){
        var ans = false;

        for(var i = 0; i < $scope.likedTweets.length; i++)
            if(tweet.tweetID === $scope.likedTweets[i].tweetID){
                ans=true;
                break;
            }

        return ans;
    }

    $scope.likeTweet=function(index){

       if(!($scope.isLikedTweet($scope.tweets[index]))){

            //console.log($scope.isLikedTweet($scope.tweets[index]));

            $scope.tweets[index].likeCount+=1;
            var tweet=$scope.tweets[index];
            tweet.userID=$scope.user.userID;
            $http.post("/like", tweet, {})
            .success(function(data, status, headers, config){
                $scope.success=data.message;
                $scope.getLikedTweets();
            })
            .error(function(data, status, headers, config){
                $scope.error=data;
            })



       }

    }

    $scope.unLikeTweet=function(index){

        if(($scope.isLikedTweet($scope.tweets[index]))){

            //console.log($scope.isLikedTweet($scope.tweets[index]));

            $scope.tweets[index].likeCount-=1;
            var tweet=$scope.tweets[index];
            tweet.userID=$scope.user.userID;
            $http.post("/unlike",tweet, {})
            .success(function(data, status, headers, config){
                $scope.success=data.message;
                $scope.getLikedTweets();
            })
            .error(function(data, status, headers, config){
                $scope.error=data;
            })



        }

    }

    $scope.getLikedTweets=function(){

        $http.get("/get_tweets_liked?user_id="+$scope.user.userID, {})
        .success(function(data, status, headers, config){
            $scope.likedTweets=data;

        })
        .error(function(data, status, headers, config){
            $scope.error=data;
        });
    }

    $scope.initializePrimary=function(){

        $http.get("/get_user_username", {})
        .success(function(data, status, headers, config){

            $scope.user=data;
            //get primary user tweets
            $scope.primaryTweets();

            //get following
            $http.get("/get_following?username="+$scope.user.handle, {})
            .success(function(data, status, headers, config){

                $scope.following=data;
                //get tweets of followed
                $scope.followedTweets();

            })
            .error(function(data, status, headers, config){
                $scope.error=data;
            });

            //get likes
            $scope.getLikedTweets();

            $scope.sortTweets();

            //get followers
            $http.get("/get_followers?username="+$scope.user.handle, {})
            .success(function(data, status, headers, config){
                $scope.followers=data;
                //get tweets of following

            })
            .error(function(data, status, headers, config){
                $scope.error=data;
            });

            //get suggested

            $http.get("/get_suggested_users?user_id="+$scope.user.userID, {})
            .success(function(data, status, headers, config){
                $scope.suggestions=data;
            })
            .error(function(data, status, headers, config){
                $scope.error=data;
            });

        })
        .error(function(data, status, headers, config){
            $scope.error=data;
        });



    }

    $scope.findTag=function(content){

        var val=content.split(" ");

        console.log(val);

        var tags="";
        for(var i=0; i<val.length; i++){
            if(val[i][0]==='#'){
                tags+=(val[i].slice(1));
                break;
            }

            console.log(tags);
        }

        return tags;
    }

    $scope.parseAndFetch=function(){

        if(!$scope.searchString)
             $scope.searchString = 'null string';

        else if($scope.searchString[0]=='#'){

            var tag = $scope.searchString.slice(1);


            if(!tag){
                $scope.searchString = 'returning un-tagged tweets';
            }

                var url = "/get_tweet_tag?tag="+tag;
                $http.get(url, {})
                .success(function (data, status, headers, config) {

                    $scope.tweets=data;
                    if(data.length === 0){
                        $scope.searchString = 'no such tag';
                    }
                })
                .error(function (data, status, headers, config) {
                    $scope.searchString = 'no such tag';
                });

        }

        else if($scope.searchString[0]=='@'){

            var username = $scope.searchString.slice(1);

            if(username){
                var url = "/get_tweet_username?username="+username;
                $http.get(url, {})
                .success(function (data, status, headers, config) {
                    $scope.tweets=data;

                })
                .error(function (data, status, headers, config) {
                    $scope.searchString = 'no such user';
                });
            }
            else{
                $scope.searchString = 'null username';
            }

        }


    }

    $scope.update=function(isValid){


        if(isValid){

            $scope.updateData.userID = $scope.user.userID;
            $scope.updateData.email = $scope.user.email;
            $scope.updateData.handle = $scope.user.handle;
            $scope.updateData.interestCapture['userID'] = $scope.user.userID;
            $scope.updateData.interestCapture['interestID'] = $scope.user.interestCapture['interestID'];


            $http.post("/update", $scope.updateData, {})
            .success(function(data, status, header, config){
                console.log(data);
               // $scope.registerForm.$setPristine();
                //$scope.registerForm.$setUntouched()
            })
            .error(function(data, status, header, config){
                console.log(data);
            })

        }

    }

    $scope.sortTweets=function(){
        $scope.tweets=$scope.tweets.sort(function(a, b){
            return b.tweetID - a.tweetID;
        });
    }

    $scope.scrollTo = function(id){
         $scope.tabs = "/templates/home_partials/tabs/"+id+".html";
         //$scope.initializePrimary();
    }

    $scope.getFollowedUsers = function(){

        $http.get("/get_following?username="+$scope.user.handle, {})
        .success(function(data, status, headers, config){

            $scope.following=data;//followed users obtained in following[]
            //tweets of followed users obtained
            $scope.followedTweets();

        })
        .error(function(data, status, headers, config){
            $scope.error=data;
        });

    }

    $scope.getFollowers = function(){
        $http.get("/get_followers?username="+$scope.user.handle, {})
        .success(function(data, status, headers, config){
            $scope.followers=data;//users who follow primary obtained in followers[]
        })
        .error(function(data, status, headers, config){
            $scope.error=data;
        });
    }

    $scope.getSuggestedUsers = function(){
        $http.get("/get_suggested_users?user_id="+$scope.user.userID, {})
        .success(function(data, status, headers, config){
            $scope.suggestions=data;//suggested users obtained in suggestions[]
        })
        .error(function(data, status, headers, config){
            $scope.error=data;
        });
    }



    //PRIMARY AJAX FOR AUTOMATIC USER INITIALIZATION FOR HOMEPAGE
    // 1. Tweets of self and followed
    // 2. list of following, followed, suggested

    $http.get("/get_user_username", {})
    .success(function(data, status, headers, config){

        $scope.user=data; // user obtained in user{}

        $scope.primaryTweets();//tweets of primary user saved in tweets[]

        $scope.getFollowedUsers();//followed users and their tweets in following[] and tweets[]

        $scope.getLikedTweets();//get list of liked tweets for deciding if a tweet can be liked or unliked likedTweets[]

        //$scope.sortTweets();//sort tweets, not working

        $scope.getFollowers();//followers in folllowers[]

        $scope.getSuggestedUsers();//suggested users in suggestions[]

    })
    .error(function(data, status, headers, config){
        $scope.error=data;
    });

    $scope.tweet=function(){

        $scope.tweetData.userID=$scope.user.userID;
        $scope.tweetData.hashTag=$scope.findTag($scope.tweetData.content);
        $scope.tweetData.handle=$scope.user.handle;

        console.log($scope.tweetData);

        $http.post("/tweet", $scope.tweetData, {})
        .success(function(data, status, headers, config){
            console.log(data);
            $scope.primaryTweets();
            $scope.followedTweets();
        })
        .error(function(data, status, headers, config){
            console.log(data);
        });
    }

    $scope.unfollow=function(index){

        var follower = { userID : $scope.following[index].userID, followerID : $scope.user.userID };
        $http.post("/unfollow", follower, {})
        .success(function(data, status, header, config){
            console.log(data);
            $scope.primaryTweets();
            $scope.getFollowedUsers();
            $scope.getSuggestedUsers();

            //$scope.followedTweets();
        })
        .error(function(data, status, header, config){
            console.log(data);
        })
    }

    $scope.follow=function(index){

        var follower = { userID : $scope.suggestions[index].userID, followerID : $scope.user.userID };
        $http.post("/follow", follower, {})
        .success(function(data, status, header, config){
            console.log(data);
            $scope.primaryTweets();
            $scope.getFollowedUsers();
            $scope.getSuggestedUsers();
        })
        .error(function(data, status, header, config){
            console.log(data);
        })
    }

    $scope.retweet=function(val){
        $scope.retweetValue = val;
        var tweet=$scope.tweets[$scope.index];
        //var additionalContent = window.prompt("Say something in the retweet, tags will not change");
        var retweet={
            userID: $scope.user.userID,
            hashTag: tweet.hashTag,
            content: tweet.content+' '+$scope.retweetValue,
            handle: $scope.user.handle
        }
        console.log(retweet);

        $http.post("/tweet", retweet, {})
        .success(function(data, status, headers, config){
            console.log(data);
            $scope.primaryTweets();
            $scope.followedTweets();
        })
        .error(function(data, status, headers, config){
            console.log(data);
        });

    }

    $scope.isNotFollowed = function(handle){

        for(var i = 0; i < $scope.following.length; i++){
            if($scope.following[i].handle === handle)
                return false;
        }

        return true;
    }

    $scope.setRetweetIndex=function(i){
        $scope.index = i;
    }

    $scope.followPV = function(handle){

          var follower = { userID : $scope.userProfile.userID, followerID : $scope.user.userID };
          $http.post("/follow", follower, {})
          .success(function(data, status, header, config){
              console.log(data);
          })
          .error(function(data, status, header, config){
              console.log(data);
          })
    }

    $scope.noInterests = function(){

        for( var x in $scope.user.interestCapture){
            if(x)
                return false;
        }

        return true;
    }

});


