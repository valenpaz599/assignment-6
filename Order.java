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
import java.util.ArrayList;

public class Order implements OrderInterface, Comparable<Order> {

    // A unique number for identifying the order
    private int orderNumber;

    // The time the order was placed (e.g., in hours)
    private int orderTime;

    // The day the order was placed (e.g., MONDAY, TUESDAY, etc.)
    private Day orderDay;

    // The customer who placed the order
    private Customer customer;

    // A list of beverages included in the order
    private ArrayList<Beverage> beverages;

    public Order(int orderTime, Day orderDay, Customer customer) {
        this.orderNumber = (int) (Math.random() * 80001) + 10000; // Generate a random order number between 10000 and 90000
        this.orderTime = orderTime;
        this.orderDay = orderDay;
        this.customer = new Customer(customer); // Create a deep copy of the customer
        this.beverages = new ArrayList<>();     // Initialize the list of beverages
    }

    /**
     * Determines if the order was placed on a weekend (Saturday or Sunday).
     */
    @Override
    public boolean isWeekend() {
        return orderDay == Day.SATURDAY || orderDay == Day.SUNDAY;
    }

    /**
     * Retrieves the beverage at the specified index in the order.
     */
    @Override
    public Beverage getBeverage(int itemNo) {
        if (itemNo >= 0 && itemNo < beverages.size()) {
            return beverages.get(itemNo);
        }
        return null;
    }

    /**
     * Adds a coffee beverage to the order.
     */
    @Override
    public void addNewBeverage(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
        beverages.add(new Coffee(bevName, size, extraShot, extraSyrup));
    }

    /**
     * Adds an alcohol beverage to the order.
     */
    @Override
    public void addNewBeverage(String bevName, Size size) {
        beverages.add(new Alcohol(bevName, size, isWeekend()));
    }

    /**
     * Adds a smoothie beverage to the order.
     */
    @Override
    public void addNewBeverage(String bevName, Size size, int numOfFruits, boolean addProtein) {
        beverages.add(new Smoothie(bevName, size, numOfFruits, addProtein));
    }

    /**
     * Calculates the total price of all beverages in the order.
     */
    @Override
    public double calcOrderTotal() {
        double total = 0;
        for (Beverage bev : beverages) {
            total += bev.calcPrice();
        }
        return total;
    }

    /**
     * Counts the number of beverages of a specific type in the order.
     */
    @Override
    public int findNumOfBeveType(Type type) {
        int count = 0;
        for (Beverage bev : beverages) {
            if (bev.type == type) {
                count++;
            }
        }
        return count;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns a string representation of the order, including order details and beverages.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(orderNumber).append("\n");
        sb.append("Order Time: ").append(orderTime).append("\n");
        sb.append("Order Day: ").append(orderDay).append("\n");
        sb.append("Customer: ").append(customer).append("\n");
        sb.append("Beverages:\n");
        for (Beverage bev : beverages) {
            sb.append("  - ").append(bev.toString()).append("\n");
        }
        sb.append("Total Price: $").append(calcOrderTotal());
        return sb.toString();
    }
    public int getTotalItems() {
        return beverages.size();
    }

    /**
     * Compares this order with another order based on their order numbers.
     */
    @Override
    public int compareTo(Order other) {
        return Integer.compare(this.orderNumber, other.orderNumber);
    }
}
