package ui;

import model.Complex;
import model.OneQubit;
import model.OneQubitQuantumCircuit;
import model.TwoQubitQuantumCircuit;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
        oneQubitControlPanelOptions();
        command = input.next();
        oneQubitControlPanelCommand(command);
    }

    // EFFECTS: Displays menu of possible actions on the one qubit quantum circuit.
    private void oneQubitControlPanelOptions() {
        System.out.println("\nSelect From:");
        System.out.println("\n0 - view current qubit state");
        System.out.println("\n1 - get current qubit measurement probabilities");
        System.out.println("\n2 - see first quantum gate in list");
        System.out.println("\n3 - remove first quantum gate in list");
        System.out.println("\n4 - add quantum gate to list");
        System.out.println("\n5 - apply first quantum gate in list to qubit");
        System.out.println("\n6 - apply all quantum gates in list to qubit");
        System.out.println("\n7 - measure qubit");
        System.out.println("\nq - Return to main menu; may have to press twice.");
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
        returnToMenuOrQuit(command);
    }

    // MODIFIES: this
    // EFFECTS: If "q", returns to main menu. Else, go back to the quantum circuit control panel.
    private void returnToMenuOrQuit(String command) {
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
        controlOneQubitQuantumCircuit();
    }




    private void startTwoQubitQuantumCircuit() {
        String command;
        initialQubitStateSelectionMenuTwoQubits();
        command = input.next();
        processQubitStateSelectionCommandTwoQubits(command);
    }

    private void initialQubitStateSelectionMenuTwoQubits() {
        System.out.println("\nSelect From:");
        System.out.println("\n0 -> Initial state of |00>");
        System.out.println("\n1 -> Initial state of |01>");
        System.out.println("\n2 -> Initial state of |10>");
        System.out.println("\n3 -> Initial state of |11>");
    }

    private void processQubitStateSelectionCommandTwoQubits(String command) {
        if (command.equals("0")) {
            System.out.println("This implementation not complete! Returning to Main menu.");
        } else if (command.equals("1")) {
            System.out.println("This implementation not complete! Returning to Main menu.");
        } else if (command.equals("2")) {
            System.out.println("This implementation not complete! Returning to Main menu.");
        } else if (command.equals("3")) {
            System.out.println("This implementation not complete! Returning to Main menu.");
        } else {
            System.out.println("Input is not valid! Returning to Main menu.");
        }
    }

}

