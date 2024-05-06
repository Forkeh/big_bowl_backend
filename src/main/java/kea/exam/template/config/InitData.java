package kea.exam.template.config;

import kea.exam.template.category.Category;
import kea.exam.template.category.CategoryRepository;
import kea.exam.template.type.Type;
import kea.exam.template.type.TypeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitData implements ApplicationRunner {

    private final Set<Type> types = new HashSet<>();
    private final Set<Category> categories = new HashSet<>();

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;

    public InitData(TypeRepository typeRepository, CategoryRepository categoryRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Initializing data...");

        init();
    }

    private void init() {
        System.out.println("Creating items in database...");
        createType();
        createCategory();
    }

    private void createCategory() {
        categories.add(new Category("Drikkevarer"));
        categories.add(new Category("Snacks"));
        categories.add(new Category("Alkohol"));
        categories.add(new Category("Andet"));

        categoryRepository.saveAll(categories);
    }


    private void createType() {
        types.add(new Type("Bowling Standard", 200));
        types.add(new Type("Bowling Junior", 100));
        types.add(new Type("Air Hockey", 150));
        types.add(new Type("Spisning", 50));

        typeRepository.saveAll(types);
    }
}
