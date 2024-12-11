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
public class Coffee extends Beverage {
    
    // Indicates if an extra shot of espresso is added
    private boolean extraShot;
    
    // Indicates if extra syrup is added
    private boolean extraSyrup;
    
    // Additional cost for each add-on (extra shot or extra syrup)
    private final double EXTRA_COST = 0.5;

    /**
     * Constructor to create a Coffee beverage with specified details.
     */
    public Coffee(String name, Size size, boolean extraShot, boolean extraSyrup) {
        super(name, Type.COFFEE, size);
        this.extraShot = extraShot;
        this.extraSyrup = extraSyrup;
    }

    /**
     * Calculates the price of the coffee.
     */
    @Override
    public double calcPrice() {
        double price = addSizePrice();  // Start with the base price adjusted for size
        if (extraShot) {
            price += EXTRA_COST;  // Add cost for extra shot if selected
        }
        if (extraSyrup) {
            price += EXTRA_COST;  // Add cost for extra syrup if selected
        }
        return price;
    }
    public boolean hasExtraShot() {
        return extraShot;
    }

   
    public boolean hasExtraSyrup() {
        return extraSyrup;
    }
    
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (obj instanceof Coffee) {
            Coffee other = (Coffee) obj;
            return this.extraShot == other.extraShot && this.extraSyrup == other.extraSyrup;
        }
        return false;
    }

    /**
     * Returns a string representation of the coffee beverage.
     */
    @Override
    public String toString() {
        return super.toString() + ", Extra Shot: " + extraShot + ", Extra Syrup: " + extraSyrup + ", Price: $" + calcPrice();
    }
}

