package ProductCatalog.controller;

import ProductCatalog.dto.SearchProductDto;
import ProductCatalog.models.Product;
import ProductCatalog.service.ISearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/search")
@RestController //restAPI
//@Controller - used when views are returned
public class SearchProductController {
    @Autowired
    ISearchProductService searchService;

//    @ResponseBody - used with @controller to tell not to return view but json
    @PostMapping
    public Page<Product> searchProduct(@RequestBody SearchProductDto searchDto){
        return searchService.searchProduct(searchDto.getQuery(),searchDto.getPageNumber(),searchDto.getPageCount());
    }

}
