package ProductCatalog.controller;


import ProductCatalog.dto.ProductDto;
import ProductCatalog.models.Category;
import ProductCatalog.models.Product;
import ProductCatalog.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class StorageProductController {
    @Autowired
    private StorageProductService productService;

    @GetMapping("/test")
    public String test() {
        return "Controller is working";
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = productService.createProduct(from(productDto));
        return from(product);
    }
    public ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        //Add category proper details
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }

    public Product from(ProductDto productDto){
        Product prd = new Product();
        prd.setId(productDto.getId());
        prd.setName(productDto.getName());
        prd.setImageUrl(productDto.getImageUrl());
        prd.setPrice(productDto.getPrice());
        if(productDto.getCategory()!=null) {
            Category category = new Category();
            category.setName(productDto.getCategory());
            prd.setCategory(category);
        }
        prd.setDescription(productDto.getDescription());
        return prd;
    }
}
