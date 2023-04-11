package JDBC;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("com.mysql.cj.jdbc.Driver"); // Class will load at runtime dynamically Class.forName("$ClassName")
        Connection connection = null;

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<CustomerDetails> customerDetailsArrayList = new ArrayList<CustomerDetails>();
        JSONArray customerDetailsJsonArray = new JSONArray();

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");
        //object of statement class will help us to execute queries

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");


        //  setting pointer for first row resultSet.next();

//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1)); // get cell 1
//            System.out.println(resultSet.getString(2)); // get cell 2
//            System.out.println(resultSet.getInt(3)); // get cell 3
//            System.out.println(resultSet.getString(4)); // get cell 4
//        }

        while (resultSet.next()) {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setCourseName(resultSet.getString(1));
            customerDetails.setPurchaseDate(resultSet.getString(2));
            customerDetails.setAmount(resultSet.getInt(3));
            customerDetails.setLocation(resultSet.getString(4));
            customerDetailsArrayList.add(customerDetails);
        }


        // creating separate json files for each row in result set in JDBC/ResultSet/customerInfo.json
        for (int i = 0; i < customerDetailsArrayList.size(); i++) {
            objectMapper.writeValue(new File("src/test/java/JDBC/ResultSet/customerInfo" + i + ".json"), customerDetailsArrayList.get(i));

        }

        // create one common json file with all the result set in one file as data
        for (int i = 0; i < customerDetailsArrayList.size(); i++) {

            // create json string from java object -  gson library
            Gson gson = new Gson();
            String customerDetailsString = gson.toJson(customerDetailsArrayList.get(i));
            customerDetailsJsonArray.add(customerDetailsString);
        }

        // Json simple library
        JSONObject jsonObjectData = new JSONObject();
        jsonObjectData.put("data", customerDetailsJsonArray);
        System.out.println(jsonObjectData.toJSONString());

        //apache common text library to remove escape chars
        String unescapedString = StringEscapeUtils.unescapeJava(jsonObjectData.toJSONString());
        System.out.println(unescapedString);

        unescapedString = unescapedString.replace("\"{", "{");
        unescapedString = unescapedString.replace("}\"", "}");
        System.out.println(unescapedString);

        try (FileWriter fileWriter = new FileWriter("src/test/java/JDBC/ResultSet/combineOneJson.json")) {
            fileWriter.write(unescapedString);
        }

        // Convert Json file back to java Object
        ObjectMapper read = new ObjectMapper();
        CustomerDetails customerDetails1 = read.readValue(new File("src/test/java/JDBC/ResultSet/customerInfo0.json"),CustomerDetails.class);
        System.out.print(customerDetails1.getCourseName()); // output - Appium

    }
}

