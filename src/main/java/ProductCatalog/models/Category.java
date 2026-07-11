package ProductCatalog.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "category")
public class Category extends BaseModel {
    private String Description;
    @ElementCollection
    private List<Product> products;
}
