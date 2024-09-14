
package com.december.bikemanager.cli;

import java.util.Scanner;

import com.december.bikemanager.data.*;

public class CLIMainMenu extends CLIComponent {

    private String notice = "";

    public CLIMainMenu(Scanner scanner, String notice) {
        super(scanner);
        this.name = "Main Menu (MainMenu)";
        this.notice = notice;
    }

    @Override
    public void render() {
        println("BIKE MANAGEMENT SYSTEM - MAIN MENU\n");
        println("\n======== Notification =======");

        System.err.println(notice);

        System.err.println(ProductManager.productCount() + " products in database");

        println("\n========== Options ==========");
        println("1. Add a product");
        println("2. Search for products");
        println("3. Update a product");
        println("4. Delete product");
        println("5. Save products to file");
        println("6. Prints product from file\n");
        println("Enter anything else to exit\n");
        print("Choose an option: ");

        int choice = -1;
        try {
            String input = readln();
            choice = Integer.parseInt(input);

            if (choice < 0 || choice > 6) {
                choice = -1;
            }
        } catch (Exception e) {
            choice = -1;
        }

        this.navNext(choice);
    }
}
