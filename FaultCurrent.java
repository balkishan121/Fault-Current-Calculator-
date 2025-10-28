/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author balki
 */
class FaultCurrent {
    /**
     * Calculates the Symmetrical Fault Current and related ratings based on Thevenin Impedance.
     * @param mvaBase System Base MVA.
     * @param kvBase System Base kV at the fault location.
     * @param zThPerUnit The Thevenin equivalent per-unit impedance (Zth(pu)) at the fault point.
     * @return An array of calculated results: [0] I_fault (kA), [1] Fault MVA, [2] Recommended Breaker kA Rating
     */
    public double[] calculateFault(double mvaBase, double kvBase, double zThPerUnit) {
        // 1. Calculate Per-Unit Fault Current (I_fault_pu)
        // I_fault_pu = V_pu / Z_th_pu. Assuming V_pu = 1.0 (pre-fault voltage)
        double iFaultPerUnit = 1.0 / zThPerUnit;

        // 2. Calculate Fault MVA (MVA_fault)
        // MVA_fault = I_fault_pu * MVA_base
        double mvaFault = iFaultPerUnit * mvaBase;

        // 3. Calculate Base Current (I_base) for the fault location
        // I_base (kA) = MVA_base / (sqrt(3) * kV_base)
        double iBase = mvaBase / (Math.sqrt(3) * kvBase);

        // 4. Calculate Symmetrical Fault Current (I_fault_kA)
        // I_fault_kA = I_fault_pu * I_base
        double iFault_kA = iFaultPerUnit * iBase;

        // 5. Calculate Recommended Breaker kA Rating
        // Breaker Rating (kA) = 1.25 * I_fault_kA (Applying 1.25 safety factor)
        double recommendedBreaker_kA = 1.25 * iFault_kA;

        // Store and return results
        // [0] I_fault (kA), [1] Fault MVA, [2] Recommended Breaker kA Rating
        return new double[]{iFault_kA, mvaFault, recommendedBreaker_kA};
    }
    
}
