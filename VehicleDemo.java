class Vehicle {

    // -------- Data Members --------
    public String brandName;          // public
    public String modelName;          // public

    private String fuelType;          // private
    private double distanceTravelled; // private (in km)
    private double fuelConsumed;      // private (in liters)

    // -------- Constructors --------

    // Default Constructor
    public Vehicle() {
        brandName = "Unknown";
        modelName = "Unknown";
        fuelType = "Unknown";
        distanceTravelled = 0;
        fuelConsumed = 0;
    }

    // Parameterized Constructor
    public Vehicle(String brandName, String modelName,
                   String fuelType, double distanceTravelled,
                   double fuelConsumed) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.distanceTravelled = distanceTravelled;
        this.fuelConsumed = fuelConsumed;
    }

    // -------- Getters and Setters --------
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public double getFuelConsumed() {
        return fuelConsumed;
    }

    public void setFuelConsumed(double fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    // -------- Member Methods --------
    public void start() {
        System.out.println(modelName + " is starting...");
    }

    public void stop() {
        System.out.println(modelName + " is stopping...");
    }

    public void accelerate() {
        System.out.println(modelName + " is accelerating...");
    }

    public double calculateMileage() {
        if (fuelConsumed == 0)
            return 0;
        return distanceTravelled / fuelConsumed;
    }

    // -------- Display Method --------
    public void displayDetails() {
        System.out.printf("%-10s %-10s %-10s %-10.2f%n",
                brandName,
                modelName,
                fuelType,
                calculateMileage());
    }
}

// -------- Main Class --------
public class VehicleDemo {
    public static void main(String[] args) {

        Vehicle v1 = new Vehicle("Toyota", "Corolla", "Petrol", 500, 25);
        Vehicle v2 = new Vehicle("Hyundai", "Verna", "Diesel", 600, 20);
        Vehicle v3 = new Vehicle("Tata", "Nexon", "Electric", 300, 50);
        Vehicle v4 = new Vehicle("Honda", "City", "Petrol", 450, 22);
        Vehicle v5 = new Vehicle("Mahindra", "XUV300", "Diesel", 520, 26);

        // Calling methods
        v1.start();
        v1.accelerate();
        v1.stop();

        System.out.println("\nVehicle Mileage Comparison");
        System.out.println("------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s%n",
                "Brand", "Model", "Fuel", "Mileage");
        System.out.println("------------------------------------------------");

        v1.displayDetails();
        v2.displayDetails();
        v3.displayDetails();
        v4.displayDetails();
        v5.displayDetails();
    }
}
