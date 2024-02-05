import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static constants.TestData.DELTA;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType ingredientType;
    private final String name;
    private final Float price;

    private Ingredient ingredient;

    public IngredientTest(IngredientType ingredientType, String name, Float price) {
        this.ingredientType = ingredientType;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getIngredients() {
        return new Object[][] {
                {IngredientType.SAUCE, "Кетчуп", 10.10f},
                {IngredientType.FILLING, "Котлета", 20.20f}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(ingredientType, name, price);
    }

    @Test
    public void getNameReturnIngredientName() {
        Assert.assertEquals("Некорректное название ингридиента", name, ingredient.getName());
    }
    @Test
    public void getPriceReturnIngredientPrice() {
        Assert.assertEquals("Некорректная цена ингридиента", price, ingredient.getPrice(), DELTA);
    }

    @Test
    public void getTypeReturnIngredientType() {
        Assert.assertEquals("Некорректный тип ингридиента", ingredientType, ingredient.getType());
    }
}
