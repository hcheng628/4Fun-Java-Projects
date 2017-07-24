package us.supercheng.app.service;

import java.util.Properties;

/**
 * Created by cl799honchen on 7/24/2017.
 */
public interface IPropService {
    final String APP_PROP_APP_LOCAL_PATH_KEY="app.local.path";
    final String APP_PROP = "/App.properties";

    Properties getAppProp();
    String getAppLocalRootPath();
}
