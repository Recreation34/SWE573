package utils;

import com.google.gson.Gson;
import database.Interactions;
import objects.Coin;
import objects.Tweet;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TweetFetcher {

    public void startGettingTweets() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        List<Coin> coins = Interactions.getCoins();

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                for (Coin coin : coins) {
                    keywordloop:
                    for (Object keyword : new Gson().fromJson(coin.getKeywords(), ArrayList.class)) {
                        if (status.getText().contains((String) keyword)) {
                            Tweet tweet = new Tweet();
                            tweet.setCoinId(coin.getId());
                            tweet.setTweetId(status.getId());
                            tweet.setUserId(status.getUser().getId());
                            tweet.setTime((int) (System.currentTimeMillis() / 1000));
                            tweet.setPrice(PriceUtils.getPrice(PriceUtils.getPricesFromExchange(), coin.getTicker()));
                            Interactions.addTweet(tweet);
                            break keywordloop;
                        }
                    }
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {

            }

            @Override
            public void onScrubGeo(long l, long l1) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }

            @Override
            public void onException(Exception e) {

            }
        });

        List<String> allKeywords = new ArrayList<>();
        for (Coin coin : coins) {
            allKeywords.addAll(new Gson().fromJson(coin.getKeywords(), ArrayList.class));
        }

        FilterQuery tweetFilterQuery = new FilterQuery(); // See
        System.out.println(String.valueOf(allKeywords));
        tweetFilterQuery.track(allKeywords.toArray(new String[0])); // OR on keywords

        twitterStream.filter(tweetFilterQuery);

    }
}
