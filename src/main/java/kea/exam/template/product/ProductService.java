package kea.exam.template.product;

import kea.exam.template.category.Category;
import kea.exam.template.category.CategoryRepository;
import kea.exam.template.exceptions.EntityNotFoundException;
import kea.exam.template.product.dto.ProductRequestDTO;
import kea.exam.template.product.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return toDTO(productRepository.save(toEntity(productRequestDTO)));
    }

    private ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getImageURL(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
                        .getName()
        );
    }

    private Product toEntity(ProductRequestDTO productRequestDTO) {
        Category categoryInDB = categoryRepository.findFirstByNameContainsIgnoreCase(productRequestDTO.category())
                .orElseThrow(() -> new EntityNotFoundException("Category", productRequestDTO.category()));

        return new Product(
                productRequestDTO.name(),
                productRequestDTO.image(),
                productRequestDTO.price(),
                productRequestDTO.stock(),
                categoryInDB
        );
    }


}
