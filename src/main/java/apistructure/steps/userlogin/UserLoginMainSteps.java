package apistructure.steps.userlogin;

import apistructure.baseclass.RestBaseClass;
import apistructure.pojomodels.UserLoginPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;

public class UserLoginMainSteps extends RestBaseClass {

    protected String email;
    protected String password;

    @Getter
    @Setter
    protected String accessToken;

    @Step("Авторизация пользователя в системе по эндпоиту /auth/login")
    public Response sendPostRequestUserLogin(String email, String password){

        UserLoginPojo userLoginPojo =
                new UserLoginPojo(email, password);


        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userLoginPojo)
                        .when()
                        .post(apiUserLogin);

        String bearerToken = response.jsonPath().get("accessToken");

        if (bearerToken != null) {
            this.accessToken = bearerToken;
        }

        return response;
    }
}
