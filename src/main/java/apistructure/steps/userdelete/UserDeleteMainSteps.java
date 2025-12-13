package apistructure.steps.userdelete;

import apistructure.baseclass.RestBaseClass;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;

public class UserDeleteMainSteps extends RestBaseClass {

    protected String email;
    protected String password;

    @Getter
    @Setter
    protected String accessToken;

    @Step("Удаление нового пользователя если он существует")
    public Response deleteUserIfExists() {

        if (accessToken == null || accessToken.isBlank()) {
            return null;
        }

        String token = accessToken.startsWith("Bearer ")
                ? accessToken.substring("Bearer ".length()).trim()
                : accessToken.trim();

        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .when()
                .delete(apiUserInformation);

        return response;
    }

}
