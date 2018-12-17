package database;

import objects.Coin;
import objects.Result;
import objects.Tweet;

import java.util.ArrayList;
import java.util.List;

public class Interactions {

    public static int removeOldTweets() {
        // removes tweets older than one month.
        int month = ((int) (System.currentTimeMillis() / 1000)) - 2629743;
        String query = "DELETE FROM tweets WHERE time < " + month;
        return new DataBaseInteractions().executeQuery(query, new Object());
    }

    public static List<Coin> getCoins() {
        String query = "SELECT * FROM coins";
        List<Coin> coins = new ArrayList<>();
        for (Object object : new DataBaseInteractions().retrieve(query, new Coin())) {
            Coin coin = (Coin) object;
            coins.add(coin);
        }
        return coins;
    }

    public static int updateCoin(Coin coin) {
        String query = "UPDATE coins SET info = ? WHERE id = " + coin.getId();
        return new DataBaseInteractions().executeQuery(query, coin);
    }

    public static List<Tweet> getTweets(int coinId, int seconds) {
        int now = (int) (System.currentTimeMillis() / 1000);
        String query = "SELECT * FROM tweets WHERE coinId = " + coinId + " AND time > " + (now - seconds);
        List<Tweet> tweets = new ArrayList<>();
        for (Object object : new DataBaseInteractions().retrieve(query, new Tweet())) {
            Tweet tweet = (Tweet) object;
            tweets.add(tweet);
        }
        return tweets;
    }

    public static List<Tweet> getAllTweets(int coinId) {
        String query = "SELECT * FROM tweets WHERE coinId = " + coinId;
        List<Tweet> tweets = new ArrayList<>();
        for (Object object : new DataBaseInteractions().retrieve(query, new Tweet())) {
            Tweet tweet = (Tweet) object;
            tweets.add(tweet);
        }
        return tweets;
    }

    public static List<Tweet> getTweetsBefore(int coinId, int seconds) {
        int now = (int) (System.currentTimeMillis() / 1000);
        String query = "SELECT * FROM tweets WHERE coinId = " + coinId + " AND time > " + (now - seconds * 2) + " AND time < " + (now - seconds);
        List<Tweet> tweets = new ArrayList<>();
        for (Object object : new DataBaseInteractions().retrieve(query, new Tweet())) {
            Tweet tweet = (Tweet) object;
            tweets.add(tweet);
        }
        return tweets;
    }

    public static int addTweet(Tweet tweet) {
        String query = "INSERT INTO tweets VALUES (? , ? , ?, ?, ?)";
        return new DataBaseInteractions().executeQuery(query, tweet);
    }

    // get tweet count for last given seconds
    public static long getTweetCount(int coinId, int seconds) {
        int now = (int) (System.currentTimeMillis() / 1000);
        System.out.println(now - seconds);
        String query = "SELECT COUNT(*) FROM tweets WHERE coinId = " + coinId + " AND time > " + (now - seconds);
        return (long) (new DataBaseInteractions().retrieve(query));
    }

    //
    public static long getTweetCountBefore(int coinId, int seconds) {
        int now = (int) (System.currentTimeMillis() / 1000);
        System.out.println(now - seconds);
        String query = "SELECT COUNT(*) FROM tweets WHERE coinId = " + coinId + " AND time > " + (now - seconds * 2) + " AND time < " + (now - seconds);
        return (long) (new DataBaseInteractions().retrieve(query));
    }

    public static List<Result> getResults(int startingTime, int endTime) {
        return null;
    }

    public static int addResult(Result result) {
        return 0;
    }

}
