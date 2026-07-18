package ProductCatalog.dto;

import ProductCatalog.models.SortType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class SearchProductDto {
    private String query;
    //private SortType sortType;
    private int pageNumber;
    private int pageCount;
}
