package amanet.tests.api;

import amanet.rest.ApiCall;
import amanet.rest.MyPojo;
import amanet.tests.APIBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests extends APIBaseTest {
    @Test
    public void apiTest() {
        String getRequest = new ApiCall.ApiCallBuilder().
                setUrl("https://jsonplaceholder.typicode.com/todos/1").
                build().
                sendGet();

        MyPojo myPojo = gson.fromJson(getRequest, MyPojo.class);
        Assert.assertEquals(myPojo.getUserId(), 1, "User Id must be equal to 1");
    }
}
