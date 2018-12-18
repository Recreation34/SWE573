import org.junit.Before;
import org.junit.Test;
import utils.PriceUtils;
import utils.Utils;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

public class PriceUtilsTest {

    String json;

    @Before
    public void setUp() throws Exception {
        json = Utils.readFile("C:\\Users\\fffppp\\IdeaProjects\\TweetFetcher\\src\\test\\java\\testPriceJson.json", Charset.defaultCharset());
    }

    @Test
    public void testGetPrice() {
        double btc_price = PriceUtils.getPrice(json, "BTC");
        assertEquals(btc_price, 3507.41, 0.01);
    }
}
