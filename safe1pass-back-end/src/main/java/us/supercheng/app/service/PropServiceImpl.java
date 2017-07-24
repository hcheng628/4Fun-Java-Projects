package us.supercheng.app.service;

import java.util.Properties;

/**
 * Created by cl799honchen on 7/24/2017.
 */
public class PropServiceImpl implements IPropService{

    private Properties appProp;
    private String appLocalRootPath;

    public PropServiceImpl () {
        this.appProp = new Properties();
        this.init();
    }

    public void init() {
        try {
            this.appProp.load(this.getClass().getResourceAsStream(IPropService.APP_PROP));
            this.appLocalRootPath = this.appProp.getProperty(APP_PROP_APP_LOCAL_PATH_KEY);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Properties getAppProp() {
        return this.appProp;
    }

    @Override
    public String getAppLocalRootPath() {
        return this.appLocalRootPath;
    }
}
