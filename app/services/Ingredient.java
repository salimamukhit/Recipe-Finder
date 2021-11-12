package services;

public class Ingredient {
    private final String dataUnitQuantity;
    private final String dataUnit;
    private final String ingredient;

    public Ingredient(String dataUnitQuantity, String dataUnit, String ingredient) {
        this.dataUnitQuantity = dataUnitQuantity;
        this.dataUnit = dataUnit;
        this.ingredient = ingredient;
    }

    public String getDataUnitQuantity() {
        return dataUnitQuantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getDataUnit() {
        return dataUnit;
    }
}
