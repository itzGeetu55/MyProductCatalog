package ProductCatalog.service;

import ProductCatalog.models.Product;

import java.util.List;

public interface ISearchProductService {
    List<Product> searchProduct(String query);
}
