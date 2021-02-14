package ui;

import model.Complex;
import model.OneQubitQuantumCircuit;
import model.TwoQubitQuantumCircuit;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Quantum Circuit Simulation Application. Heavily based off of TellerApp.java
public class QuantumCircuitApp {
    private OneQubitQuantumCircuit oneQubit;
    private TwoQubitQuantumCircuit twoQubit;
    private Scanner input;

    // EFFECTS: Runs the Quantum Circuit Application
    public QuantumCircuitApp() {
        runQuantumCircuit();
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for choosing the size of the quantum circuit.
    private void runQuantumCircuit() {
        boolean keepGoing = true;
        String command;

        input = new Scanner(System.in);

        while (keepGoing) {
            qubitNumberSelectionMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processQubitNumberSelectionCommand(command);
            }

        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: Displays start menu, for showing the options for the # of qubits.
    private void qubitNumberSelectionMenu() {
        System.out.println("\nSelect From:");
        System.out.println("\n1 -> 1 Qubit Circuit");
        System.out.println("\n2 -> 2 Qubit Circuit");
        System.out.println("\nq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for choosing # of qubits.
    private void processQubitNumberSelectionCommand(String command) {
        if (command.equals("1")) {
            startOneQubitQuantumCircuit();
        } else if (command.equals("2")) {
            startTwoQubitQuantumCircuit();
        } else {
            System.out.println("Input is not valid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for choosing initial one qubit quantum state.
    private void startOneQubitQuantumCircuit() {
        String command;
        initialQubitStateSelectionMenuOneQubit();
        command = input.next();
        processQubitStateSelectionCommandOneQubit(command);

    }

    // EFFECTS: Displays menu of possible initial one qubit states.
    private void initialQubitStateSelectionMenuOneQubit() {
        System.out.println("\nSelect From:");
        System.out.println("\n0 -> Initial state of |0>");
        System.out.println("\n1 -> Initial state of |1>");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for choosing the initial one qubit quantum state.
    private void processQubitStateSelectionCommandOneQubit(String command) {
        if (command.equals("0")) {
            oneQubit = new OneQubitQuantumCircuit(new Complex(1, 0), new Complex(0, 0));
            controlOneQubitQuantumCircuit();
        } else if (command.equals("1")) {
            oneQubit = new OneQubitQuantumCircuit(new Complex(0, 0), new Complex(1, 0));
            controlOneQubitQuantumCircuit();
        } else {
            System.out.println("Input is not valid! Returning to main menu.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for controlling the one qubit system.
    private void controlOneQubitQuantumCircuit() {
        String command;
        qubitControlPanelOptions();
        command = input.next();
        oneQubitControlPanelCommand(command);
    }

    // EFFECTS: Displays menu of possible actions on the one qubit quantum circuit.
    private void qubitControlPanelOptions() {
        System.out.println("\nSelect From:");
        System.out.println("\n0 - view current qubit state");
        System.out.println("\n1 - get current qubit measurement probabilities");
        System.out.println("\n2 - see first quantum gate in list");
        System.out.println("\n3 - remove first quantum gate in list");
        System.out.println("\n4 - add quantum gate to list");
        System.out.println("\n5 - apply first quantum gate in list to qubit");
        System.out.println("\n6 - apply all quantum gates in list to qubit");
        System.out.println("\n7 - measure qubit");
        System.out.println("\nq - Return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for actions on the one qubit quantum circuit.
    private void oneQubitControlPanelCommand(String command) {
        if (command.equals("0")) {
            System.out.println("\n" + oneQubit.returnState());
        } else if (command.equals("1")) {
            System.out.println("\n" + oneQubit.returnProbabilities());
        } else if (command.equals("2")) {
            System.out.println("\n" + oneQubit.seeFirstGate());
        } else if (command.equals("3")) {
            System.out.println("\n" + oneQubit.removeGate());
        } else if (command.equals("4")) {
            oneQubitAddGate();
        } else if (command.equals("5")) {
            System.out.println("\n" + oneQubit.applyGate());
        } else if (command.equals("6")) {
            System.out.println("\n" + oneQubit.applyAllGates());
        } else if (command.equals("7")) {
            System.out.println("\n" + oneQubit.makeMeasurement());
        }  else if (! command.equals("q")) {
            System.out.println("\nInput is not valid!");
        }
        returnToMenuOrQuitOne(command);
    }

    // MODIFIES: this
    // EFFECTS: If "q", returns to main menu. Else, go back to the one qubit quantum circuit control panel.
    private void returnToMenuOrQuitOne(String command) {
        if (command.equals("q")) {
            System.out.println("\nReturning to main menu...");
        } else {
            controlOneQubitQuantumCircuit();
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for adding gates to the quantum circuit.
    private void oneQubitAddGate() {
        String command;
        oneQubitAddGateOptions();
        command = input.next();
        command = command.toUpperCase();
        oneQubitAddGateCommand(command);
    }

    // EFFECTS: Displays menu of possible 1-qubit gates that can be added.
    private void oneQubitAddGateOptions() {
        System.out.println("\nSelect From:");
        System.out.println("\nx - Pauli X Gate");
        System.out.println("\ny - Pauli Y Gate");
        System.out.println("\nz - Pauli Z Gate");
        System.out.println("\ns - S (sqrt(Z)) Gate");
        System.out.println("\nt - T (sqrt(S)) Gate");
        System.out.println("\ni - Identity Gate");
        System.out.println("\nh - Hadamard Gate");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for adding gates to the quantum circuit.
    private void oneQubitAddGateCommand(String command) {
        String[] allowedLetters = {"X", "Y", "Z", "S", "T", "I", "H"};
        List<String> allowedLettersList = Arrays.asList(allowedLetters);
        if (allowedLettersList.contains(command)) {
            System.out.println("\n" + oneQubit.addGate(command));
        } else {
            System.out.println("\nInput is not valid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for choosing initial two qubit quantum state.
    private void startTwoQubitQuantumCircuit() {
        String command;
        initialQubitStateSelectionMenuTwoQubits();
        command = input.next();
        processQubitStateSelectionCommandTwoQubits(command);
    }

    // EFFECTS: Displays menu of possible initial two qubit states.
    private void initialQubitStateSelectionMenuTwoQubits() {
        System.out.println("\nSelect From:");
        System.out.println("\n0 -> Initial state of |00>");
        System.out.println("\n1 -> Initial state of |01>");
        System.out.println("\n2 -> Initial state of |10>");
        System.out.println("\n3 -> Initial state of |11>");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for choosing the initial two qubit quantum state.
    private void processQubitStateSelectionCommandTwoQubits(String command) {
        Complex zero = new Complex(0, 0);
        Complex one = new Complex(1, 0);
        if (command.equals("0")) {
            twoQubit = new TwoQubitQuantumCircuit(one, zero, zero, zero);
            controlTwoQubitQuantumCircuit();
        } else if (command.equals("1")) {
            twoQubit = new TwoQubitQuantumCircuit(zero, one, zero, zero);
            controlTwoQubitQuantumCircuit();
        } else if (command.equals("2")) {
            twoQubit = new TwoQubitQuantumCircuit(zero, zero, one, zero);
            controlTwoQubitQuantumCircuit();
        } else if (command.equals("3")) {
            twoQubit = new TwoQubitQuantumCircuit(zero, zero, zero, one);
            controlTwoQubitQuantumCircuit();
        } else {
            System.out.println("Input is not valid! Returning to Main menu.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for controlling the two qubit system.
    private void controlTwoQubitQuantumCircuit() {
        String command;
        qubitControlPanelOptions();
        command = input.next();
        twoQubitControlPanelCommand(command);
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for actions on the one qubit quantum circuit.
    private void twoQubitControlPanelCommand(String command) {
        if (command.equals("0")) {
            System.out.println("\n" + twoQubit.returnState());
        } else if (command.equals("1")) {
            System.out.println("\n" + twoQubit.returnProbabilities());
        } else if (command.equals("2")) {
            System.out.println("\n" + twoQubit.seeFirstGate());
        } else if (command.equals("3")) {
            System.out.println("\n" + twoQubit.removeGate());
        } else if (command.equals("4")) {
            twoQubitAddGateOne();
        } else if (command.equals("5")) {
            System.out.println("\n" + twoQubit.applyGate());
        } else if (command.equals("6")) {
            System.out.println("\n" + twoQubit.applyAllGates());
        } else if (command.equals("7")) {
            System.out.println("\n" + twoQubit.makeMeasurement());
        }  else if (! command.equals("q")) {
            System.out.println("\nInput is not valid!");
        }
        returnToMenuOrQuitTwo(command);
    }

    // MODIFIES: this
    // EFFECTS: If "q", returns to main menu. Else, go back to the two qubit quantum circuit control panel.
    private void returnToMenuOrQuitTwo(String command) {
        if (command.equals("q")) {
            System.out.println("\nReturning to main menu...");
        } else {
            controlTwoQubitQuantumCircuit();
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for adding first gate to 2-qubit quantum circuit
    private void twoQubitAddGateOne() {
        String command;
        System.out.println("\nChoose the gate to act on the first qubit.");
        oneQubitAddGateOptions();
        System.out.println("\nc - CNOT/CN Gate (This acts on both qubits)");
        command = input.next();
        command = command.toUpperCase();
        twoQubitAddGateOneCommand(command);
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for adding first gate to the 2-qubit quantum circuit.
    private void twoQubitAddGateOneCommand(String command) {
        String[] allowedLetters = {"X", "Y", "Z", "S", "T", "I", "H", "C"};
        List<String> allowedLettersList = Arrays.asList(allowedLetters);
        if (command.equals("C")) {
            System.out.println("\n" + twoQubit.addGate("CN"));
        } else if (allowedLettersList.contains(command)) {
            twoQubitAddGateTwo(command);
        } else {
            System.out.println("\nInput is not valid!");
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input for adding second gate to 2-qubit quantum circuit
    private void twoQubitAddGateTwo(String gateone) {
        String command;
        System.out.println("\nChoose the gate to act on the second qubit.");
        oneQubitAddGateOptions();
        command = input.next();
        command = command.toUpperCase();
        twoQubitAddGateTwoCommand(gateone, command);
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for adding second gate to the 2-qubit quantum circuit.
    private void twoQubitAddGateTwoCommand(String gateone, String command) {
        String[] allowedLetters = {"X", "Y", "Z", "S", "T", "I", "H"};
        List<String> allowedLettersList = Arrays.asList(allowedLetters);
        if (allowedLettersList.contains(command)) {
            String combinedgate = gateone + command;
            System.out.println("\n" + twoQubit.addGate(combinedgate));
        } else {
            System.out.println("\nInput is not valid!");
        }
    }


}

