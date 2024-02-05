import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static constants.TestData.*;

public class BunTest {

    private Bun bun;
    @Before
    public void setUp(){
        bun = new Bun(BUN_NAME, BUN_PRICE);
    }
    @Test
    public void getNameReturnBunName(){
        Assert.assertEquals("Некорректное имя", BUN_NAME, bun.getName());
    }

    @Test
    public void getPriceReturnBunPrice(){
        Assert.assertEquals("Некорректная цена", BUN_PRICE, bun.getPrice(), DELTA);
    }
}
