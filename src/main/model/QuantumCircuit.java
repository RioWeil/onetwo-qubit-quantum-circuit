package model;

import java.util.Random;

public abstract class QuantumCircuit {
    protected Gates gates;
    protected Random random;

    // REQUIRES: gate is one of "X", "Y", "Z", "H", "T", "S", "I", a two-character combination of those 7, or "CN".
    // MODIFIES: this
    // EFFECTS: Adds a gate to gates, and returns "Added <> gate to list."
    public String addGate(String gate) {
        gates.addGate(gate);
        return "Added " + gate + " gate to list.";
    }

    // EFFECTS: Produces the first gate in gates, or returns "No gates in list" if empty.
    public String seeFirstGate() {
        if (gates.isEmpty()) {
            return "No gates in list.";
        } else {
            return "The next gate is " + gates.seeNextGate() + ".";
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes first gate from gates and returns "Removed <> gate.", or "No gates in list to remove" if empty.
    public String removeGate() {
        if (gates.isEmpty()) {
            return "No gates in list to remove.";
        } else {
            String nextgate = gates.seeNextGate();
            gates.removeNextGate();
            return "Removed " + nextgate + " gate.";
        }
    }

    // MODIFIES: this
    // EFFECTS: Applies the first gate in list to the qubit, and removes the gate from gates.
    //          Returns "Applied <> gate", or if empty, does nothing and returns "No gates in list to apply."
    protected abstract String applyGate();

    // MODIFIES: this
    // EFFECTS: Applies all gates in the list to the qubit, and clears gates to be empty.
    //          Returns "Applied all gates in list.", or if empty, does nothing and returns "No gates in list to apply."
    public String applyAllGates() {
        if (gates.isEmpty()) {
            return "No gates in list to apply.";
        } else {
            while (! gates.isEmpty()) {
                applyGate();
            }
            return "Applied all gates in list.";
        }
    }

    // EFFECTS: Returns description of current qubit state as a string.
    protected abstract String returnState();

    // EFFECTS: Returns description of probabilities of the different measurement outcomes of the current qubit system.
    protected abstract String returnProbabilities();

    // MODIFIES: this
    // EFFECTS: Measures the qubit system and collapses it to a pure state. Returns a report of the measurement outcome.
    protected abstract String makeMeasurement();

    // EFFECTS: Produces string of the form "#.###" from double (or "-#.###" if negative).
    // String formatting from https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
    protected String formatDoubleToString(double d) {
        if (d == 0 || d == 1) {
            return String.format("%.4g", d);
        } else {
            return String.format("%.3g", d);
        }
    }

}
