package objects;

import com.google.gson.annotations.Expose;

public class Coin {

    @Expose
    private int id;
    private String name;
    private String ticker;
    private String keywords;
    private String info;
    private int status = 0;

    public Coin() {

    }

    public Coin(Object object) {
        Coin coin = (Coin) object;
        this.id = coin.id;
        this.name = coin.name;
        this.keywords = coin.keywords;
        this.status = coin.status;
        this.ticker = coin.ticker;
        this.info = coin.info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
