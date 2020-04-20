package amanet.rest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiCall {
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private String url;
    private Map<String, String> headers;

    private ApiCall(ApiCallBuilder apiCallBuilder) {
        this.url = apiCallBuilder.url;
        this.headers = apiCallBuilder.headers;
    }

    public static class ApiCallBuilder {
        String url;
        Map<String, String> headers = new HashMap<>();

        public ApiCall build() {
            return new ApiCall(this);
        }

        public ApiCallBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public ApiCallBuilder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public ApiCallBuilder setHeaders(String key, String value) {
            this.headers.put(key, value);
            return this;
        }
    }

    public String sendGet() {
        try {

            HttpGet getRequest = new HttpGet(this.url);
            this.headers.forEach((k, v) -> getRequest.addHeader(k, v));

            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            HttpEntity httpEntity = response.getEntity();

            httpClient.getConnectionManager().shutdown();
            return EntityUtils.toString(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
