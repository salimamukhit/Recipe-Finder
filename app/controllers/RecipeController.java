package controllers;

import models.Ingredient;
import models.Recipe;
import play.db.jpa.JPAApi;
import play.mvc.Result;
import play.libs.Json;
import services.RecipeService;

import javax.inject.Inject;

import java.util.List;

import static play.mvc.Results.ok;

public class RecipeController {

    @Inject
    private RecipeService recipeService;

    @Inject
    private JPAApi jpaApi;

    public Result getAllRecipes() {
        return jpaApi.withTransaction(em -> {
            List<Recipe> result = null;
            result = recipeService.getAllRecipes(em);
            return ok(Json.toJson(result));
        });
    }

    public Result getRecipeList(String ingredients) {
        return jpaApi.withTransaction(em -> {
            List<Recipe> result = null;

            // Prepare a string for SQL
            String[] query = ingredients.split(",");
            String queryStr = "";
            for (String str : query) {
                str = str.replaceAll("'", "''"); // An escape character for quotes
                String s = "'" + str + "'" + ",";
                queryStr += s;
            }

            queryStr = queryStr.substring(0, queryStr.length() - 1);
            result = recipeService.getRecipeList(em, queryStr);
            return ok(Json.toJson(result));
        });
    }

    public Result getAllIngredients() {
        return jpaApi.withTransaction(em -> {
            List<Ingredient> result = null;
            result = recipeService.getAllIngredients(em);
            return ok(Json.toJson(result));
        });
    }

    public Result getIngredients(String recipeName) {
        return jpaApi.withTransaction(em -> {
            List<Ingredient> result = null;
            result = recipeService.getIngredients(em, recipeName);
            return ok(Json.toJson(result));
        });
    }
}
