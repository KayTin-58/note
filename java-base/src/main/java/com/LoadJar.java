package com;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>
 * 动态加载jar
 * </p>
 *
 * @author KayTin 2019/12/4
 */
public class LoadJar {

    public static void main(String[] args)  {
        String path = "commons-lang3-3.1.jar";//jar文件需放在工程目录下

        loadJar(path);
        Object strip = null;
        try {
            Class<?> aClass = Class.forName("org.apache.commons.lang3.StringUtils");
            Object instance = aClass.newInstance();
            strip = aClass.getDeclaredMethod("strip", String.class, String.class).invoke(instance,
                    "[1,2,3,4,5,6,2,3,5,1]", "[]");
        } catch (InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(strip);

    }


    /**
     * 加载Jar
     *
     * @param jarPath
     */
    public static void loadJar(String jarPath) {
        File file = new File(jarPath);
        if (!file.exists()) {
            throw new RuntimeException("jar is not exist");
        }
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        boolean accessible = method.isAccessible();
        if (!accessible) {
            method.setAccessible(true);
        }
        // 获取系统类加载器
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        //获取jar文件的url路径
        URL url;
        try {
            url = file.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (InvocationTargetException | IllegalAccessException | MalformedURLException e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }

    }
}
