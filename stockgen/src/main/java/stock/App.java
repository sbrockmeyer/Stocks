package stock;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        Float newBal = 0f;
        Long accountNum;
        String fName;
        String lName;
        String social;
        String email;
        String phone;

        String type;
        Long countShare;
        String price;
        String symbol;
        LocalDate date = LocalDate.now();
        Float stockAmount = 0f;
        Float fullCost = 0f;

        try {
            JSONArray jsonArray = (JSONArray) parser
                    .parse(new FileReader("stockgen/stock_transations-3.by.account.holder.json"));

            for (int i = 0; i < 1; i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                System.out.println(object.keySet());

                accountNum = (Long) object.get("account_number");
                fName = (String) object.get("first_name");
                lName = (String) object.get("last_name");
                social = (String) object.get("ssn");
                email = (String) object.get("email");
                phone = (String) object.get("phone");

                System.out.println(accountNum);
                System.out.println(fName + " " + lName);
                System.out.println(social);
                System.out.println(email);
                System.out.println(phone);

                String beginningBal = (String) object.get("beginning_balance");
                System.out.println(beginningBal);

                String begBal = beginningBal.replaceAll(("\\$"), "");
                Float bal = Float.parseFloat(begBal);

                JSONArray stockArray = (JSONArray) object.get("stock_trades");

                for (int a = 0; a < stockArray.size(); a++) {
                    JSONObject object2 = (JSONObject) stockArray.get(a);

                    type = (String) object2.get("type");
                    countShare = (Long) object2.get("count_shares");
                    Float shares = (Float) countShare.floatValue();
                    symbol = (String) object2.get("stock_symbol");
                    Float startStock = 0f;

                    System.out.println("________________________");
                    // System.out.println("a " + type)/;
                    System.out.println(object2.get("type"));
                    System.out.println(symbol);
                    System.out.println(object2.get("count_shares"));
                    System.out.println(object2.get("price_per_share"));

                    price = (String) object2.get("price_per_share");
                    String priceShare = price.replaceAll(("\\$"), "");
                    Float sharePrice = Float.parseFloat(priceShare);

                    if (type.equals("Buy")) {
                        fullCost = sharePrice * shares;
                        newBal = bal - fullCost;
                        stockAmount = startStock + shares;
                        System.out.println(startStock);
                        System.out.println("here is new bal $" + newBal);

                    } else if (type.equals("Sell")) {
                        fullCost = sharePrice * shares;
                        newBal = bal + fullCost;
                        stockAmount = startStock - shares;
                        System.out.println(startStock);
                        System.out.println("here is new bal $" + newBal);

                    } else {
                        System.out.println("something isnt right");
                    }

                    File myObj = new File("stock.html");
                    if (myObj.createNewFile()) {
                        System.out.println("File created" + myObj.getName());
                    } else {
                        System.out.println("File already exists");
                    }

                    FileWriter myWriter = new FileWriter("stock.html");
                    myWriter.write(
                            "<h1>" + date + "</h1>" +
                                    "<p>" + "Full Name: " + fName + " " + lName + "</p>" +
                                    "<p>" + "Social Security Number: " + social + "</p>" +
                                    "<p>" + "Email: " + email + "</p>" +
                                    "<p>" + "Phone: " + phone + "</p>" +
                                    "<p>" + "Account #:  " + accountNum + "</p>" +
                                    "<h3>" + "Type   Symbol  Price   Shares  Total" + "</h3>" +
                                    "<p>" + type + " " + symbol + " " + price +  " " + stockAmount + " " + fullCost + "</p>" +
                                    "<p>" + "Full balance: $"+ newBal + "</p>"
                    );
                    myWriter.close();
                    System.out.println("wrote to file");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
