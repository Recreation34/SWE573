package objects;

public class TweetCount {

    private int duration;
    private long tweetCountsNow;
    private long tweetCountsBefore;
    private double tweetChange;
    private double firstPrice;
    private double lastPrice;
    private double priceChange;

    public void calculateTweetChange() {
        tweetChange = ((tweetCountsNow - tweetCountsBefore) / (double) tweetCountsBefore) * 100;
    }

    public void calculatePriceChange() {
        priceChange = ((lastPrice - firstPrice) / (double) firstPrice) * 100;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getTweetCountsNow() {
        return tweetCountsNow;
    }

    public void setTweetCountsNow(long tweetCountsNow) {
        this.tweetCountsNow = tweetCountsNow;
    }

    public long getTweetCountsBefore() {
        return tweetCountsBefore;
    }

    public void setTweetCountsBefore(long tweetCountsBefore) {
        this.tweetCountsBefore = tweetCountsBefore;
    }

    public double getTweetChange() {
        return tweetChange;
    }

    public void setTweetChange(double tweetChange) {
        this.tweetChange = tweetChange;
    }

    public double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }
}
