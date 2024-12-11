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
public class Alcohol extends Beverage {
    
    // Indicates if the drink is ordered on a weekend
    private boolean isWeekend;
    
    // Additional cost applied if the drink is ordered on a weekend
    private final double WEEKEND_COST = 0.6;

  
    public Alcohol(String name, Size size, boolean isWeekend) {
        super(name, Type.ALCOHOL, size);
        this.isWeekend = isWeekend;
    }
    
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (obj instanceof Alcohol) {
            Alcohol other = (Alcohol) obj;
            return this.isWeekend == other.isWeekend;
        }
        return false;
    }
    public String getName() {
        return name;
    }

   
    public Size getSize() {
        return size;
    }

    @Override
    public double calcPrice() {
        double price = addSizePrice();  // Start with the base price adjusted for size
        if (isWeekend) {
            price += WEEKEND_COST;  // Add the weekend surcharge if applicable
        }
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + ", Weekend: " + isWeekend + ", Price: $" + calcPrice();
    }
}
