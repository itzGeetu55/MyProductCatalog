package ProductCatalog.controller;

import ProductCatalog.dto.FakeStoreProductDto;
import ProductCatalog.dto.ProductDto;
import ProductCatalog.models.Product;
import ProductCatalog.service.FakeStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ProductCatalog.service.IProductService;

@RestController
public class ProductController {
    public ProductController() {
        System.out.println(">>> ProductController loaded");
    }
    @Autowired
    private IProductService productService;

    //@GetMapping("/products/{ID}")-> REST endpoint
    //"/products/{ID}" is called a URI template or path template. ID is Path variable name (the placeholder name from the URI template)
    // fixed part of the URL path + variable part whose value comes from the request URL

    @GetMapping("/products/{ID}")
    public ProductDto getProductById(@PathVariable("ID") Long id){ //@PathVariable("ID") Long id -> Path variable binding with method parameter
        Product product = productService.getProductById(id);
        return from(product);
    }

    @PutMapping("/products/{id}")
    public void putProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        productService.put(id,from(productDto));
    }
    @PatchMapping("/products/{id}")
    public ProductDto patchProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        Product pr = productService.patch(id,from(productDto));
        return productDto;
    }

    public ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        return productDto;
    }

    public Product from(ProductDto p){
        Product prd = new Product();
        prd.setId(p.getId());
        prd.setName(p.getName());
        prd.setImageUrl(p.getImageUrl());
        prd.setPrice(p.getPrice());
        prd.setCategory(p.getCategory());
        prd.setDescription(p.getDescription());
        return prd;
    }
}
