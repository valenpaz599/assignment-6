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

public class Customer {
    
    // The name of the customer
    private String name;
    
    // The age of the customer
    private int age;

    /**
     * Constructor to create a Customer with the specified name and age.
     */
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Copy constructor to create a new Customer object by copying another Customer.
     */
    public Customer(Customer other) {
        this.name = other.name;
        this.age = other.age;
    }

    /**
     * Returns a string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer: " + name + ", Age: " + age;
    }
    
    public int getAge() {
        return age;
    }

}

