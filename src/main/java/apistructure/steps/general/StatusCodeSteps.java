package apistructure.steps.general;

import apistructure.baseclass.RestBaseClass;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;

public class StatusCodeSteps {

    @Step("Проверка статуса кода 200")
    public void checkSuccessfulCodeStatus200(Response response){
        response.then().statusCode(SC_OK);
    }
}
