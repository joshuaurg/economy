package com.fishman.app.economy.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by hema on 16/9/21.
 */
public class RespCodeProperties {

    private static RespCodeProperties env;
    private Properties properties = new Properties();

    private RespCodeProperties() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("/errorCode.properties"));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    };
    // 线程同步获得唯一实例
    public static synchronized RespCodeProperties getInstance() {
        if (null == env) {
            return new RespCodeProperties();
        } else {
            return env;
        }
    }
    public RespCodeProperties clone() {
        return getInstance();
    }
    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
