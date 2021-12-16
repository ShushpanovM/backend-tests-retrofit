package ru.geekbrains.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Retrofit;
import ru.geekbrains.dto.Product;
import ru.geekbrains.enums.CategoryType;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.utils.RetrofitUtils;

public class BaseTest {
    static Retrofit client;
    Faker faker = new Faker();
    static ProductService productService;
    static CategoryService categoryService;
    Product product;
    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().dish())
                .withPrice((int) ((Math.random() + 1) * 100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

}
