package services;

import java.io.IOException;

public class Populator {

    private final static String url = "https://www.allrecipes.com/recipe/";

    public void populateRecipesDatabase() {
        // TODO retrieve the last recipe id from database
        int recipeId = 6663;
        while(true) {
            String pageUrl = url + recipeId;
            WebScraper webScraper = new WebScraper(pageUrl);
            try {
                Recipe newRecipe = webScraper.getRecipeInfo();
                // TODO add this hashMap and recipe URL to database
                recipeId++;
            } catch(IOException e) {
                // TODO record the last recipe
                /* SQL command:
                update t_last_recipe_id
                set nvalue = recipeId
                where id = 1
                 */
                break;
            }
        }
    }
}
