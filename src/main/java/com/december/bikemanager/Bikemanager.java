
package com.december.bikemanager;

import java.util.ArrayList;
import java.util.Scanner;

import com.december.bikemanager.cli.CLIAddMenu;
import com.december.bikemanager.cli.CLIComponent;
import com.december.bikemanager.cli.CLIMainMenu;
import com.december.bikemanager.cli.CLIProductDetails;
import com.december.bikemanager.cli.CLISearchMenu;
import com.december.bikemanager.cli.CLIUpdateProduct;
import com.december.bikemanager.data.ProductManager;

import java.lang.ArrayIndexOutOfBoundsException;

public class Bikemanager {
    public static ArrayList<Integer> panelIndex = new ArrayList<Integer>();
    public static int componentsLength;

    public static void navBack() {
        panelIndex.remove(0);
    }

    public static void navPanel(int panel) {
        if (panel < 0 && panel > componentsLength) {
            return;
        }

        panelIndex.add(0, panel);
    }

    public static void setPanel(int panel) {
        navPanel(panel);
    }

    public static void setPanel(int panel, boolean clearAll) {
        if (clearAll) {
            panelIndex.clear();
        }

        setPanel(panel);
    }

    public static void main(String[] args) {
        ProductManager.loadProductsFromFile();
        navPanel(0);

        // Product product = new Product(1, "Bike", "Brand", "Category", 2017, 200.00);
        Scanner scanner = new Scanner(System.in);
        String mainMenuNotice = "";

        CLIComponent[] components = {
                new CLIMainMenu(scanner, mainMenuNotice),
                new CLIAddMenu(scanner),
                new CLISearchMenu(scanner),
                new CLIUpdateProduct(scanner),
                new CLIComponent(scanner),
                new CLIComponent(scanner),
                new CLIComponent(scanner),
                new CLIProductDetails(scanner)
        };

        componentsLength = components.length;

        // * Main loop
        int curPanelCode = 0;
        while (panelIndex.size() != 0 && curPanelCode != -1) {
            try {
                curPanelCode = panelIndex.get(0);
                CLIComponent current = components[curPanelCode];

                current.init();
                current.render();
                current.clean();

                mainMenuNotice = current.menuNotice;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Exiting app...");

            } catch (Exception e) {

                System.out.println("DBG: There was an error rendering");
                System.out.println("DBG: Exiting app\n\nError log:");

                curPanelCode = -1;

                System.err.println(e);
            }
        }

        ProductManager.onAppExit();
        scanner.close();
        System.out.println("DBG: Render cycle ended");
    }
}
