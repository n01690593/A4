import java.util.Scanner;

public class HouseholdEnergyBillCalculator {

    // Base class
    static class EnergyBill {
        // Attributes
        private String customerName;
        private String address;
        private double electricityUsage; // in kWh
        private double gasUsage; // in units

        // Rates
        private static final double ELECTRICITY_RATE = 0.12; // rate per kWh
        private static final double GAS_RATE = 0.08; // rate per unit

        // Constructor
        public EnergyBill(String customerName, String address, double electricityUsage, double gasUsage) {
            this.customerName = customerName;
            this.address = address;
            this.electricityUsage = electricityUsage;
            this.gasUsage = gasUsage;
        }

        // Method to calculate total charges
        public double calculateCharges() {
            return (electricityUsage * ELECTRICITY_RATE) + (gasUsage * GAS_RATE);
        }

        // Method to display the bill
        public void displayBill() {
            System.out.println("Customer Name: " + customerName);
            System.out.println("Address: " + address);
            System.out.println("Electricity Usage: " + electricityUsage + " kWh");
            System.out.println("Gas Usage: " + gasUsage + " units");
            System.out.println("Total Charges: $" + String.format("%.2f", calculateCharges()));
        }
    }

    // Derived class
    static class GreenEnergyBill extends EnergyBill {
        private double solarEnergyContribution; // in kWh

        // Solar energy deduction rate
        private static final double SOLAR_DEDUCTION_RATE = 0.05; // rate per kWh of solar energy

        // Constructor
        public GreenEnergyBill(String customerName, String address, double electricityUsage, double gasUsage, double solarEnergyContribution) {
            super(customerName, address, electricityUsage, gasUsage);
            this.solarEnergyContribution = solarEnergyContribution;
        }

        // Overriding the method to calculate total charges with solar energy deduction
        @Override
        public double calculateCharges() {
            double initialCharges = super.calculateCharges();
            double solarDeduction = solarEnergyContribution * SOLAR_DEDUCTION_RATE;
            return Math.max(0, initialCharges - solarDeduction); // Ensure bill does not go negative
        }

        // Overriding the method to display the bill with solar energy details
        @Override
        public void displayBill() {
            super.displayBill();
            System.out.println("Solar Energy Contribution: " + solarEnergyContribution + " kWh");
            System.out.println("Solar Energy Deduction: $" + String.format("%.2f", solarEnergyContribution * SOLAR_DEDUCTION_RATE));
            System.out.println("Total Bill After Solar Deduction: $" + String.format("%.2f", calculateCharges()));
        }
    }

    // Main class to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer details
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        // Get energy usage details
        System.out.print("Enter electricity usage (kWh): ");
        double electricityUsage = scanner.nextDouble();

        System.out.print("Enter gas usage (units): ");
        double gasUsage = scanner.nextDouble();

        // Ask if user uses solar energy
        System.out.print("Do you use solar energy? (yes/no): ");
        String useSolar = scanner.next().trim().toLowerCase();

        if (useSolar.equals("yes")) {
            System.out.print("Enter solar energy contribution (kWh): ");
            double solarContribution = scanner.nextDouble();

            // Create GreenEnergyBill instance
            GreenEnergyBill greenBill = new GreenEnergyBill(name, address, electricityUsage, gasUsage, solarContribution);
            greenBill.displayBill();
        } else {
            // Create EnergyBill instance
            EnergyBill bill = new EnergyBill(name, address, electricityUsage, gasUsage);
            bill.displayBill();
        }

        scanner.close();
    }
}