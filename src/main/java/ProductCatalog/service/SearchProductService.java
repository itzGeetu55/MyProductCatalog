package ProductCatalog.service;

import ProductCatalog.dto.SortParam;
import ProductCatalog.dto.SortType;
import ProductCatalog.models.Product;
import ProductCatalog.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements ISearchProductService {
    @Autowired
    ProductRepo productRepo;
    public Page<Product> searchProduct(String query, int pageCount, int perPageCount, List<SortParam> sortParams){
        //Sort sort = Sort.by("id").descending();

        Sort sort = null;
        if (!sortParams.isEmpty()){
            if(sortParams.get(0).getSortType() == SortType.desc)
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
            else
                sort = Sort.by(sortParams.get(0).getParamName());

            for(int i=1; i<sortParams.size();i++){
                Sort incSort = null;
                if(sortParams.get(i).getSortType() == SortType.desc)
                    incSort = Sort.by(sortParams.get(i).getParamName()).descending();
                else
                    incSort = Sort.by(sortParams.get(i).getParamName());
                sort = sort.and(incSort);
            }
        }
        if(sort != null)
            return productRepo.findByNameContainingIgnoreCase(query, PageRequest.of(pageCount,perPageCount, sort));
        else
            return productRepo.findByNameContainingIgnoreCase(query, PageRequest.of(pageCount,perPageCount));
    }
}
