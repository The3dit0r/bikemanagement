package com.december.bikemanager.cli;

import java.util.List;
import java.util.Scanner;

import com.december.bikemanager.Bikemanager;
import com.december.bikemanager.data.ProductManager;
import com.december.bikemanager.data.Product;
import com.december.bikemanager.data.Utility;

public class CLIProductDetails extends CLIComponent {

  private static String curID = "";
  private Product curProduct = null;
  private Product newProduct = null;
  private String notice = "";

  public void saveProduct() {
    try {

      curProduct.setName(newProduct.getName());
      curProduct.setBrandID(newProduct.getBrandID());
      curProduct.setCategoryID(newProduct.getCategoryID());
      curProduct.setListPrice(newProduct.getListPrice());
      curProduct.setModelYear(newProduct.getModelYear());

      ProductManager.setProduct(curID, curProduct);

      setNotice("Product saved: " + curProduct);
    } catch (Exception e) {
      setNotice("Failed to save product\n" + e.getMessage());
    }
  }

  public Product loadProduct() {
    List<Product> res = ProductManager.findProduct("id", curID);
    if (res.size() == 0) {
      return null;
    }

    return res.get(0);
  }

  public static void showProduct(String id) {
    if (!ProductManager.validateID(id)) {
      return;
    }

    curID = id;
    Bikemanager.navPanel(7);
  }

  public CLIProductDetails(Scanner scanner) {
    super(scanner);
    this.name = "Product Details (CLIProductDetails)";
  }

  private void setNotice(String a) {
    this.notice = a;
  }

  private String printNotice() {
    if (Utility.isnt(this.notice)) {
      return "";
    }

    return ("! " + this.notice);
  }

  private void handleEditing(String inp) {
    if (curProduct == null) {
      return;
    }

    String[] args = inp.split(" ");
    String cmd = args[0];
    StringBuilder q = new StringBuilder();

    for (int i = 1; i < args.length; i++) {
      q.append(args[i] + " ");
    }

    String val = q.toString().trim();
    boolean revert = val.equals(".rv");

    try {
      switch (cmd) {
        case ".en": {
          newProduct.setName(revert ? curProduct.name : val);
          break;
        }

        case ".eb": {
          newProduct.setBrandID(revert ? curProduct.brandID : val);
          break;
        }

        case ".ec": {
          newProduct.setCategoryID(revert ? curProduct.categoryID : val);
          break;
        }

        case ".ey": {
          newProduct.setModelYear(revert ? curProduct.modelYear + "" : val);
          break;
        }

        case ".ep": {
          newProduct.setListPrice(revert ? curProduct.listPrice + "" : val);
          break;
        }

        default:
          return;
      }
    } catch (Exception e) {
      return;
    }
  }

  private String compare(String type) {
    String _new = newProduct.get(type);
    String _old = curProduct.get(type);

    if (_new.equals(_old))
      return "  " + _old;

    return "* " + _new;
  }

  @Override
  public void render() {
    println("BIKE MANAGEMENT SYSTEM - PRODUCT DETAILS\n");

    if (this.curProduct == null) {
      Product loadProd = loadProduct();

      if (loadProd != null) {
        curProduct = loadProd;
        newProduct = loadProd.clone();
        return;
      }

      println("An error occured displaying the product");
      println("Press Enter to return");

      readln();
      navBack();
    }

    println("'.c': Discard changes and return to previous menu");
    println("'.save': Save all changes");
    println("'.revert': Revert all changes");

    println(String.format("\nProduct info [ID %s]:", curID));

    String[] info = {
        "[cmd]",
        "[.en] Name:             " + compare("name"),
        "[.eb] Brand ID:         " + compare("brand"),
        "[.ec] Category ID:      " + compare("category"),
        "[.ey] Model year:       " + compare("year"),
        "[.ep] List price:       " + compare("price"),
    };

    for (int i = 0; i < info.length; i++) {
      println(info[i]);
    }

    println("\n", "-".repeat(46));
    println("* To edit product info, type [cmd] [new_value]");
    println("  Example: '.en Product New Name'\n");

    println("* To revert the changes, type [cmd] .rv");
    println("  Example: '.en .rv'");
    println("-".repeat(46), "\n", printNotice(), "\n");

    print("> ");
    String input = readln();

    if (input.equals(".c")) {
      navBack();
      return;
    }

    if (input.equals(".revert")) {
      handleEditing(".en .rv");
      handleEditing(".eb .rv");
      handleEditing(".ec .rv");
      handleEditing(".ey .rv");
      handleEditing(".ep .rv");

      return;
    }

    if (input.equals(".save")) {
      print("Confirm your action by typing 'y': ");
      String confirm = readln();

      if (confirm.equals("y")) {
        saveProduct();
      } else {
        setNotice("Saving canceled");
      }
    }

    handleEditing(input);
  }
}
