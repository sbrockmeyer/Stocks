package stock;

import java.io.*;
// import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {
    public static void main (String[] args){
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(new FileReader("stockgen/stock_transations-3.by.account.holder.json"));
            // JSONObject jsonObject = (JSONObject)obj;
            JSONArray array = (JSONArray)obj;
            System.out.println(array);
            // String Fname = (String)jsonObject.get("first_name");
            // String Lname = (String)jsonObject.get("last_name");
            // long accountNum = (long)jsonObject.get("account_number");
            // String social = (String)jsonObject.get("ssn");


            // System.out.println("Name: " + Fname + Lname);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
