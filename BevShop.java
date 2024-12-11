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
import javax.swing.JOptionPane;


public class BevShop implements BevShopInterface {
    
    // List to store all the orders for the month
    private ArrayList<Order> orders;
    
    // The current order being processed
    private Order currentOrder;
    
    // The number of alcohol drinks in the current order
    private int numOfAlcoholDrinks;

    
    public BevShop() {
        orders = new ArrayList<>();
        numOfAlcoholDrinks = 0;
    }

    @Override
    public boolean isValidTime(int time) {
        return time >= MIN_TIME && time <= MAX_TIME;
    }

    @Override
    public int getMaxNumOfFruits() {
        return MAX_FRUIT;
    }

    @Override
    public int getMinAgeForAlcohol() {
        return MIN_AGE_FOR_ALCOHOL;
    }

    @Override
    public boolean isMaxFruit(int numOfFruits) {
        return numOfFruits > MAX_FRUIT;
    }

    @Override
    public int getMaxOrderForAlcohol() {
        return MAX_ORDER_FOR_ALCOHOL;
    }

    @Override
    public boolean isEligibleForMore() {
        return numOfAlcoholDrinks < MAX_ORDER_FOR_ALCOHOL;
    }

    @Override
    public int getNumOfAlcoholDrink() {
        return numOfAlcoholDrinks;
    }

    @Override
    public boolean isValidAge(int age) {
        return age >= MIN_AGE_FOR_ALCOHOL;
    }

    /*
     * Starts a new order with the specified details.
     */
    @Override
    public void startNewOrder(int time, Day day, String customerName, int customerAge) {
        if (!isValidTime(time)) {
            throw new IllegalArgumentException("Invalid time: Orders can only be placed between 8 AM and 11 PM.");
        }
        Customer customer = new Customer(customerName, customerAge);
        currentOrder = new Order(time, day, customer);
        orders.add(currentOrder);
        numOfAlcoholDrinks = 0; // Reset alcohol count for the new order
    }

    @Override
    public void processCoffeeOrder(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
        currentOrder.addNewBeverage(bevName, size, extraShot, extraSyrup);
    }
    
    @Override
    public void processAlcoholOrder(String bevName, Size size) {
        if (currentOrder.getCustomer().getAge() < MIN_AGE_FOR_ALCOHOL) {
            JOptionPane.showMessageDialog(null, "Your age is not appropriate for alcohol", "Age Restriction", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isEligibleForMore()) {
            currentOrder.addNewBeverage(bevName, size);
            numOfAlcoholDrinks++;
        } else {
            JOptionPane.showMessageDialog(null, "You have reached the maximum number of alcohol drinks for this order.", "Limit Reached", JOptionPane.WARNING_MESSAGE);
        }
    }


    @Override
    public void processSmoothieOrder(String bevName, Size size, int numOfFruits, boolean addProtein) {
        if (isMaxFruit(numOfFruits)) {
            System.out.println("Maximum number of fruits exceeded for Smoothie.");
            return;
        }
        currentOrder.addNewBeverage(bevName, size, numOfFruits, addProtein);
    }

    @Override
    public int findOrder(int orderNo) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber() == orderNo) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double totalOrderPrice(int orderNo) {
        int index = findOrder(orderNo);
        if (index != -1) {
            return orders.get(index).calcOrderTotal();
        }
        return 0.0;
    }

    @Override
    public double totalMonthlySale() {
        double totalSale = 0.0;
        for (Order order : orders) {
            totalSale += order.calcOrderTotal();
        }
        return totalSale;
    }

    @Override
    public int totalNumOfMonthlyOrders() {
        return orders.size();
    }

    @Override
    public Order getCurrentOrder() {
        return currentOrder;
    }

    
    @Override
    public Order getOrderAtIndex(int index) {
        if (index >= 0 && index < orders.size()) {
            return orders.get(index);
        }
        return null;
    }

    /**
     * Sorts the orders using their natural ordering.
     */
    @Override
    public void sortOrders() {
        orders.sort(null);
    }

    /**
     * Returns a string representation of all orders and the total monthly sale.
     * 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString()).append("\n\n");
        }
        sb.append("Total Monthly Sale: $").append(totalMonthlySale());
        return sb.toString();
    }
}

