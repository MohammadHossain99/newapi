package testRestApi;

import base.TestBaseClass;
import clientPackage.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends TestBaseClass {

    String baseurl;
    String serviceUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;
    JSONObject responseJASON;

    //    TestBaseClass testBaseClass;
    RestClient restClient;



    @BeforeMethod
    public void init() throws IOException {

        restClient = new RestClient();
//        testBaseClass = new TestBaseClass();
        this.baseurl = prop.getProperty("URL");
        this.serviceUrl = prop.getProperty("serviceURL");

        this.url = this.baseurl + this.serviceUrl; // it become uri
        HashMap<String,String> addHeaders = new HashMap<>();
        addHeaders.put("Content-Type","application/json");
        closeableHttpResponse = restClient.postMethod(this.url,"",addHeaders);
    }
}
