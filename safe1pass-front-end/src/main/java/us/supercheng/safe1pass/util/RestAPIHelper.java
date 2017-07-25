package us.supercheng.safe1pass.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by cl799honchen on 7/25/2017.
 */
public class RestAPIHelper {

    public final String HTTP_GET = "GET";
    public final String HTTP_POST = "POST";
    public final String HTTP_PUT = "PUT";

    public String rest_GET (String API_Endpoint, String urlParams) throws Exception {
        // String urlwithParams = API_Endpoint + "?" + URLEncoder.encode(urlParams,"UTF-8");
//        System.out.println("Before urlwithParams: " + API_Endpoint + "?" + urlParams);
//        System.out.println("After urlwithParams:  " + urlwithParams);
        HttpURLConnection conn = (HttpURLConnection) new URL(API_Endpoint + "?" + urlParams).openConnection();
        conn.setRequestMethod(HTTP_GET);
        return this.getCommonAPIResponse(conn);
        //return null;
    }

    public String rest_POST (String API_Endpoint, String postDataStr, String contentType) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(API_Endpoint).openConnection();
        this.setCommonHTTPRequestProperties(conn);
        this.setCommonAPIRequest(conn, HTTP_POST, contentType, postDataStr);
        return this.getCommonAPIResponse(conn);
    }

    public String rest_PUT (String API_Endpoint, String postDataStr, String contentType) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(API_Endpoint).openConnection();
        this.setCommonHTTPRequestProperties(conn);
        this.setCommonAPIRequest(conn, HTTP_PUT, contentType, postDataStr);
        return this.getCommonAPIResponse(conn);
    }



    private void setCommonHTTPRequestProperties(HttpURLConnection conn) {
        conn.setDoOutput(true);
        conn.setRequestProperty( "Charset", "UTF-8");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
    }

    private void setCommonAPIRequest (HttpURLConnection conn, String httpRequestMethod, String contentType, String postDataStr) throws Exception {
        conn.setRequestMethod(httpRequestMethod);
        conn.setRequestProperty( "Content-Type", contentType);
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataStr.getBytes(StandardCharsets.UTF_8).length ));
        conn.getOutputStream().write(postDataStr.getBytes(StandardCharsets.UTF_8));
    }

    private String getCommonAPIResponse (HttpURLConnection conn) throws Exception {
        if(conn.getResponseCode() == 200) {
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            // String response = sb.toString();
            return sb.toString();
        } else {
            throw new RuntimeException("API Exception: " + conn.getResponseCode() + " " + conn.getResponseMessage());
        }
    }
}
