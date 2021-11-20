package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.Ingredient;
import models.Recipe;
import play.libs.Json;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RecipeService {
    public List<Recipe> getAllRecipes(EntityManager em) {
        List<Recipe> result = null;

        result = em.createQuery("select rc from Recipe rc order by rc.name").getResultList();

        return result;
    }

    public List<Recipe> getRecipeList(EntityManager em, String ingredients) {
        String queryStr = "select ig.recipe from Ingredient ig where ig.name in (" + ingredients + ")";

        List<Recipe> result = em.createQuery(queryStr).getResultList();

        return result;
    }

    public List<Ingredient> getAllIngredients(EntityManager em) {
        List<Ingredient> result = null;

        result = em.createQuery("select distinct ig.name from Ingredient ig order by ig.name asc").getResultList();

        return result;
    }


}
