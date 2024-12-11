/*
 * Class: CMSC203 
 * Instructor: Huseyin
 * Description: Online beverage market.
 * Due: 12/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Valentin Gabriel Paz
 */
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;


public class BevShopDriverApp {

    public static void main(String[] args) {
        BevShop bevShop = new BevShop();

        // Main loop to keep the application running until the user chooses to exit
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Welcome to Bradley Beverage Shop! What would you like to do?",
                    "BevShop Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Start New Order", "View Orders", "Exit"},
                    "Start New Order");

            if (choice == 0) {
                startNewOrder(bevShop);
            } else if (choice == 1) {
                viewOrders(bevShop);
            } else {
                JOptionPane.showMessageDialog(null, "Thank you for visiting Bradley Beverage Shop!", "Goodbye", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        }
    }

    /**
     * Starts a new order by prompting the user for order details such as time, customer name, and age.
     */
    private static void startNewOrder(BevShop bevShop) {
        try {
            int time = Integer.parseInt(JOptionPane.showInputDialog("Enter the order time (8-23):"));
            if (!bevShop.isValidTime(time)) {
                JOptionPane.showMessageDialog(null, "Invalid time! Orders can only be placed between 8 AM and 11 PM.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String customerName = JOptionPane.showInputDialog("Enter customer name:");
            int customerAge = Integer.parseInt(JOptionPane.showInputDialog("Enter customer age:"));

            Day day = getOrderDay();

            bevShop.startNewOrder(time, day, customerName, customerAge);

            processBeverages(bevShop);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prompts the user to select the day of the week for the order.
     */
    private static Day getOrderDay() {
        String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        int dayChoice = JOptionPane.showOptionDialog(null, "Select the order day:", "Order Day",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, days, days[0]);
        return Day.valueOf(days[dayChoice]);
    }

    /**
     * Processes the addition of beverages to the current order.
     */
    private static void processBeverages(BevShop bevShop) {
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Add a beverage to your order:",
                    "Add Beverage",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Add Coffee", "Add Alcohol", "Add Smoothie", "Finish Order"},
                    "Add Coffee");

            if (choice == 0) {
                addCoffee(bevShop);
            } else if (choice == 1) {
                addAlcohol(bevShop);
            } else if (choice == 2) {
                addSmoothie(bevShop);
            } else {
                JOptionPane.showMessageDialog(null, "Order completed!", "Order Complete", JOptionPane.PLAIN_MESSAGE);
                break;
            }
        }
    }

    /**
     * Adds a coffee beverage to the current order.
     */
    private static void addCoffee(BevShop bevShop) {
        String name = JOptionPane.showInputDialog("Enter coffee name:");
        Size size = getSize();
        boolean extraShot = JOptionPane.showConfirmDialog(null, "Add extra shot?", "Extra Shot", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        boolean extraSyrup = JOptionPane.showConfirmDialog(null, "Add extra syrup?", "Extra Syrup", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        bevShop.processCoffeeOrder(name, size, extraShot, extraSyrup);
    }

    /**
     * Adds an alcohol beverage to the current order.
     */
    private static void addAlcohol(BevShop bevShop) {
        String name = JOptionPane.showInputDialog("Enter alcohol name:");
        Size size = getSize();

        bevShop.processAlcoholOrder(name, size);
    }

    /**
     * Adds a smoothie beverage to the current order.
     */
    private static void addSmoothie(BevShop bevShop) {
        String name = JOptionPane.showInputDialog("Enter smoothie name:");
        Size size = getSize();
        int numFruits = Integer.parseInt(JOptionPane.showInputDialog("Enter number of fruits (max 5):"));
        boolean addProtein = JOptionPane.showConfirmDialog(null, "Add protein?", "Add Protein", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        bevShop.processSmoothieOrder(name, size, numFruits, addProtein);
    }

    /**
     * Prompts the user to select a size for the beverage.
     */
    private static Size getSize() {
        String[] sizes = {"SMALL", "MEDIUM", "LARGE"};
        int sizeChoice = JOptionPane.showOptionDialog(null, "Select the size:", "Select Size",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);
        return Size.valueOf(sizes[sizeChoice]);
    }

    /**
     * Displays a summary of all orders.
     */
    private static void viewOrders(BevShop bevShop) {
        StringBuilder ordersSummary = new StringBuilder();

        for (int i = 0; i < bevShop.totalNumOfMonthlyOrders(); i++) {
            ordersSummary.append(bevShop.getOrderAtIndex(i).toString()).append("\n\n");
        }

        if (ordersSummary.length() == 0) {
            JOptionPane.showMessageDialog(null, "No orders available.", "Orders", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, ordersSummary.toString(), "Orders", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
