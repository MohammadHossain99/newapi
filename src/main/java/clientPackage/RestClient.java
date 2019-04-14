package clientPackage;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    /**
     * Get Response
     */
    public CloseableHttpResponse getMethod(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);   //Http get Request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);  // Hit the Get Url
        return closeableHttpResponse;
    }

    public CloseableHttpResponse getMethod(String url, HashMap<String , String > headersMap) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);   //Http get Request

        for (Map.Entry<String,String> headers : headersMap.entrySet()){
            httpGet.addHeader(headers.getKey(),headers.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

        return closeableHttpResponse;
    }

    public CloseableHttpResponse postMethod(String url,String entityString ,HashMap<String , String > headersMap) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);   //Http get Request

        httpPost.setEntity(new StringEntity(entityString));

        //for headers
        for (Map.Entry<String,String> headers : headersMap.entrySet()){
            httpPost.addHeader(headers.getKey(),headers.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

        return closeableHttpResponse;
    }
}
