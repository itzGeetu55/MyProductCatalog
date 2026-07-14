package ProductCatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product")
public class Product extends BaseModel {
    private String Description; //same
    private String imageUrl;
    private Double price;
    @ManyToOne
    private Category category;
    private Double primeSaleDiscount;
}
