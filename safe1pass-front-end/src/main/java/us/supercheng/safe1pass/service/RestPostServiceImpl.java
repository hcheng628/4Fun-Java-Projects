package us.supercheng.safe1pass.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import us.supercheng.safe1pass.util.RestAPIHelper;
import java.util.List;
import java.util.Properties;

/**
 * Created by cl799honchen on 7/25/2017.
 */
public class RestPostServiceImpl implements IPostService{

    private final String RestAPI_Content_Type = "application/x-www-form-urlencoded";
    private final String RestAPI_Key_BASEURL = "rest.api.endpoint.baseurl";
    private final String RestAPI_Key_LISTPOSTS = "rest.api.endpoint.method.listposts";
    private final String RestAPI_Key_POST = "rest.api.endpoint.method.post";

    private RestAPIHelper restAPIHelper;
    private Properties restAPIProp;

    public RestPostServiceImpl (Properties inAPIProp) {
        this.restAPIHelper = new RestAPIHelper();
        this.restAPIProp = inAPIProp;
    }

    public Properties getRestAPIProp() {
        return restAPIProp;
    }

    @Override
    public List<String> getListOfPostFiles(String username) {
        try {
            String postFileResponse = this.restAPIHelper.rest_GET(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_LISTPOSTS),
                    "username=" + username);
            Gson gson = new GsonBuilder().create();
            TypeToken<List<String>> token = new TypeToken<List<String>>() {};
            return gson.fromJson(postFileResponse,token.getType());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getPostContent(String postFilename, String username) {
        // System.out.println(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_POST));
        try {
            return this.restAPIHelper.rest_GET(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_POST),
                    "postname=" + postFilename + "&username=" + username);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void savePost(String username, String postname, String postContent) {
        try {
            String savePostResponse = this.restAPIHelper.rest_PUT(this.restAPIProp.getProperty(RestAPI_Key_BASEURL) + this.restAPIProp.getProperty(RestAPI_Key_POST),
                   "username=" + username + "&postname=" + postname + "&postcontent=" + postContent,
                    "application/x-www-form-urlencoded");
            if (!Boolean.parseBoolean(savePostResponse)) {
                throw new RuntimeException("Failed Saving Post");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
