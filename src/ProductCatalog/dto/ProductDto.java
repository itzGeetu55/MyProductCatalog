package ProductCatalog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ProductCatalog.models.Category;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String Description;
    private String imageUrl;
    private Double price;
    private Category category;

}
