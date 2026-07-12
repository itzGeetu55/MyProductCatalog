package ProductCatalog.service;

import ProductCatalog.dto.ProductDto;
import ProductCatalog.models.*;
import ProductCatalog.repository.categoryRepo;
import ProductCatalog.repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
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
        //can cause duplicate categories as we are not comparing this category against repo data
        if (category.getId() == null) {
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        else {
            Optional<Category> existingCategory = categoryRepo.findById(category.getId());

            if (existingCategory.isPresent()) {
                product.setCategory(existingCategory.get());
            }
            else {
                Category savedCategory = categoryRepo.save(category);
                product.setCategory(savedCategory);
            }
        }

        return prodRepo.save(product);
    }

    public Product getProduct(Long id){
        System.out.println(id);
        Optional<Product> optionalProduct = prodRepo.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    //get all products
    public List<Product> getAllProducts(){
        List<Product> products = prodRepo.findAll();
        return products;
    }
    //In progress
    public Product updateProduct(Long id,Product productUpdate){
//        Product updatedProduct = prodRepo.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Product with id " + id + " does not exist"));

        Optional<Product> optionalProduct = prodRepo.findById(id);
        if(optionalProduct.isPresent()){
            //if product is present then update the product properties
            Product updatedProduct = optionalProduct.get();
            updatedProduct.setName(productUpdate.getName());
            updatedProduct.setDescription(productUpdate.getDescription());
            updatedProduct.setPrice(productUpdate.getPrice());
            updatedProduct.setImageUrl(productUpdate.getImageUrl());

            //if category is new and not saved then we need to save it first ---faulty
            Category category = productUpdate.getCategory(); //request category
            //updatedProduct.setCategory(productUpdate.getCategory());
            updatedProduct = prodRepo.save(updatedProduct);
            return updatedProduct;
        }
//        else{
//            //if product doesn't exist then create with those new properties
//            Product updatedProduct = optionalProduct.get();
//            updatedProduct.setName(productUpdate.getName());
//            updatedProduct.setDescription(productUpdate.getDescription());
//            updatedProduct.setPrice(productUpdate.getPrice());
//            updatedProduct.setImageUrl(productUpdate.getImageUrl());
//            updatedProduct.setCategory(productUpdate.getCategory());
//            updatedProduct = prodRepo.save(updatedProduct);
//            return updatedProduct;
//        }
        throw new RuntimeException("Product with id " + id + " does not exist");
    }

    //delete product
    public String deleteProduct(Long id){
        prodRepo.findById(id).orElseThrow(()->new RuntimeException("Product with this "+ id + " doesn't exist"));
        prodRepo.deleteById(id);
        return "Product with id " + id + " successfully deleted";
    }
}
