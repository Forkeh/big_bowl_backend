package kea.exam.template.product;

import kea.exam.template.category.Category;
import kea.exam.template.category.CategoryRepository;
import kea.exam.template.exceptions.BadRequestException;
import kea.exam.template.product.dto.ProductRequestDTO;
import kea.exam.template.product.dto.ProductResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProductThatAlreadyExists() {
        // Arrange
        var requestDTO = new ProductRequestDTO(
                "Fries",
                "www.image.com",
                2,
                2,
                "Andet"
        );

        Mockito.when(productRepository.existsByNameContainingIgnoreCase(requestDTO.name())).thenReturn(true);

        // Act and Assert
        assertThrows(BadRequestException.class, () -> productService.createProduct(requestDTO));
    }

    @Test
    void createProductThatDoesNotExist() {
        // Arrange
        var requestDTO = new ProductRequestDTO(
                "Fries",
                "www.image.com",
                2,
                2,
                "Andet"
        );

        var product = new Product(
                1L,
                requestDTO.name(),
                requestDTO.image(),
                requestDTO.price(),
                requestDTO.stock(),
                new Category("Andet")
        );

        Mockito.when(productRepository.existsByNameContainingIgnoreCase(requestDTO.name())).thenReturn(false);
        Mockito.when(categoryRepository.findFirstByNameContainsIgnoreCase(requestDTO.category())).thenReturn(Optional.of(new Category("Andet")));
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        // Act
        ProductResponseDTO result = productService.createProduct(requestDTO);

        // Assert
        assertEquals(result.id(), product.getId());
        assertEquals(result.image(), product.getImageURL());
        assertEquals(result.price(), product.getPrice());
        assertEquals(result.stock(), product.getStock());

    }

}