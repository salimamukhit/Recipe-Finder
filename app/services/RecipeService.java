package services;

import models.Ingredient;
import models.Recipe;

import javax.persistence.EntityManager;
import java.util.List;

public class RecipeService {
    public List<Recipe> getAllRecipes(EntityManager em) {
        List result = null;

        result = em.createQuery("select rc from Recipe rc order by rc.name").getResultList();

        return result;
    }

    public List<Recipe> getRecipeList(EntityManager em, String ingredients) {
        String queryStr = "select ig.recipe from Ingredient ig where ig.name in (" + ingredients + ")";

        List result = em.createQuery(queryStr).getResultList();

        return result;
    }

    public List<Ingredient> getAllIngredients(EntityManager em) {
        List result = null;

        result = em.createQuery("select distinct ig.name from Ingredient ig order by ig.name asc").getResultList();

        return result;
    }

    public List<Ingredient> getIngredients(EntityManager em, String recipeName) {
        List result = null;

        recipeName = recipeName.replaceAll("'", "''");

        result = em.createQuery("select rc.ingredients from Recipe rc where rc.name = '" + recipeName + "'").getResultList();

        return result;
    }
}
