package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.Ingredient;
import models.Recipe;
import play.libs.Json;

import javax.persistence.EntityManager;
import java.util.List;

public class RecipeService {
    public List<Recipe> getAllRecipes(EntityManager em) {
        List<Recipe> result = null;

        result = em.createQuery("select rc from Recipe rc order by rc.name").getResultList();

        return result;
    }

    public List<Recipe> getRecipeList(EntityManager em, String ingredients) {
        String queryStr = "select ig.recipeName from Ingredient ig where ig.name in (" + ingredients + ")";
        List<String> recipeNames = em.createQuery(queryStr).getResultList();

        // Make the list of recipe names into SQL readable array
        StringBuilder sb = new StringBuilder();
        for(String str : recipeNames) {
            str = str.replaceAll("'", "''"); // SQL ' escape character
            str = "'" + str + "'"; // Wrapping it between ''
            sb.append(str).append(","); // Appending to a string builder
        }

        // Strip out the last comma
        String recipeNamesStr = sb.substring(0, sb.toString().length() - 1);

        // Return all recipes with those ingredients
        List<Recipe> result = null;

        result = em.createQuery("select rc from Recipe rc where rc.name in (" + recipeNamesStr + ")").getResultList();

        return result;
    }

    public List<Ingredient> getAllIngredients(EntityManager em) {
        List<Ingredient> result = null;

        result = em.createQuery("select distinct ig.name from Ingredient ig order by ig.name asc").getResultList();

        return result;
    }


}
