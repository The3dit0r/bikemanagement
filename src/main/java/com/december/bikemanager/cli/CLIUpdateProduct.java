package com.december.bikemanager.cli;

import java.util.Scanner;

import com.december.bikemanager.data.ProductManager;
import com.december.bikemanager.data.Product;
import com.december.bikemanager.data.Utility;

import java.util.List;

public class CLIUpdateProduct extends CLIComponent {

  public CLIUpdateProduct(Scanner scanner) {
    super(scanner);

    this.name = "Update Product (CLIUpdateProduct)";
  }

  @Override
  public void render() {
    println("BIKE MANAGEMENT SYSTEM - UPDATE PRODUCT\n");

    println("'.c': Return to main menu");
    println("'.s': Use search panel\n");

    if (Utility.is(errorText)) {
      println(errorText);
    }

    print("Enter product ID: ");
    String input = readln();

    if (input.equals(".c")) {
      navBack();
      return;
    }

    if (input.equals(".s")) {
      navNext(2);
      return;
    }

    if (!ProductManager.validateID(input)) {
      errorText = "! Error: Invalid ID";
      return;
    }

    String query = input.toUpperCase();
    List<Product> res = ProductManager.findProduct("id", query);

    if (res.size() == 0) {
      errorText = "! Error: No results found!";
      return;
    }

    errorText = "";
    CLIProductDetails.showProduct(query);
  }
}
