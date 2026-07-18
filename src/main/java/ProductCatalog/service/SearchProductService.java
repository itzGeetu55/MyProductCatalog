package ProductCatalog.service;

import ProductCatalog.models.Product;
import ProductCatalog.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SearchProductService implements ISearchProductService {
    @Autowired
    ProductRepo productRepo;
    public Page<Product> searchProduct(String query, int pageCount, int perPageCount){
        Page<Product> products = productRepo.findByNameContainingIgnoreCase(query, PageRequest.of(pageCount,perPageCount));
        return products;
    }
}
