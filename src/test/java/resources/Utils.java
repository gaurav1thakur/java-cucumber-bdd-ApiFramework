package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
   static RequestSpecification req; //static ? for loggin all previous requests too in loggin file txt
   ResponseSpecification resspec;

    public static String getGlobalValue(String property) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fileInputStream);
        return prop.getProperty(property);
    }

    public RequestSpecification requestSpecification() throws IOException {
        if(req == null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req =  new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
            System.out.print(req);
            return req;
        }
        return  req;
    }

    public ResponseSpecification responseSpecification() throws FileNotFoundException {
        return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    public String getJsonPath(Response response, String key){
        String respond = response.asString();
        JsonPath js =  new JsonPath(respond);
        return js.get(key).toString();
    }
}
