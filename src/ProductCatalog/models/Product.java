package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String Description;
    private String imageUrl;
    private Double price;
    private Category category;
    private Double primeSaleDiscount;
}
