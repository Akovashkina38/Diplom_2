package order;

import client.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import user.User;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client  {

    private static String ORDER_PATH = "/api/orders";
   // private static String INGREDIENT_PATH = "/api/ingredients";

    @Step("Создание заказа с отправкой токена")
    public ValidatableResponse createOrderWithToken(String token, String ingredient) {
        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .body(ingredient)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Создание заказа без отправки токена")
    public ValidatableResponse createOrderWithoutToken(String ingredient) {
        return given()
                .spec(getSpec())
                .body(ingredient)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Получение заказов конкретного пользователя с отправкой токена")
    public ValidatableResponse getOrdersWithToken(String token) {
        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .when()
                .get(ORDER_PATH)
                .then();
    }

    @Step("Получение заказов конкретного пользователя без отправки токена")
    public ValidatableResponse getOrdersWithoutToken() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}
