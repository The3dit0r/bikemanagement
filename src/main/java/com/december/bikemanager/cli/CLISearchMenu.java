package com.december.bikemanager.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.december.bikemanager.data.ProductManager;
import com.december.bikemanager.data.Product;
import com.december.bikemanager.data.Utility;

public class CLISearchMenu extends CLIComponent {
  private int page = 0;
  private int limit = 15;
  private String query = "";
  private String type = "";

  public CLISearchMenu(Scanner scanner) {
    super(scanner);
    this.name = "Search Panel (SearchPanel)";
  }

  private void printTable(List<Product> products) {

    if (products.size() == 0) {
      println("");
      return;
    }

    products.add(0, new Product("", "", "", "", -1, -1));

    String columns[] = {
        "id", "name", "brand", "category", "year", "price"
    };

    HashMap<String, StringBuilder> rows = new HashMap<String, StringBuilder>();

    int maxLen = 0;

    for (int i = 0; i < columns.length; i++) {
      String col = columns[i];

      int prevMax = maxLen;
      maxLen = 0;

      for (Product product : products) {
        String id = product.id;
        StringBuilder row = rows.get(id);

        int last = i - 1 < 0 ? prevMax : product.get(columns[i - 1]).length();

        String sep = " ".repeat(prevMax - last + 5);
        String value = product.get(col);

        maxLen = Math.max(value.length(), maxLen);

        if (Utility.is(row)) {
          row.append(sep + value);
        } else {
          rows.put(id, new StringBuilder(sep + value));
        }
      }
    }

    int i = 0;

    for (String rowID : rows.keySet()) {
      i++;

      String line = rows.get(rowID).toString().trim();
      println(line);

      if (i == 1 || i == rows.size()) {
        println("-".repeat(line.length() + 3));
      }
    }
  }

  public void reset() {
    this.query = "";
    this.page = 0;
  }

  private void setQuery(String q) {
    this.query = q;
    this.page = 0;
  }

  @Override
  public void init() {
    super.init();
  }

  public void render() {
    ArrayList<Product> products = ProductManager.findProduct(type, query);

    int end = Math.min(products.size(), limit * (page + 1));
    int start = Math.min(end, page * limit);

    List<Product> showing = products.subList(start, end);

    println("BIKE MANAGEMENT SYSTEM - ALL PRODUCTS\n");

    println("'.c': Return to main menu");
    println("'.n': Navigate to next page");
    println("'.p': Navigage to previous page\n");

    if (end - start > 0) {
      println(String.format("Page: %d (%d - %d out of %d)", page + 1, start + 1, end, products.size()));
    }

    println(String.format("Showing %d entries.", showing.size()));

    printTable(showing);

    println("Search for product by name");
    println("You can change this by using .s <type>\n");
    print(Utility.is(query) ? String.format("> ('%s') ", query) : "> ");

    String input = this.readln();

    if (input.equals(".c")) {
      navBack();
      reset();
      return;
    }

    double maxPageDiv = products.size() / this.limit;
    int maxPage = (int) Math.ceil(maxPageDiv);

    if (input.equals(".n")) {
      this.page = this.page + 1 > maxPage ? 0 : this.page + 1;
      return;
    }

    if (input.equals(".p")) {
      this.page = this.page - 1 < 0 ? maxPage : this.page - 1;
      return;
    }

    // print("You search for:", query);
    setQuery(input);
  }
}
