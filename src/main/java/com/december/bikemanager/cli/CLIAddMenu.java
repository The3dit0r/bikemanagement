package com.december.bikemanager.cli;

import java.util.Scanner;

import com.december.bikemanager.data.ProductManager;
import com.december.bikemanager.data.Product;
import com.december.bikemanager.data.Utility;

public class CLIAddMenu extends CLIComponent {
  private Product localProduct;
  private String confirmCode;
  private int curStep = 1;

  public CLIAddMenu(Scanner scanner) {
    super(scanner);
    this.name = "Add Product (CLIAddMenu)";
    reset();
  }

  private void reset() {
    this.lastInput = "";
    this.localProduct = new Product("", "-", "", "", -1, -1);
    this.confirmCode = "";
    this.curStep = 1;
    // this.menuNotice = "-";
  }

  private void handleError(Exception e) {
    this.errorText = "! Error: " + e.getMessage();
  }

  private boolean checkForStopSignal() {
    if (Utility.isnt(lastInput)) {
      return false;
    }

    return lastInput.equals(".c");
  }

  @Override
  public void render() {

    // * '.c' is detected in the previous input
    if (checkForStopSignal()) {
      backToMenu();
      reset();

      this.menuNotice = "Adding product canceled ('.c' is typed)";

      return;
    }

    println("BIKE MANAGEMENT SYSTEM - ADD PRODUCT");

    String preText = "* Enter new product info (" + curStep + ")\n";
    preText = preText + "* Type .c any time to cancel\n";
    preText = preText + errorText + "\n";

    String[] info = {
        curStep != 6 ? preText : "* Confirm New Product Info\n",
        "  Name: " + localProduct.getName(),
        "  Brand ID: " + localProduct.getBrandID(),
        "  Category ID: " + localProduct.getCategoryID(),
        "  Model year: " + localProduct.getModelYear(),
        "  List price: " + localProduct.getListPrice() + "$"
    };

    int maxLen = info[0].length();
    for (int i = 0; i < curStep; i++) {
      maxLen = Math.max(maxLen, info[i].length());
      println(info[i]);
    }

    println("-".repeat(maxLen + 4));

    try {
      switch (curStep) {
        case 0: {
          // Dunno why we need this but we do
          // (Nevermind fixed it yay)
          // readln();
          break;
        }

        case 1: {
          print("> Name: ");
          localProduct.setName(readln());
          break;
        }

        case 2: {
          print("> Brand ID: ");
          localProduct.setBrandID(readln());
          break;
        }

        case 3: {
          print("> Category ID: ");
          localProduct.setCategoryID(readln());
          break;
        }

        case 4: {
          print("> Model year: ");
          localProduct.setModelYear(readln());
          break;
        }

        case 5: {
          print("> List price: ");
          localProduct.setListPrice(readln());
          break;
        }

        case 6: {
          print("Confirm addition ('y'): ");
          this.confirmCode = readln().toLowerCase();
          break;
        }

        default:
          // Do we need anything here?
          // Probably not
          break;
      }

      if (curStep < 6) {
        curStep = curStep + 1;
        this.errorText = "";
        return;
      }

      if (confirmCode.equals("y")) {
        String productID = ProductManager.addProduct(localProduct.clone());
        this.menuNotice = "New product added, reference ID: " + productID;
      } else {
        this.menuNotice = "Product addition canceled";
      }

      // * Reset point
      reset();
      print(this.menuNotice, "\nAdd more ('y'): ");

      String addMore = readln().toLowerCase();
      if (addMore.equals("y")) {
        return;
      }

    } catch (Exception e) {
      handleError(e);
      return;
    }

    backToMenu();
  }

}
