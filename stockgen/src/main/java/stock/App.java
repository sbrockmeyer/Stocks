package stock;

import java.io.*;
import java.text.FieldPosition;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {
    public static void main (String[] args){
        JSONParser parser = new JSONParser();
        Float newBal;

        try{
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("stockgen/stock_transations-3.by.account.holder.json"));

            for(int i = 0; i < 1; i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                System.out.println(object.keySet());

                System.out.println(object.get("account_number"));
                System.out.println(object.get("first_name"));
                System.out.println(object.get("last_name"));
                System.out.println(object.get("ssn"));
                System.out.println(object.get("email"));
                System.out.println(object.get("phone"));

                String beginningBal = (String)object.get("beginning_balance");
                System.out.println(beginningBal);
                // int bal = Integer.parseInt(beginningBal);
                // Float bal = Float.parseFloat(beginningBal);
                String begBal = beginningBal.replaceAll(("\\$"), "");
                Float bal = Float.parseFloat(begBal);

                JSONArray stockArray = (JSONArray) object.get("stock_trades");

                for(int j = 0; j < stockArray.size(); j++){
                    JSONObject object2 = (JSONObject) stockArray.get(j);
                    String type = (String) object2.get("type");
                    Long countShare = (Long) object2.get("count_shares"); 
                    Float shares = (Float) countShare.floatValue();
                    System.out.println("________________________");
                    // System.out.println("a " + type)/;
                    System.out.println(object2.get("type"));
                    System.out.println(object2.get("stock_symbol"));
                    System.out.println(object2.get("count_shares"));
                    System.out.println(object2.get("price_per_share"));
                    String price = (String) object2.get("price_per_share");
                    // System.out.println(price);
                    String priceShare = price.replaceAll(("\\$"), "");
                    Float sharePrice = Float.parseFloat(priceShare);

                    if (type.equals("Buy")) {
                        Float fullCost = sharePrice*shares;
                        // System.out.println(fullCost);
                        newBal = bal - fullCost;
                        System.out.println("here is new bal $"+newBal);
                        
                    }else if(type.equals("Sell")) {
                        Float fullCost = sharePrice*shares;
                        // System.out.println(fullCost);
                        newBal = bal + fullCost;
                        System.out.println( "here is new bal $"+ newBal);

                    }else{
                        System.out.println("something isnt right");
                    }

                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
