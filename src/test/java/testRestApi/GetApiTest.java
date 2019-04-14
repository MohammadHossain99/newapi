package testRestApi;

import base.TestBaseClass;
import clientPackage.RestClient;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stringUtill.StringUtill;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBaseClass {

    String baseurl;
    String serviceUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;
    JSONObject responseJASON;

    /*TestBaseClass testBaseClass;*/
    RestClient restClient;


    @BeforeMethod
    public void init() throws IOException{
        restClient = new RestClient();
        /*testBaseClass = new TestBaseClass();*/
        this.baseurl = prop.getProperty("URL");
        this.serviceUrl = prop.getProperty("serviceURL");
        this.url = this.baseurl + this.serviceUrl; // it become uri

        HashMap<String,String> addHeaders = new HashMap<>();
        addHeaders.put("Content-Type","application/json");
//        addHeaders.put("APIkey","xvCGkuYrgQQapV3IjedEIlMTA");
//        addHeaders.put("APIsecretkey","PeFFWwQKrLwzdFnQwrp351qmpc9ZIGbUPoXwfItstH2jWm5pz2");
//        addHeaders.put("Accesstoken","1109971036387360771-73SLy4xweQGEDnG80uJRlGv34TCgou");
//        addHeaders.put("Accesstokensecret","u03SnI8c9l0lz2F6WDxwIkgdAgvm1TLB5Z1Qy2qEbrnUT");

        closeableHttpResponse = restClient.getMethod(this.url,addHeaders);

        String responseEntityString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        responseJASON = new JSONObject(responseEntityString);
    }

    @Test
    public void getTest() throws IOException {

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
        System.out.println("Status Code IS : " + statusCode);

        Header[] headerArray = closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeader = new HashMap<String, String>();

        for (Header header : headerArray) {
            allHeader.put(header.getName(), header.getValue());
        }
        System.out.println(" Headers Arrays ->>> " + allHeader);
    }

    @Test
    public void getTest1(){
        String perPage =  StringUtill.getValueByJPath(responseJASON,"/errors[0]/code");
        System.out.println(perPage+" <-------------");
    }

    @Test
    public void getTest2(){
        String total = StringUtill.getValueByJPath(responseJASON,"/total");
        Assert.assertEquals(Integer.parseInt(total),12,"Test Is Failed");
        System.out.println("Test Is Passed "+total);
    }

   @Test
    public void getTestArray1(){
        String firstName = StringUtill.getValueByJPath(responseJASON,"/data[0]/last_name");
        Assert.assertEquals(firstName,"Bluth");
        System.out.println(firstName);
    }

    @Test
    public void getTestArray2(){
        String data1 = StringUtill.getValueByJPath(responseJASON,"/data[2]/first_name");
        Assert.assertEquals(data1,"Emma");
        System.out.println(data1);
    }

    @Test
    public void getTestArray3(){
        String data1 = StringUtill.getValueByJPath(responseJASON,"/data[1]/first_name");
        Assert.assertEquals(data1,"Janet");
        System.out.println(data1);
    }
}
