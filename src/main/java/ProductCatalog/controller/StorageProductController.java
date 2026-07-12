package ProductCatalog.controller;

import ProductCatalog.dto.CategoryDto;
import ProductCatalog.dto.ProductDto;
import ProductCatalog.models.Category;
import ProductCatalog.models.Product;
import ProductCatalog.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        return from(product);
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> allProducts = new ArrayList<>();
        for(Product product: products){
            allProducts.add(from(product));
        }
        return allProducts;
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id,@RequestBody ProductDto productUpdate){
        Product product = productService.updateProduct(id, from(productUpdate));
        return from(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        String message = productService.deleteProduct(id);
        return message;
    }

    public ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        //Add category proper details
        if(product.getCategory()!= null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
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
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());
            prd.setCategory(category);
        }
        prd.setDescription(productDto.getDescription());
        return prd;
    }

}
