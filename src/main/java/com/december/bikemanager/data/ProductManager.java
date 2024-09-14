package com.december.bikemanager.data;

import java.util.HashMap;
import java.util.ArrayList;

public class ProductManager {

  private static String DATA_PATH = "./data";

  private static final int ID_LENGTH = 6;
  private static final String ID_TEMPLATE = "0123456789";

  private static HashMap<String, Product> products = new HashMap<String, Product>();

  public static void loadProductsFromFile() {
    try {
      addProduct("Domane SL 7", "Trek", "Road", 2023, 5499);
      addProduct("Giant Propel Advanced Pro", "Giant", "Road", 2022, 4500);
      addProduct("Stumpjumper Alloy", "Specialized", "Mountain", 2023, 2600);
      addProduct("Vado SL 4.0", "Specialized", "Electric", 2021, 3500);
      addProduct("Topstone Carbon Lefty 3", "Cannondale", "Gravel", 2022, 4200);
      addProduct("Checkpoint SL 5", "Trek", "Gravel", 2023, 3299);
      addProduct("Fathom 29 2", "Giant", "Mountain", 2022, 1500);
      addProduct("Turbo Vado 3.0", "Specialized", "Electric", 2022, 2700);
      addProduct("SuperSix EVO Hi-Mod", "Cannondale", "Road", 2023, 7500);
      addProduct("Canyon Neuron AL 6.0", "Canyon", "Mountain", 2021, 2000);
      addProduct("Emonda SLR 9", "Trek", "Road", 2023, 11999);
      addProduct("Ribble CGR AL e", "Ribble", "Electric", 2022, 2999);
      addProduct("Revolt Advanced 2", "Giant", "Gravel", 2021, 2400);
      addProduct("Jekyll 29 1", "Cannondale", "Mountain", 2023, 6999);
      addProduct("SLR 9 eTap", "Trek", "Road", 2023, 12999);
      addProduct("Ripley AF GX Eagle", "Ibis", "Mountain", 2022, 4299);
      addProduct("S-Works Diverge", "Specialized", "Gravel", 2023, 10999);
      addProduct("Sirrus X 5.0", "Specialized", "Hybrid", 2021, 2300);
      addProduct("Endurace CF SLX 8 Disc", "Canyon", "Road", 2023, 6000);
      addProduct("Rogue Ridge RF750", "Rogue Ridge", "Electric", 2022, 5000);
      addProduct("Roubaix Pro", "Specialized", "Road", 2022, 8000);
      addProduct("Anthem Advanced Pro 29", "Giant", "Mountain", 2023, 5700);
      addProduct("Checkpoint ALR 5", "Trek", "Gravel", 2021, 2400);
      addProduct("Synapse Carbon 105", "Cannondale", "Road", 2022, 3000);
      addProduct("Spectral CF 7", "Canyon", "Mountain", 2023, 4000);
      addProduct("Vado 5.0 IGH", "Specialized", "Electric", 2023, 5000);
      addProduct("Calibre Line 10", "Calibre", "Mountain", 2021, 1500);
      addProduct("Talon 1", "Giant", "Mountain", 2022, 800);
      addProduct("Mavaro Neo 1", "Cannondale", "Electric", 2023, 7500);
      addProduct("FX Sport 4", "Trek", "Hybrid", 2021, 1800);
      addProduct("Allez Elite", "Specialized", "Road", 2022, 1600);
      addProduct("Escape 3", "Giant", "Hybrid", 2023, 520);
      addProduct("Marlin 7", "Trek", "Mountain", 2023, 1050);
      addProduct("Trail 5", "Cannondale", "Mountain", 2022, 1000);
      addProduct("Domane AL 2", "Trek", "Road", 2023, 1050);
      addProduct("Contend AR 1", "Giant", "Road", 2022, 1800);
      addProduct("Rockhopper Elite 29", "Specialized", "Mountain", 2021, 1350);
      addProduct("Crosstrail Elite", "Specialized", "Hybrid", 2022, 1400);
      addProduct("Quick 3", "Cannondale", "Hybrid", 2023, 900);
      addProduct("Sirrus 2.0", "Specialized", "Hybrid", 2021, 700);
      addProduct("Domane XR9", "Trek", "Road", 2023, 2700);
      addProduct("Domane QF4", "Trek", "Gravel", 2022, 2500);
      addProduct("Domane ZT3", "Trek", "Road", 2023, 2999);
      addProduct("Domane PW6", "Trek", "Hybrid", 2021, 1800);
      addProduct("Domane CJ7", "Trek", "Mountain", 2023, 2400);
      addProduct("Talon V2", "Giant", "Electric", 2022, 2800);
      addProduct("Talon B5", "Giant", "Gravel", 2023, 2200);
      addProduct("Talon Y8", "Giant", "Road", 2022, 2600);
      addProduct("Domane DS1", "Trek", "Hybrid", 2021, 2100);
      addProduct("Domane NW9", "Trek", "Mountain", 2023, 2900);
      addProduct("Domane FK8", "Trek", "Road", 2023, 2700);
      addProduct("Domane RM7", "Trek", "Gravel", 2022, 2500);
      addProduct("Domane GT4", "Trek", "Hybrid", 2021, 2100);
      addProduct("Domane VS6", "Trek", "Mountain", 2023, 2800);
      addProduct("Domane BW3", "Trek", "Electric", 2022, 2900);
      addProduct("Domane YC9", "Trek", "Gravel", 2023, 2400);
      addProduct("Domane NL5", "Trek", "Road", 2022, 2600);
      addProduct("Domane QX2", "Trek", "Hybrid", 2021, 1800);
      addProduct("Domane JH1", "Trek", "Mountain", 2023, 2200);
      addProduct("Domane DL7", "Trek", "Electric", 2022, 2750);
    } catch (Exception e) {
      System.err.print(e);
      System.exit(0);
    }
  }

  public static void saveProductToFile() {

  }

  private static String generateProductID() {
    String chars = ID_TEMPLATE;
    StringBuilder id = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      id.append(chars.charAt(Utility.randInt(chars.length())));
    }

    return id.toString();
  }

  public static int productCount() {
    return products.size();
  }

  public static ArrayList<Product> getAllProduct() {
    ArrayList<Product> res = new ArrayList<Product>();

    for (String itemID : products.keySet()) {
      Product product = products.get(itemID);
      res.add(product);
    }

    return res;
  }

  public static String addProduct(String name, String brand, String category, int modelYear, double price)
      throws Exception {
    Product newProd = new Product("", "", "", "", -1, -1);

    String productID;

    do {
      productID = generateProductID();
    } while (products.containsKey(productID));

    newProd.setId(productID);
    newProd.setName(name);
    newProd.setBrandID(brand);
    newProd.setCategoryID(category);
    newProd.setModelYear(modelYear);
    newProd.setListPrice(price);

    products.put(productID, newProd);

    return productID;
  }

  public static String addProduct(Product product) {
    String productID;

    do {
      productID = generateProductID();
    } while (products.containsKey(productID));

    product.id = productID;
    products.put(productID, product);
    return productID;
  }

  public static Product removeProduct(String id) throws Exception {
    if (!products.containsKey(id)) {
      throw new Exception("not-found");
    }

    Product pro = products.get(id);
    products.remove(id);

    return pro;
  }

  public static String[] getColumn(String type) {
    StringBuilder str = new StringBuilder();

    for (Product prod : getAllProduct()) {
      str.append(prod.get(type) + "\n");
    }

    return str.toString().split("\n");
  }

  public static boolean validateID(String id) {
    if (id.length() != ID_LENGTH)
      return false;

    for (int i = 0; i < ID_LENGTH; i++) {
      String c = id.charAt(i) + "";

      if (!ID_TEMPLATE.contains(c)) {
        return false;
      }
    }

    return true;
  }

  public static ArrayList<Product> findProduct(String type, String query) {
    if (query.length() == 0) {
      return getAllProduct();
    }

    ArrayList<Product> res = new ArrayList<Product>();

    for (String itemID : products.keySet()) {
      Product product = products.get(itemID);
      if (product.match(type, query)) {
        res.add(product);
      }
    }

    return res;
  }

  public static void setProduct(String id, Product product) {
    products.put(id, product);
  }

  public static void onAppExit() {

  }
}
