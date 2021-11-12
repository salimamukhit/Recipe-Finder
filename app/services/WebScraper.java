package services;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that scrapes a single URL and retrieves recipe information
 */
public class WebScraper {
    private String pageURL;

    /**
     * Constructor for a Web Scraper
     * @param pageURL a URL of a recipe
     */
    public WebScraper(String pageURL) {
        this.pageURL = pageURL;
    }

    /**
     * Gets all recipe data from the page
     * @return a HashMap where recipe name is a key and array of ingredients is a value
     * @throws IOException if it couldn't open html page
     */
    public Recipe getRecipeInfo() throws IOException {
        Recipe newRecipe = new Recipe();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        try {
            HtmlPage page = webClient.getPage(this.pageURL);
            String recipeName = page.getTitleText();

            List<?> anchors = page.getByXPath("//input[@data-ingredient]");

            for (Object o : anchors) {
                HtmlAnchor anchor = (HtmlAnchor) o;
                String dataUnitQuantity = anchor.getAttribute("data-unit-quantity");
                String dataUnit = anchor.getAttribute("data-unit");
                String ingredient = anchor.getAttribute("data-ingredient");

                Ingredient newIngredient = new Ingredient(dataUnitQuantity, dataUnit, ingredient);
                ingredientList.add(newIngredient);
            }
            newRecipe.setRecipeURL(this.pageURL);
            newRecipe.setRecipeName(recipeName);
            newRecipe.setIngredients(ingredientList);

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();
        } catch (IOException e) {
            throw new IOException("Unable to scrape a page " + this.pageURL);
        }
        return newRecipe;
    }
}
