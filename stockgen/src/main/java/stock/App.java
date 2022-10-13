package stock;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {
    public static void main (String[] args){
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(new FileReader("stock_transations-3.by.account.holder.json"));
            JSONObject jsonObject = (JSONObject)obj;
            String Fname = (String)jsonObject.get("first_name");
            String Lname = (String)jsonObject.get("last_name");
            System.out.println("Name: " + Fname + Lname);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
