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


public class Smoothie extends Beverage {

    // The number of fruits added to the smoothie
    private int numFruits;

    // Indicates if protein powder is added to the smoothie
    private boolean addProtein;

    // Additional cost for adding protein powder
    private final double PROTEIN_COST = 1.5;

    // Cost for each additional fruit added to the smoothie
    private final double FRUIT_COST = 0.5;
    
    public Smoothie(String name, Size size, int numFruits, boolean addProtein) {
        super(name, Type.SMOOTHIE, size);
        this.numFruits = numFruits;
        this.addProtein = addProtein;
    }
    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }
    public int getNumFruits() {
        return numFruits;
    }

    
    public boolean hasProtein() {
        return addProtein;
    }
    
    
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (obj instanceof Smoothie) {
            Smoothie other = (Smoothie) obj;
            return this.numFruits == other.numFruits && this.addProtein == other.addProtein;
        }
        return false;
    }

    @Override
    public double calcPrice() {
        double price = addSizePrice();            // Start with the base price adjusted for size
        price += numFruits * FRUIT_COST;          // Add cost for each fruit
        if (addProtein) {
            price += PROTEIN_COST;                // Add cost for protein powder if selected
        }
        return price;
    }

    /**
     * Returns a string representation of the smoothie.
     */
    @Override
    public String toString() {
        return super.toString() + ", Fruits: " + numFruits + ", Protein: " + addProtein + ", Price: $" + calcPrice();
    }
	public String getName1() {
		
		return name ;
	}
}
