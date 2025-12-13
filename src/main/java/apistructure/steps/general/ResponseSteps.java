package apistructure.steps.general;

import apistructure.baseclass.RestBaseClass;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSteps {

    @Step("Проверка положительного ответа success:true")
    public void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("success", equalTo(true));
    }

    @Step("Проверка созданного accessToken")
    public void checkAccessTokenNewUser(Response response) {
        response.then().assertThat().body("accessToken", notNullValue());
    }

    @Step("Проверка созданного refreshToken")
    public void checkRefreshTokenNewUser(Response response) {
        response.then().assertThat().body("refreshToken", notNullValue());
    }

    @Step("Проверка наличия электронного адреса в ответе")
    public void checkEmailNewUser(Response response) {
        response.then().assertThat().body("user.email", notNullValue());
    }

    @Step("Проверка наличия имени пользователя в ответе")
    public void checkNameNewUser(Response response) {
        response.then().assertThat().body("user.name", notNullValue());
    }

    @Step("Вывод ответа в консоль")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }


}
