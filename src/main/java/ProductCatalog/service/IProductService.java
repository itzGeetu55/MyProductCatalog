package ProductCatalog.service;

import ProductCatalog.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void put(Long id,Product product);
    Product patch(Long id, Product product);
    Product postProduct(Product product);
    void deleteProduct(Long id);
}