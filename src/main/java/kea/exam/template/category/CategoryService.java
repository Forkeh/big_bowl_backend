package kea.exam.template.category;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<String> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .toList();
    }
}
