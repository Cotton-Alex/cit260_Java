/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Scanner;
import Control.GameControl;
import Exceptions.GameControlException;
import GOTG.GOTG;
import Model.Game;
import Model.Item;
import Model.ItemType;

/**
 *
 * @author alexcotton
 */
public class InventoryMenuView extends View {

    public InventoryMenuView() {
        super("\n" + "\n----------------------------------"
                + "\n|  Inventory                     |"
                + "\n----------------------------------"
                + "\nG - Groot's Inventory"
                + "\nS - Ship's Inventory"
                + "\nC - Display Groot's coin total"
                + "\nF - Inventory Report"
                + "\nR - Item's Starting Location Report"
                + "\nQ - Back"
                + "\n----------------------------------");
    }

    @Override
    public boolean doAction(String choice) {

        choice = choice.toUpperCase(); // convert choice to upper case

        switch (choice) {
            case "G": // Groot's Inventory
                this.grootsInventory();
                break;
            case "S": // Ship's Inventory
                this.shipsInventory();
                break;
            case "C": //Display Groot's coin total
                this.coinInventory();
                break;
            case "F":
                this.saveInventoryReport();
                break;
            case "R":
                this.saveItemLocationReport();
                break;
            default:
                ErrorView.display(this.getClass().getName(), "\n*** Invalid selection *** Try again");
                break;
        }

        return false;
    }

    private void grootsInventory() {
StringBuilder line;
        Game game = GOTG.getCurrentGame();
        Item[] items = game.getInventory();
        this.console.println(
                "--------------------------------------------------------------\n"
                + "|                    Inventory Items                    |\n"
                + "--------------------------------------------------------------");
        this.console.println("Amount\t\tItem");
        this.console.println("--------------------------------------------------------------");
        for (int i = 0; i < ItemType.values().length; i++) {
            this.console.println(items[i].getQuantityInStock() +  "\t\t" + items[i].getName());
}
        this.console.println("--------------------------------------------------------------");

    }










//        int j = 0;
//        Game game = GOTG.getCurrentGame();
//
//        
//
//        Item[] items = game.getInventory();
//this.console.print("" + "Items" );
//this.console.print("      " + "      " + "Amount\n");
//        for (int i = 0; i < ItemType.values().length; i++) {
//            if (items[i].getQuantityInStock() >= 1) {
//                this.console.print("" + items[i].getName());
//                this.console.print("      " + "      " + items[i].getQuantityInStock() + "\n");
//                j++;
//            }
//        }
//        if (j == 0){
//            this.console.println("\n You have no items in your inventory.");
//        }
       
    

    private void shipsInventory() {
        System.out.println(
                "*** List the items that are stored on the Groot's ship ***");
    }

    private void coinInventory() {
        System.out.println("*** Display the total coins Groot is carrying ***");
    }

    private void saveInventoryReport() {
        //prompt for and get the name of the file to save the report
        String saveMenu = display;
        display = ("\n\nEnter the file path for the file where the report is to be saved.");
        String filePath = this.getInput();
        
        InventoryItemsView inventoryItems = new InventoryItemsView();

        try {
            //save the report to the specified file
            GameControl.saveInventoryReport(inventoryItems, filePath);
            this.console.println("Inventory Report successfully saved to " + filePath);
        } catch (Exception ex) {
            ErrorView.display("MainMenuView", ex.getMessage());
        }
        display = saveMenu;
    }

    //LocationReport:
    private void saveItemLocationReport() {
        //prompt for and get the name of the file to save the report
        String saveMenu = display;
        display = ("\n\nEnter the file path for the file where the report is to be saved.");
        String filePath = this.getInput();
        
        ItemStartLocationsView itemStartLocations = new ItemStartLocationsView();

        try {
            //save the report to the specified file
            GameControl.saveReport(itemStartLocations, filePath);
            this.console.println("Item Locations Report successfully saved to " + filePath);
        } catch (Exception ex) {
            ErrorView.display("MainMenuView", ex.getMessage());
        }
        display = saveMenu;
    }
}
