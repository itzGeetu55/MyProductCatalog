package ProductCatalog.service;

import ProductCatalog.dto.ProductDto;
import ProductCatalog.models.*;
import ProductCatalog.repository.categoryRepo;
import ProductCatalog.repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class StorageProductService  {

    @Autowired
    productRepo prodRepo;

    @Autowired
    categoryRepo categoryRepo;

    //Null checks
    public Product createProduct(Product product){
        Category category = product.getCategory();

        if (category == null) {
            throw new IllegalArgumentException("Category is required");
        }

        if (category.getId() == null) {
            // New category
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        } else {
            Optional<Category> existingCategory =
                    categoryRepo.findById(category.getId());

            if (existingCategory.isPresent()) {
                product.setCategory(existingCategory.get());
            } else {
                Category savedCategory = categoryRepo.save(category);
                product.setCategory(savedCategory);
            }
        }

        return prodRepo.save(product);
    }

    public Product getProduct(Long id){
        Optional<Product> optionalProduct = prodRepo.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    //get all products

    //In progress
    public Product updateProduct(ProductDto changedProduct){
        return null;
    }

    //delete product

}
