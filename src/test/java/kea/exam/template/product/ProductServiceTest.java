package kea.exam.template.product;

import kea.exam.template.category.Category;
import kea.exam.template.category.CategoryRepository;
import kea.exam.template.exceptions.BadRequestException;
import kea.exam.template.exceptions.EntityNotFoundException;
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

        Mockito.when(productRepository.existsByNameContainingIgnoreCase(requestDTO.name()))
                .thenReturn(true);

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

        var productInDB = new Product(
                1L,
                requestDTO.name(),
                requestDTO.image(),
                requestDTO.price(),
                requestDTO.stock(),
                new Category("Andet")
        );

        Mockito.when(productRepository.existsByNameContainingIgnoreCase(requestDTO.name()))
                .thenReturn(false);
        Mockito.when(categoryRepository.findFirstByNameContainsIgnoreCase(requestDTO.category()))
                .thenReturn(Optional.of(new Category("Andet")));
        Mockito.when(productRepository.save(Mockito.any()))
                .thenReturn(productInDB);

        // Act
        ProductResponseDTO result = productService.createProduct(requestDTO);

        // Assert
        assertEquals(result.id(), productInDB.getId());
        assertEquals(result.image(), productInDB.getImageURL());
        assertEquals(result.price(), productInDB.getPrice());
        assertEquals(result.stock(), productInDB.getStock());

    }

    @Test
    void updateProductThatDoesNotExit() {
        // Arrange
        Long id = 10L;

        var requestDTO = new ProductRequestDTO(
                "Fries",
                "www.image.com",
                2,
                2,
                "Andet"
        );

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(id, requestDTO));
    }

    @Test
    void updateProductThatExists() {
        // Arrange
        Long id = 10L;

        var requestDTO = new ProductRequestDTO(
                "Fries",
                "www.image.com",
                2,
                2,
                "Andet"
        );

        var productInDB = new Product(
                id,
                "Chips",
                "www.face.com",
                4,
                4,
                new Category("Snacks")
        );

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.of(productInDB));

        Mockito.when(categoryRepository.findFirstByNameContainsIgnoreCase(requestDTO.category()))
                .thenReturn(Optional.of(new Category("Andet")));

        Mockito.when(productRepository.save(Mockito.any()))
                .thenReturn(productInDB);

        // Act
        ProductResponseDTO result = productService.updateProduct(id, requestDTO);

        // Assert
        assertEquals(requestDTO.name(), result.name());
        assertEquals(requestDTO.image(), result.image());
        assertEquals(requestDTO.price(), result.price());
        assertEquals(requestDTO.stock(), result.stock());
        assertEquals(requestDTO.category(), result.category());
    }

    @Test
    void updateProductThatExistsButCategoryDoesNotExist() {
        // Arrange
        Long id = 10L;

        var requestDTO = new ProductRequestDTO(
                "Fries",
                "www.image.com",
                2,
                2,
                "Andet"
        );

        var productInDB = new Product(
                id,
                "Chips",
                "www.face.com",
                4,
                4,
                new Category("Snacks")
        );

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.of(productInDB));

        Mockito.when(categoryRepository.findFirstByNameContainsIgnoreCase(requestDTO.category()))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(id, requestDTO));

    }

}