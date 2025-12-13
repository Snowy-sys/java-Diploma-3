package apistructure.baseclass;

import io.restassured.RestAssured;

public class RestBaseClass {

    static {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru/api";
    }

    protected final String apiUserLogin = "/auth/login";
    protected final String apiUserInformation = "/auth/user";
}
