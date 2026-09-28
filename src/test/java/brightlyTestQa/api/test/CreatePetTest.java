package brightlyTestQa.api.test;

import brightlyTestQa.api.endPoints.PetEndPoints;
import brightlyTestQa.utils.Util;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreatePetTest extends BaseTest {
    PetEndPoints petEndPoints = new PetEndPoints();
    String petId;

    @Test(priority = 1)
    public void createPetTest1() throws IOException {
        //        String requestBody = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggisssevb1\",\"photoUrls\":[\"dfgdfdg\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
        //OR
        String payload = Util.readJson("src/main/resources/apiPayloads/createPet.json");
        Response res= petEndPoints.createPet(payload);
        Assert.assertEquals(res.statusCode(), 200);
        petId=Util.getkeyValueFromResponse(res.asString(), "id");
        Assert.assertEquals(Util.getkeyValueFromResponse(res.asString(), "name"), "doggisssevb12");
    }

    @Test(priority = 2, dependsOnMethods = "createPetTest1")
    public void getPetTest1() {

        Response res= petEndPoints.getPet(petId);
        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(Util.getkeyValueFromResponse(res.asString(), "id"), petId);
    }

    @Test(priority = 3, dependsOnMethods = "getPetTest1")
    public void deletePetTest1() {
        Response res= petEndPoints.deletePet(petId);
        Assert.assertEquals(res.statusCode(), 200);
        res.then().log().all();
        Assert.assertEquals(Util.getkeyValueFromResponse(res.asString(), "message"), petId);
    }
}
