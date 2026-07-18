package ProductCatalog.service;

import ProductCatalog.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchProductService {
    Page<Product> searchProduct(String query, int pageCount, int perPageCount);
}
