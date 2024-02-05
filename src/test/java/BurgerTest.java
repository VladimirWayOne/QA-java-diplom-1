import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static constants.TestData.DELTA;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient cutlet;

    @Mock
    Ingredient chili;
    @Mock
    Ingredient sausage;

    @Mock
    Ingredient ketchup;

    Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(cutlet);
        burger.addIngredient(chili);
        burger.addIngredient(sausage);

        Mockito.when(bun.getPrice()).thenReturn(0.5f);
        Mockito.when(cutlet.getPrice()).thenReturn(10.1f);
        Mockito.when(chili.getPrice()).thenReturn(1.9f);
        Mockito.when(sausage.getPrice()).thenReturn(2.0f);

        Mockito.when(bun.getName()).thenReturn("Булка");
        Mockito.when(cutlet.getName()).thenReturn("Котлета из мраморной говядины");
        Mockito.when(chili.getName()).thenReturn("Сладкий чили");
        Mockito.when(sausage.getName()).thenReturn("Купат");

        Mockito.when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(chili.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sausage.getType()).thenReturn(IngredientType.FILLING);
    }

    @After
    public void tearDown() {
        burger.ingredients.clear();
        burger.bun = null;
    }

    //    setBuns
//    addIngredient
//    removeIngredient
//    moveIngredient
//    getPrice
//    getReceipt
    @Test
    public void setBunSetsBunNotNull() {
        burger.setBuns(bun);
        Assert.assertNotNull("Ожидалось наличие булочки", burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToList() {
        // Добавляем ещё один ингридиент к 3 имеющимся
        burger.addIngredient(ketchup);
        Assert.assertEquals("Ожидалось наличие 4 ингридиентов", 4, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesIngridient() {
        burger.removeIngredient(0);
        Assert.assertEquals("Ожидалось наличие 2 ингридиентов", 2, burger.ingredients.size());
    }

    @Test
    public void moveIngredientChangesIngridientIndex() {
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Ожидался индекс ингридиента cutlet равным 1", 1, burger.ingredients.indexOf(cutlet));
    }

    @Test
    public void getPriceReturnsSumOfIngredientsPrice() {
        float expectedPrice = bun.getPrice() * 2 + cutlet.getPrice() + sausage.getPrice() + chili.getPrice();
        burger.setBuns(bun);
        Assert.assertEquals("некорректная цена бургера", expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getReceiptReturnsAllIngredientsAndBurgerPrice() {
        burger.setBuns(bun);
        String expectedReceipt = String.format(
                "(==== %s ====)\r\n" +
                "= filling %s =\r\n" +
                "= sauce %s =\r\n" +
                "= filling %s =\r\n" +
                "(==== %s ====)\r\n" +
                "\r\n" +
                "Price: %f\r\n", bun.getName(), cutlet.getName(), chili.getName(), sausage.getName(), bun.getName(),bun.getPrice() * 2 + cutlet.getPrice() + sausage.getPrice() + chili.getPrice());
//        System.out.println(expectedReceipt+"\r\n"+burger.getReceipt());
        Assert.assertEquals("ожидался другой чек",expectedReceipt,burger.getReceipt());
    }
}
