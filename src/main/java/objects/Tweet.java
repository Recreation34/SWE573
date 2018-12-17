package objects;

public class Tweet {

    private long tweetId;
    private long userId;
    private int coinId;
    private double price;
    private int time;

    public Tweet() {
    }

    public Tweet(Object object) {
        Tweet tweet = (Tweet) object;
        this.tweetId = tweet.tweetId;
        this.userId = tweet.userId;
        this.coinId = tweet.coinId;
        this.price = tweet.price;
        this.time = tweet.time;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
