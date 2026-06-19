package ProductCatalog.service;

import ProductCatalog.dto.ProductDto;
import ProductCatalog.models.Product;

public interface IProductService {
    Product getProductById(Long id);
    void put(Long id,Product product);
    Product patch(Long id, Product product);
}