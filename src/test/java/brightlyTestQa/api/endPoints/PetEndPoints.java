package brightlyTestQa.api.endPoints;

import brightlyTestQa.api.test.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetEndPoints extends BaseTest {
    public Response createPet(String body) {

        RequestSpecification addPetReqSpec = given().spec(getRequestSpecification()).body(body);
        Response getPetResponse = addPetReqSpec.when().post(Routes.CREATE_PET);
        return getPetResponse;
    }

    public Response getPet(String petId) {
        RequestSpecification getPetReqSpec = given().spec(getRequestSpecification());
        Response getPetresponse = getPetReqSpec.when().get(Routes.GET_PET, petId);
        return getPetresponse;
    }

    public Response deletePet(String petId) {
        RequestSpecification getPetReqSpec = given().spec(getRequestSpecification());
        Response getPetresponse = getPetReqSpec.when().delete(Routes.DELETE_PET, petId);
        return getPetresponse;
    }
}
