package kea.exam.template.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameContainingIgnoreCase(String name);

    Page<Product> findAllByCategoryNameContains(Pageable pageable, String categoryName);
}
