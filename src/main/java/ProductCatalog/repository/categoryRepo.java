package ProductCatalog.repository;

import ProductCatalog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepo extends JpaRepository<Category, Long> {
}
