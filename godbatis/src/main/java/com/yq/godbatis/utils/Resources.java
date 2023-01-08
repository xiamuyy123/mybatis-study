package com.yq.godbatis.utils;

import java.io.InputStream;

public class Resources {

    private Resources(){};

    public static InputStream getResourceAsStream(String name){
        return ClassLoader.getSystemClassLoader().getResourceAsStream(name);
    }
}
