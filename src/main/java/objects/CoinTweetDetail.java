package objects;

import java.util.ArrayList;

public class CoinTweetDetail {

    private int tweetsCount;
    private double coinLastPrice;
    private int tweetDataTimeInterval;
    private ArrayList<double[]> tweetData = new ArrayList<>();


    public int getTweetsCount() {
        return tweetsCount;
    }

    public void setTweetsCount(int tweetsCount) {
        this.tweetsCount = tweetsCount;
    }

    public ArrayList<double[]> getTweetData() {
        return tweetData;
    }

    public void setTweetData(ArrayList<double[]> tweetData) {
        this.tweetData = tweetData;
    }

    public int getTweetDataTimeInterval() {
        return tweetDataTimeInterval;
    }

    public void setTweetDataTimeInterval(int tweetDataTimeInterval) {
        this.tweetDataTimeInterval = tweetDataTimeInterval;
    }

    public double getCoinLastPrice() {
        return coinLastPrice;
    }

    public void setCoinLastPrice(double coinLastPrice) {
        this.coinLastPrice = coinLastPrice;
    }
}
