package kea.exam.template.config;

import kea.exam.template.activity.Activity;
import kea.exam.template.activity.ActivityRepository;
import kea.exam.template.category.Category;
import kea.exam.template.category.CategoryRepository;
import kea.exam.template.product.Product;
import kea.exam.template.product.ProductRepository;
import kea.exam.template.type.Type;
import kea.exam.template.type.TypeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InitData implements ApplicationRunner {

    private final List<Category> categories = new ArrayList<>();
    private final List<Type> types = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Activity> activities = new ArrayList<>();

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ActivityRepository activityRepository;

    public InitData(TypeRepository typeRepository, CategoryRepository categoryRepository, ProductRepository productRepository, ActivityRepository activityRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.activityRepository = activityRepository;
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
        createProducts();
        createActivities();
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

    private void createProducts() {
        products.add(new Product("Pepsi 33cl.", "https://www.maxgaming.dk/bilder/artiklar/21567.jpg?m=1652173726", 20, 100, categories.get(0)));
        products.add(new Product("Coca Cola 33cl.", "https://www.maxgaming.dk/bilder/artiklar/zoom/26704_1.jpg?m=1678105407", 20, 80, categories.get(0)));
        products.add(new Product("Fanta 33cl.", "https://www.maxgaming.dk/img/bilder/artiklar/26857.jpg?m=1679058529&w=720", 15, 60, categories.get(0)));
        products.add(new Product("Sprite 33cl.", "https://lekalass.fr/48-large_default/sprite-33cl.jpg", 18, 70, categories.get(0)));
        products.add(new Product("Kim's Barbeque Chips", "https://kims.dk/wp-content/uploads/2019/09/Sweet-N-Juicy-Barbecue-Chips-170g.png", 25, 30, categories.get(1)));
        products.add(new Product("Kim's Saltede Chips", "https://kims.dk/wp-content/uploads/2021/02/Chips-Havsalt-170g.png", 25, 30, categories.get(1)));
        products.add(new Product("Kim's Sour Cream Chips", "https://www.maxgaming.dk/bilder/artiklar/21567.jpg?m=1652173726", 25, 30, categories.get(1)));
        products.add(new Product("Tuborg Classic", "https://billigfadoel.dk/wp-content/uploads/2018/11/Tuborg-Classic-25-cl-glasflaske-30-stk-bestil-hos-Billigfadoel.jpg", 20, 120, categories.get(2)));
        products.add(new Product("Carlsberg", "https://billigfadoel.dk/wp-content/uploads/2018/11/Carlsberg-Pilsner-25-cl-glasflaske-30-stk-bestil-hos-Billigfadoel.jpg", 18, 100, categories.get(2)));
        products.add(new Product("Heineken", "https://www.campoluzenoteca.com/5826-img_ppage/heineken-33cl-ep-caja-24.jpg", 25, 150, categories.get(2)));
        products.add(new Product("Tom og Jerry Legetøj", "https://m.media-amazon.com/images/I/61dNq9wOdIL.jpg", 100, 10, categories.get(3)));

        productRepository.saveAll(products);

    }

    private void createActivities() {
        activities.add(new Activity("Bowling lane 1", true, types.get(0)));
        activities.add(new Activity("Bowling lane 2", true, types.get(0)));
        activities.add(new Activity("Bowling lane 3", true, types.get(0)));
        activities.add(new Activity("Bowling lane 4", true, types.get(0)));
        activities.add(new Activity("Bowling lane 5", true, types.get(0)));
        activities.add(new Activity("Bowling lane 6", false, types.get(0)));
        activities.add(new Activity("Bowling lane 7", false, types.get(0)));
        activities.add(new Activity("Bowling lane 8", true, types.get(0)));
        activities.add(new Activity("Bowling lane 9", true, types.get(0)));
        activities.add(new Activity("Bowling lane 10", true, types.get(0)));
        activities.add(new Activity("Bowling lane 11", false, types.get(0)));
        activities.add(new Activity("Bowling lane 12", true, types.get(0)));
        activities.add(new Activity("Bowling lane 13", true, types.get(0)));
        activities.add(new Activity("Bowling lane 14", false, types.get(0)));
        activities.add(new Activity("Bowling lane 15", true, types.get(0)));
        activities.add(new Activity("Bowling lane 16", true, types.get(0)));
        activities.add(new Activity("Bowling lane 17", true, types.get(0)));
        activities.add(new Activity("Bowling lane 18", true, types.get(0)));
        activities.add(new Activity("Bowling lane 19", true, types.get(0)));
        activities.add(new Activity("Bowling lane 20", true, types.get(0)));
        activities.add(new Activity("Bowling lane 21", true, types.get(1)));
        activities.add(new Activity("Bowling lane 22", true, types.get(1)));
        activities.add(new Activity("Bowling lane 23", true, types.get(1)));
        activities.add(new Activity("Bowling lane 24", false, types.get(1)));

        activities.add(new Activity("Air Hockey 1", true, types.get(2)));
        activities.add(new Activity("Air Hockey 2", true, types.get(2)));
        activities.add(new Activity("Air Hockey 3", false, types.get(2)));
        activities.add(new Activity("Air Hockey 4", true, types.get(2)));

        activities.add(new Activity("Dining 1", true, types.get(3)));
        activities.add(new Activity("Dining 2", true, types.get(3)));
        activities.add(new Activity("Dining 3", false, types.get(3)));
        activities.add(new Activity("Dining 4", true, types.get(3)));
        activities.add(new Activity("Dining 5", true, types.get(3)));
        activities.add(new Activity("Dining 6", true, types.get(3)));

        activityRepository.saveAll(activities);
    }
}
