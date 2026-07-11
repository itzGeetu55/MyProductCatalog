package ProductCatalog.service;

import ProductCatalog.dto.FakeStoreProductDto;
import ProductCatalog.models.Category;
import ProductCatalog.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    //In this service, I make a call to fakestoreAPI to fetch, create, update and delete the data.

    //Using RESTTEMPLATE FOR USING the defined FUNCTIONS TO MAKE CALLS.
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Product postProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", from(product) ,FakeStoreProductDto.class);
        return from(fakeStoreProductDto);
    }

    @Override
    public Product getProductById(Long id){
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        return from(fakeStoreProductDto);
    }

    public List<Product> getAllProducts(){
        List<Product> prds = new ArrayList<>();

        FakeStoreProductDto[] FksProducts = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        for(FakeStoreProductDto fk: FksProducts){
            prds.add(from(fk));
        }
        return prds;
    }

    public void put(Long id, Product product){
        restTemplate.put("https://fakestoreapi.com/products/{id}", from(product), id);
    }
    public Product patch(Long id, Product product){
       FakeStoreProductDto fk = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", from(product),FakeStoreProductDto.class, id);
        return  from(fk);
    }
    public void deleteProduct(Long id){
        restTemplate.delete("https://fakestoreapi.com/products/{id}", id);
    }

    public Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    public FakeStoreProductDto from(Product p){
        FakeStoreProductDto fk = new FakeStoreProductDto();
        fk.setId(p.getId());
        fk.setTitle(p.getName());
        fk.setImage(p.getImageUrl());
        fk.setCategory(p.getCategory().getName());
        fk.setPrice(p.getPrice());
        fk.setDescription(p.getDescription());
        return fk;
    }
}
