package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="vname")
    private String name;
    @OneToOne
    @JoinColumn(name = "vrecipe")
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        Ingredient ingredient = (Ingredient) o;
        return Objects.equals(id, ingredient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, recipe);
    }
}
