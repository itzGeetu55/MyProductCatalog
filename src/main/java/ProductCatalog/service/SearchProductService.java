package ProductCatalog.service;

import ProductCatalog.models.Product;
import ProductCatalog.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements ISearchProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> searchProduct(String query){
        List<Product> products = productRepo.findByNameContainingIgnoreCase(query);
        return products;
    }
}
