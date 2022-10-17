package stock;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {
    public static void main (String[] args){
        JSONParser parser = new JSONParser();

        try{
            // Object obj = parser.parse(new FileReader("stockgen/stock_transations-3.by.account.holder.json"));
            Object obj = parser.parse(new FileReader("stockgen/dummy.json"));
            JSONObject jsonObject = (JSONObject)obj;
            // JSONArray array = (JSONArray)obj;
            // System.out.println(array);
            String Fname = (String)jsonObject.get("first_name");
            String Lname = (String)jsonObject.get("last_name");
            long accountNum = (long)jsonObject.get("account_number");
            String social = (String)jsonObject.get("ssn");
            String email = (String)jsonObject.get("email");
            String phone = (String)jsonObject.get("phone");
            String begBal = (String)jsonObject.get("beginning_balance");

            System.out.println("Account number: " +accountNum);
            System.out.println("Name: " + Fname + Lname);
            System.out.println("Social: " + social);
            System.out.println("Email: " + email);
            System.out.println("Phone" + phone);
            System.out.println("Begining bal: " + begBal);

            JSONArray stockTrades = (JSONArray)jsonObject.get("stock_trades");
            Iterator iterator = stockTrades.iterator();
            while(iterator.hasNext()){
                System.out.println((iterator.next()));
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        // Create File
        try{
            File myObj = new File("doc.txt");
            if(myObj.createNewFile()){
                System.out.println("File Created: " + myObj.getName());
            }else{
                System.out.println("File already exists");
            }
        }catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }

        // Write to file
        try{
            FileWriter myWriter = new FileWriter("doc.txt");
            myWriter.write("hi");
            myWriter.close();
            System.out.println("You wrote to the file");
        }catch (IOException e){
            System.out.println("error");
            e.printStackTrace();
        }

    }
}
