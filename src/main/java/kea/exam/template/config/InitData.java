package kea.exam.template.config;

import kea.exam.template.activity.Activity;
import kea.exam.template.activity.ActivityRepository;
import kea.exam.template.booking.Booking;
import kea.exam.template.booking.BookingRepository;
import kea.exam.template.booking_product.BookingProduct;
import kea.exam.template.booking_product.BookingProductKey;
import kea.exam.template.booking_product.BookingProductRepository;
import kea.exam.template.category.Category;
import kea.exam.template.category.CategoryRepository;
import kea.exam.template.participant.Participant;
import kea.exam.template.participant.ParticipantRepository;
import kea.exam.template.product.Product;
import kea.exam.template.product.ProductRepository;
import kea.exam.template.type.Type;
import kea.exam.template.type.TypeRepository;
import kea.exam.template.user.User;
import kea.exam.template.user.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InitData implements ApplicationRunner {

    private final List<Category> categories = new ArrayList<>();
    private final List<Type> types = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Activity> activities = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Participant> participants = new ArrayList<>();
    List<BookingProduct> bookingProducts = new ArrayList<>();

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ParticipantRepository participantRepository;
    private final BookingProductRepository bookingProductRepository;


    public InitData(TypeRepository typeRepository, CategoryRepository categoryRepository, ProductRepository productRepository, ActivityRepository activityRepository,
                    UserRepository userRepository, BookingRepository bookingRepository, ParticipantRepository participantRepository,
                    BookingProductRepository bookingProductRepository) {
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.participantRepository = participantRepository;
        this.bookingProductRepository = bookingProductRepository;
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
        createUsers();
        createParticipants();
        createBookings();
        createBookingProducts();
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
        products.add(new Product("Pepsi 33cl.", "https://i.pinimg.com/originals/3d/c5/4e/3dc54e8f5df2eaa53ca93758d080b2f0.png", 20, 100, categories.get(0)));
        products.add(new Product("Coca Cola 33cl.", "https://i.pinimg.com/originals/43/df/a2/43dfa2c7155ed3ed4f0ccdc490e0ca84.png", 20, 80, categories.get(0)));
        products.add(new Product("Fanta 33cl.", "https://static.wixstatic.com/media/40ccf3_3632a83f249f4859bf0343de0d1c7999.png/v1/fill/w_360,h_876,al_c,lg_1,q_85,enc_auto/40ccf3_3632a83f249f4859bf0343de0d1c7999.png", 15, 60, categories.get(0)));
        products.add(new Product("Sprite 33cl.", "https://i.pinimg.com/originals/91/be/8f/91be8f7fb628fe336f045049097d3642.png", 18, 70, categories.get(0)));
        products.add(new Product("Kim's Barbeque Chips", "https://kims.dk/wp-content/uploads/2019/09/Sweet-N-Juicy-Barbecue-Chips-170g.png", 25, 30, categories.get(1)));
        products.add(new Product("Kim's Saltede Chips", "https://kims.dk/wp-content/uploads/2021/02/Chips-Havsalt-170g.png", 25, 30, categories.get(1)));
        products.add(new Product("Kim's Sour Cream Chips", "https://kims.dk/wp-content/uploads/2020/10/Sour-Cream-Onion-Chips-170g.png.webp", 25, 30, categories.get(1)));
        products.add(new Product("Tuborg Classic", "https://fadnord.dk/wp-content/uploads/sites/3/2019/01/64336-1.png", 20, 120, categories.get(2)));
        products.add(new Product("Carlsberg", "https://bevco.b-cdn.net/media/5f/11/e5/1710251699/Carlsberg%20Pilsner%20Profil%2033%20cl.png?height=560&quality=99", 18, 100, categories.get(2)));
        products.add(new Product("Heineken", "https://www.sp.com.pg/wp-content/uploads/2021/02/Heineken_Bottle-1.png", 25, 150, categories.get(2)));
        products.add(new Product("Bowling Sæt Legetøj", "https://purepng.com/public/uploads/thumbnail/kids-toys-vd2.png", 100, 10, categories.get(3)));

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


    private void createUsers() {
        users.add(new User("ghiodgerihehrgei45", "Jens", "Olesen"));
        users.add(new User("dkfj3948dfkjgk3948", "Emily", "Johnson"));
        users.add(new User("sdkfhe4k4jrjwofjer", "Michael", "Smith"));
        users.add(new User("weorivweoirvn23423", "Sophia", "Brown"));
        users.add(new User("sdkfhskfjwef3243js", "Liam", "Williams"));
        users.add(new User("weiruvneworvn2342v", "Olivia", "Jones"));
        users.add(new User("sdkjfhsdfjhsd34234", "Noah", "Garcia"));
        users.add(new User("woeirvnweornv32423", "Isabella", "Martinez"));
        users.add(new User("sdkjfhsdfjhsd34235", "Lucas", "Rodriguez"));
        users.add(new User("weorivnoweirnv2345", "Mia", "Davis"));
        users.add(new User("sdkfjhsdkjfh234234", "Mason", "Lopez"));
        users.add(new User("ghksdh23j4kjsd2j34k", "Ava", "Taylor"));
        users.add(new User("sjdhf8j34hfskdjf983", "Ethan", "Hernandez"));
        users.add(new User("kjsdfh3k4j5h34j5h3k", "Charlotte", "Moore"));
        users.add(new User("sdkjfh34kj5h43k5h34", "James", "Martin"));
        users.add(new User("sdkjfhsdkfjhsd3434j", "Amelia", "Lee"));
        users.add(new User("sdfjkhw4kj35hk35hk3", "Benjamin", "Perez"));
        users.add(new User("w4kjh35hk35h34k5h34", "Harper", "Thompson"));
        users.add(new User("j4k35h34k5h3k4h5k34", "Elijah", "White"));
        users.add(new User("kj34h5kj345hk3h4k5h", "Evelyn", "Harris"));
        users.add(new User("sdkjfhsdkfjwejf3483", "Alexander", "Clark"));
        users.add(new User("user_2g8I0Dw3Bv22Ovm6Pm1cDxtWsU7", "Brian", "Blume"));
        users.add(new User("user_2g8ESKm09FPKTgRmajrfw2QQ617", "Ali", "Amier"));

        userRepository.saveAll(users);
    }

    private void createParticipants() {
        participants.add(new Participant("James"));
        participants.add(new Participant("Olivia"));
        participants.add(new Participant("Liam"));
        participants.add(new Participant("Emma"));
        participants.add(new Participant("Noah"));
        participants.add(new Participant("Ava"));
        participants.add(new Participant("Sophia"));
        participants.add(new Participant("William"));
        participants.add(new Participant("Isabella"));
        participants.add(new Participant("Lucas"));

        participantRepository.saveAll(participants);
    }

    private void createBookings() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDateTime.now()
                .plusDays(1);
        LocalDateTime dayAfterTomorrow = LocalDateTime.now()
                .plusDays(2);
        LocalDateTime threeDays = LocalDateTime.now()
                .plusDays(3);
        LocalDateTime fourDays = LocalDateTime.now()
                .plusDays(3);
        LocalDateTime week = LocalDateTime.now()
                .plusWeeks(1);
        LocalDateTime weekAndADay = LocalDateTime.now()
                .plusWeeks(1)
                .plusDays(1);
        LocalDateTime weekAndTwoDays = LocalDateTime.now()
                .plusWeeks(1)
                .plusDays(2);
        LocalDateTime twoWeeks = LocalDateTime.now()
                .plusWeeks(2);

        bookings.add(new Booking(400, tomorrow, tomorrow.plusHours(2), users.get(2), activities.get(0), new HashSet<>(Set.of(participants.get(1), participants.get(3)))));
        bookings.add(new Booking(401, dayAfterTomorrow, dayAfterTomorrow.plusHours(1), users.get(3), activities.get(1), new HashSet<>(Set.of(participants.get(2), participants.get(4)))));
        bookings.add(new Booking(402, threeDays, threeDays.plusHours(2), users.get(4), activities.get(2), new HashSet<>(Set.of(participants.get(0), participants.get(5)))));
        bookings.add(new Booking(403, fourDays, fourDays.plusHours(1), users.get(5), activities.get(3), new HashSet<>(Set.of(participants.get(3), participants.get(6)))));
        bookings.add(new Booking(404, week, week.plusHours(2), users.get(6), activities.get(4), new HashSet<>(Set.of(participants.get(4), participants.get(7)))));
        bookings.add(new Booking(405, weekAndADay, weekAndADay.plusHours(1), users.get(7), activities.get(5), new HashSet<>(Set.of(participants.get(1), participants.get(8)))));
        bookings.add(new Booking(406, weekAndTwoDays, weekAndTwoDays.plusHours(2), users.get(8), activities.get(0), new HashSet<>(Set.of(participants.get(2), participants.get(9)))));
        bookings.add(new Booking(407, twoWeeks, twoWeeks.plusHours(1), users.get(9), activities.get(1), new HashSet<>(Set.of(participants.get(3), participants.get(0)))));
        bookings.add(new Booking(408, now.plusHours(5), now.plusHours(7), users.get(0), activities.get(2), new HashSet<>(Set.of(participants.get(4), participants.get(5)))));
        bookings.add(new Booking(409, now.plusDays(3)
                .plusHours(2), now.plusDays(3)
                .plusHours(4), users.get(1), activities.get(3), new HashSet<>(Set.of(participants.get(6), participants.get(7)))));
        bookings.add(new Booking(410, tomorrow.plusHours(3), tomorrow.plusHours(6), users.get(2), activities.get(1), new HashSet<>(Set.of(participants.get(0), participants.get(2), participants.get(4)))));
        bookings.add(new Booking(411, dayAfterTomorrow.plusHours(1), dayAfterTomorrow.plusHours(4), users.get(3), activities.get(2), new HashSet<>(Set.of(participants.get(1), participants.get(3), participants.get(5)))));
        bookings.add(new Booking(412, threeDays.plusHours(4), threeDays.plusHours(7), users.get(4), activities.get(3), new HashSet<>(Set.of(participants.get(2), participants.get(4), participants.get(6)))));
        bookings.add(new Booking(413, fourDays.plusHours(3), fourDays.plusHours(6), users.get(5), activities.get(4), new HashSet<>(Set.of(participants.get(3), participants.get(5), participants.get(7)))));
        bookings.add(new Booking(414, week.plusHours(1), week.plusHours(4), users.get(6), activities.get(0), new HashSet<>(Set.of(participants.get(4), participants.get(6), participants.get(8)))));
        bookings.add(new Booking(415, weekAndADay.plusHours(2), weekAndADay.plusHours(5), users.get(7), activities.get(1), new HashSet<>(Set.of(participants.get(5), participants.get(7), participants.get(9)))));
        bookings.add(new Booking(416, weekAndTwoDays.plusHours(3), weekAndTwoDays.plusHours(6), users.get(8), activities.get(2), new HashSet<>(Set.of(participants.get(6), participants.get(8), participants.get(0)))));
        bookings.add(new Booking(417, twoWeeks.plusHours(1), twoWeeks.plusHours(4), users.get(9), activities.get(3), new HashSet<>(Set.of(participants.get(7), participants.get(9), participants.get(1)))));
        bookings.add(new Booking(418, now.plusDays(1)
                .plusHours(2), now.plusDays(1)
                .plusHours(5), users.get(0), activities.get(4), new HashSet<>(Set.of(participants.get(8), participants.get(0), participants.get(2), participants.get(4)))));
        bookings.add(new Booking(419, now.plusDays(2)
                .plusHours(3), now.plusDays(2)
                .plusHours(6), users.get(1), activities.get(0), new HashSet<>(Set.of(participants.get(9), participants.get(1), participants.get(3), participants.get(5)))));
        bookings.add(new Booking(400, tomorrow, tomorrow.plusHours(2), users.get(21), activities.get(0), new HashSet<>(Set.of(participants.get(1), participants.get(3)))));
        bookings.add(new Booking(401, dayAfterTomorrow, dayAfterTomorrow.plusHours(1), users.get(21), activities.get(1), new HashSet<>(Set.of(participants.get(2), participants.get(4)))));


        bookings.add(new Booking(413, fourDays.plusHours(3), fourDays.plusHours(6), users.get(22), activities.get(4), new HashSet<>(Set.of(participants.get(3), participants.get(5), participants.get(7)))));
        bookings.add(new Booking(414, week.plusHours(1), week.plusHours(4), users.get(22), activities.get(0), new HashSet<>(Set.of(participants.get(4), participants.get(6), participants.get(8)))));
        bookings.add(new Booking(415, weekAndADay.plusHours(2), weekAndADay.plusHours(5), users.get(22), activities.get(1), new HashSet<>(Set.of(participants.get(5), participants.get(7), participants.get(9)))));
        bookings.add(new Booking(416, weekAndTwoDays.plusHours(3), weekAndTwoDays.plusHours(6), users.get(22), activities.get(2), new HashSet<>(Set.of(participants.get(6), participants.get(8), participants.get(0)))));
        bookings.add(new Booking(417, twoWeeks.plusHours(1), twoWeeks.plusHours(4), users.get(22), activities.get(3), new HashSet<>(Set.of(participants.get(7), participants.get(9), participants.get(1)))));


        bookingRepository.saveAll(bookings);
    }

    private void createBookingProducts() {
        bookingProducts.add(new BookingProduct(new BookingProductKey(products.get(2), bookings.get(26)), 4));
        bookingProducts.add(new BookingProduct(new BookingProductKey(products.get(4), bookings.get(26)), 1));
        bookingProducts.add(new BookingProduct(new BookingProductKey(products.get(3), bookings.get(26)), 2));
        bookingProducts.add(new BookingProduct(new BookingProductKey(products.get(2), bookings.get(26)), 3));
        bookingProducts.add(new BookingProduct(new BookingProductKey(products.get(7), bookings.get(26)), 1));

        bookingProductRepository.saveAll(bookingProducts);
    }
}
