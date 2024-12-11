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
public abstract class Beverage {
    
    // The name of the beverage
    protected String name;
    
    // The type of the beverage (COFFEE, SMOOTHIE, ALCOHOL)
    protected Type type;
    
    // The size of the beverage (SMALL, MEDIUM, LARGE)
    protected Size size;
    
    // The base price for all beverages
    protected final double BASE_PRICE = 2.0;
    
    // The additional cost for increasing the size of the beverage
    protected final double SIZE_PRICE = 0.5;

   
    public Beverage(String name, Type type, Size size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }
    public double addSizePrice() {
        switch (size) {
            case SMALL:
                return BASE_PRICE;
            case MEDIUM:
                return BASE_PRICE + SIZE_PRICE;
            case LARGE:
                return BASE_PRICE + 2 * SIZE_PRICE;
            default:
                return BASE_PRICE;
        }
    }
    
    public Size getSize() {
        return size;
    }
    
    
    public String getName() {
        return name;
    }
    public abstract double calcPrice();

    @Override
    public String toString() {
        return name + " (" + size + ")";
    }
    
    public Type getType() {
        return type;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                 // Check if both references point to the same object
        if (obj == null || getClass() != obj.getClass()) return false;  // Check if the object is null or of a different class
        Beverage other = (Beverage) obj;              // Cast the object to Beverage
        return name.equals(other.name) && type == other.type && size == other.size;
    }
}

