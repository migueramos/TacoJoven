package tacos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Taco {

    @Id
    private String id;
    private LocalDateTime createdAt;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @ManyToMany(targetEntity = Ingredient.class)
    @NotNull(message = "You must choose at least 1 ingredient")
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt(){
        this.createdAt = LocalDateTime.now();
    }
}
