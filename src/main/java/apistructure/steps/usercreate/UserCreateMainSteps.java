package apistructure.steps.usercreate;

import apistructure.baseclass.RestBaseClass;
import apistructure.pojomodels.UserCreationPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;

public class UserCreateMainSteps extends RestBaseClass {

    protected String email;
    protected String password;
    protected String name;

    @Getter
    @Setter
    protected String accessToken;

    @Step("Создать нового пользователя по эндпоинту /auth/register")
    public Response sendPostRequestUserCreation(String email, String password, String name){

        this.email = email;
        this.password = password;
        this.name = name;

        UserCreationPojo userCreationPojo =
                new UserCreationPojo(email, password, name);

        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userCreationPojo)
                        .when()
                        .post(apiUserCreation);

        String bearerToken = response.jsonPath().get("accessToken");

        if (bearerToken != null) {
            this.accessToken = bearerToken;
        }

        return response;
    }
}
