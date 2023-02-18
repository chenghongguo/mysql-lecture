package com.hongguo.mysql.mybatis;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PropertiesTest {

    Properties properties = null;

    @Before
    public void before() {
        properties = new Properties();
    }

    @Test
    public void test() {
        properties.setProperty("hello", "world");
        Object o = properties.setProperty("hello", "world2");
        System.out.println(o);
        System.out.println(properties.get("hello"));
    }

    @Test
    public void testLoadReader() throws Exception {
        Reader reader = new FileReader("p.txt");
        properties.load(reader);
        System.out.println(properties.getProperty("a"));
        System.out.println(properties);
        if (reader != null) {
            reader.close();
        }

        InputStream inputStream = Files.newInputStream(new File("p.txt").toPath());
        properties.load(inputStream);
    }

    @Test
    public void testLoadInputStream() throws Exception {
        InputStream inputStream = Files.newInputStream(new File("p.txt").toPath());
        properties.load(inputStream);
        System.out.println(properties.getProperty("a"));
        System.out.println(properties);
        if (inputStream != null) {
            inputStream.close();
        }

        Set<String> strings = properties.stringPropertyNames();
        System.out.println(strings);
    }

    @Test
    public void testLoadXml() throws Exception {
        InputStream inputStream = Files.newInputStream(new File("p.xml").toPath());
        properties.loadFromXML(inputStream);
        System.out.println(properties.getProperty("jdbc.driver"));
        System.out.println(properties);
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Test
    public void testKeyAndValueCannotBeNull() throws Exception {
        properties.setProperty(null, "hello");
        properties.setProperty("hello", null);
        properties.setProperty(null, null);
        System.out.println(properties);
    }

    @Test
    public void testDefault() {
        Properties defaultP = new Properties();
        defaultP.setProperty("d-hello", "d-value");
        defaultP.setProperty("d-hello-2", "d-value-2");
        Properties properties = new Properties(defaultP);
        properties.setProperty("p-hello-1", "p-value-1");
        properties.setProperty("d-dhello-2", "p-value-2");
        System.out.println(properties);
        System.out.println(properties.getProperty("d-hello-2"));
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            properties.setProperty("hello", Thread.currentThread().getName() + " value");
        });
        executorService.submit(() -> {
            properties.setProperty("hello", Thread.currentThread().getName() + " value");
        });
        executorService.submit(() -> {
            properties.setProperty("hello", Thread.currentThread().getName() + " value");
        });
        executorService.submit(() -> {
            properties.setProperty("hello", Thread.currentThread().getName() + " value");
        });
        executorService.submit(() -> {
            properties.setProperty("hello", Thread.currentThread().getName() + " value");
        });

        Thread.sleep(3000);
        executorService.shutdownNow();

        System.out.println(properties);
    }
}
