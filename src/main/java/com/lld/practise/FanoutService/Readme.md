Create following table

User table
    userId
    name
    email

Post table:
    postId
    userId
    content:

Follower table : Cassandra
    FollowerId
    FollowerId

NewsFeed table: Cassandra
    userId
    postId


Route for user creation
/api/new_user

Route for following a person
/api/followerId/follweId

Route for post creaton
post api/userId/
    payload

Route for fetching news feed
/api/fetch_feed

Service:
1. Fanout service using queue and add to cache then to Db

Maintain cache for all 
Also when i pull data from cache remove it 


    
  Doc: https://docs.google.com/document/d/1yWFtu3lyGwusKTePyWBgUV-6FxWx-uhxpNDmhkFOlMQ/edit?tab=t.3b2u52xr9kza#heading=h.cgvnaubgiiu5