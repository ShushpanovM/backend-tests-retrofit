package ru.geekbrains.tests;

import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.dto.Category;
import ru.geekbrains.enums.CategoryType;
import ru.geekbrains.utils.PrettyLogger;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoryTest extends BaseTest{
    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();

        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }
}
