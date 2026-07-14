package ProductCatalog.controller;

import ProductCatalog.dto.SearchProductDto;
import ProductCatalog.models.Product;
import ProductCatalog.service.ISearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/search")
@Controller
public class SearchProductController {
    @Autowired
    ISearchProductService searchService;

    @PostMapping
    public List<Product> searchProduct(@RequestBody SearchProductDto searchProduct){
        return searchService.searchProduct(searchProduct.getQuery());
    }

}
