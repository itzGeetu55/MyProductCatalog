package ProductCatalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchProductDto {
    private String query;
    private int pageNumber;
    private int pageCount;
    List<SortParam> sortParams = new ArrayList<>();
}
