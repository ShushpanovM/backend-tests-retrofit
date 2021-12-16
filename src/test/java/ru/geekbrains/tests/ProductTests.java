package ru.geekbrains.tests;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.dto.Product;
import ru.geekbrains.utils.PrettyLogger;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductTests extends BaseTest{
    int id;
    int idProduct = 18693;
    Product createProduct;

    @Test
    void postProductTest() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        id =  response.body().getId();
        createProduct = response.body();
        PrettyLogger.DEFAULT.log(response.body().toString());
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @SneakyThrows
    @AfterEach
    void getIdProduct1() {
        Response<Product> response = productService.getProduct(idProduct).execute();
        assertThat(response.body().getId(), equalTo(idProduct));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void updateProduct() {
        Response<Product> response = productService.updateProduct(createProduct).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
