package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    RequestSpecification res;
    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
        res=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                .addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();

        return res;

    }

    public String getGlobalValue(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("/Users/b0266643/Desktop/Automation/BDDFrameWork/src/test/java/resources/global.properties");
        prop.load(fis);
       return prop.getProperty(key);
    }
}
