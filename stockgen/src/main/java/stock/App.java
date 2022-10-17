package stock;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {
    public static void main (String[] args){
        JSONParser parser = new JSONParser();

        try{
            JSONArray jsonObject = (JSONArray) parser.parse(new FileReader("stockgen/stock_transations-3.by.account.holder.json"));

            for(int i = 0; i < 1; i++){
                JSONObject object = (JSONObject) jsonObject.get(i);
                System.out.println(object.keySet());

                System.out.println(object.get("account_number"));

                JSONArray stockArray = (JSONArray) object.get("stock_trades");

                for(int j = 0; j < stockArray.size(); j++){
                    //if buy - remove cash
                    //if sell - add
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
