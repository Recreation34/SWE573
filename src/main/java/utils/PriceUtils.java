package utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class PriceUtils {

    public static final String EXCHANGE_URL = "https://api.hitbtc.com/api/2/public/ticker";
    private static String result;
    private static int lastUpdate;

    public static String getPricesFromExchange() {
        int now = (int) (System.currentTimeMillis() / 1000);
        if (now - lastUpdate > 10) {
            result = RequestUtils.getResult(EXCHANGE_URL);
            lastUpdate = now;
            System.out.println("prices updated!");
        }
        return result;
    }

    public static double getPrice(String json, String symbol) {
        symbol = symbol + "USD";
        JSONArray jsonArray = new JSONArray(json);
        if (jsonArray.length() > 0) {
            for (Object object : jsonArray) {
                JSONObject coinJson = (JSONObject) object;
                if (symbol.equals(coinJson.getString("symbol"))) {
                    return coinJson.getDouble("last");
                }
            }
        }
        return 0.0;
    }
}
