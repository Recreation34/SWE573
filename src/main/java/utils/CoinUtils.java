package utils;

import objects.Coin;
import objects.CoinTweetDetail;
import objects.Tweet;
import com.google.gson.Gson;
import database.Interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CoinUtils {

    private static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    static {
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                List<Coin> coins = Interactions.getCoins();
                for (Coin coin : coins) {
                    coin.setInfo(new Gson().toJson(analyzeTweets(coin)));
                    Interactions.updateCoin(coin);
                    Logger.info(coin.getName() + " updated");
                }
                Interactions.removeOldTweets();
                Logger.info("Removed old coins");
            }
        }, 0, 3, TimeUnit.MINUTES);
    }

    public static List<Coin> getCoins() {
        return Interactions.getCoins();
    }

    public static CoinTweetDetail analyzeTweets(Coin coin) {
        CoinTweetDetail coinTweetDetail = new CoinTweetDetail();
        coinTweetDetail.setTweetDataTimeInterval(600);
        List<Tweet> tweets = Interactions.getAllTweets(coin.getId());
        if (tweets.size() > 0) {
            coinTweetDetail.setTweetsCount(tweets.size());

            ArrayList<double[]> data = new ArrayList<>();
            int time = tweets.get(0).getTime();
            int count = 0;
            List<Integer> counts = new ArrayList<>();
            for (Tweet tweet : tweets) {
                if (tweet.getTime() - time > coinTweetDetail.getTweetDataTimeInterval()) {
                    time = tweet.getTime();
                    counts.add(count);
                    data.add(new double[]{time, count, tweet.getPrice()});
                    count = 0;
                } else {
                    count++;
                }
            }
            coinTweetDetail.setTweetData(data);
            coinTweetDetail.setCoinLastPrice(tweets.get(tweets.size()-1).getPrice());
        }
        return coinTweetDetail;
    }
}
