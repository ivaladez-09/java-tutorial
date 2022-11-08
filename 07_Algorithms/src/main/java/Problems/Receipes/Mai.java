package Problems.Receipes;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class Mai {
    public static void main(String[] args) {

    }
}

class Recipe {
    int price;
    Portions[] portions;
}

class Portions {
    Ingredient ingredient;
    Unit unit;
    Integer quantity;
}

class Ingredient {
    String name;
}

class Unit {
    String name;
}

class Sale {
    Date date;
    Recipe [] recipes;
}

class Api {
    Integer soldByDate(Date date) {
        Sale [] sales = {new Sale()};
        var filteredSales = Stream.of(sales)
                .filter(sale -> sale.date.equals(date))
                .map(sale -> Stream.of(sale.recipes)
                        .map(recipe -> recipe.price)
                        .toList())
                .toList();
        return 0;
    }
}

/*
*       Recipe                            Recipe_Ingredients
*   - id                                - idRecipe
*   - idRecipeIngredientQuantity        - idIngredient
*   - price                             - idMeasQty
*                                       - idMeasUnit
*
*       Ingredient      Measurement_qty     Measurement_unit
*   - id                - id                - id
*   - name              - amount            - description
*
*       Sale                Recipe_Sale
*   - Id                    - id
*   - Date                  - IdRecipe
*   - recipeSales           - idSale
* */
