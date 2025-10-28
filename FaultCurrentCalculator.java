import java.util.InputMismatchException;
import java.util.Scanner;

    // ----------------------------------------------------------------------------------
public class FaultCurrentCalculator{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var calculator = new FaultCurrent();

        double mvaBase = 0, kvBase = 0, zThPerUnit = 0;

        System.out.println("\n--- Symmetrical Fault Current Calculator ---");
        System.out.println("Please enter the INPUT SYSTEM PARAMETERS (R, X, etc. must be pre-calculated into Zth(pu)):");

        try {
            // --- USER INPUT: System Base Values ---
            System.out.print("Enter System Base MVA (MVA_base): ");
            mvaBase = scanner.nextDouble();

            System.out.print("Enter System Base kV at Fault Location (kV_base): ");
            kvBase = scanner.nextDouble();

            // --- USER INPUT: Thevenin Impedance (Simplified) ---
            // In a full tool, this would be computed internally from component data.
            System.out.print("Enter Thevenin Equivalent Per-Unit Impedance (Zth(pu)): ");
            zThPerUnit = scanner.nextDouble();

            if (mvaBase <= 0 || kvBase <= 0 || zThPerUnit <= 0) {
                System.out.println("\nERROR: MVA Base, kV Base, and Zth(pu) must be positive values.");
                return;
            }

            // --- CALCULATION ---
            double[] results = calculator.calculateFault(mvaBase, kvBase, zThPerUnit);

            // --- OUTPUT CALCULATION RESULTS ---
            System.out.println("\n--------------------------------------------------");
            System.out.println("OUTPUT CALCULATION RESULTS:");
            System.out.println("--------------------------------------------------");
            System.out.printf("Fault Current (kA): %.3f kA\n", results[0]);
            System.out.printf("Fault MVA: %.3f MVA\n", results[1]);
            System.out.printf("Calculated Breaker Rating (kA): %.3f kA\n", results[0]); // Using I_fault_kA
            System.out.printf("Recommended Breaker kA Rating: %.3f kA\n", results[2]); // Using 1.25 * I_fault_kA
            System.out.println("--------------------------------------------------");

        } catch (InputMismatchException e) {
            System.out.println("\nERROR: Invalid input. Please enter numerical values only.");
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
