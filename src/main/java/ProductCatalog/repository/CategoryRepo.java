package ProductCatalog.repository;

import ProductCatalog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
//    @Query("select * from category")
//    List<Category> getAll();Category
}
