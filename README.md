# Project Proposal - 1 & 2 Qubit Quantum Circuit Simulation

## What will the application do?
- The application will simulate a one/two qubit system and its evolution using quantum gates and measurement.

## Who will use it?
- Physics/Mathematics/Computer Science students who want to learn about the basics of how quantum computing works.
- Educators, as a tool for teaching fundamental concepts of quantum mechanics and computing.

## Why is this project of interest?
- I am doing a degree in math and physics, and quantum mechanics is one of my favourite fields.
- I want to combine my interests in QM and computation, and get more people interested in physics.

## Brief Technical Overview
See *qc_overview.pdf* file.

## User Stories
- As a user, I want to be able to fully manipulate a 1-qubit system by applying Pauli-XYZ, S, T, Identity, and Hadamard gates.
- As a user, I want to be able to fully manipulate a 2-qubit system by applying any combination of the above 7 gates,
  as well as a quantum CNOT gate. 
- As a user, I want to be able to add and/or remove multiple gates to a list of quantum gates that will operate on the system.
- As a user, I want to be able to view the current quantum state, see probability outcomes of measurement,
  and measure the state.
- As a user, I want to be able to save the current qubit state(s) and list of quantum gates for the 1/2 qubit circuits.
- As a user, I want to be load in a qubit states and list of quantum gates from a file.

## Phase 4: Task 2
I chose to include a type hierarchy in my code.
- OneQubitQuantumCircuit and TwoQubitQuantumCircuit both extend QuantumCircuit, with different functionality in the two subclasses.
Other type hierachies in the project include:  
- FourByOneVector and TwoByOneVector extend Vector
- TwoQubit extends FourByOneVector, OneQubit extends TwoByOneVector
- FourByFourMatrix and TwoByTwoMatrix extend Matrix

## Phase 4: Task 3
Refactoring that I would do if I had more time to work on my design:
- Although it is no longer being used since the GUI was developed, it would be worth refactoring the Console ui to have a single QuantumCircuit field rather than two separate OneQubitQuantum Circuit and TwoQubitQuantumCircuit fields.
- I think I could abstract out TwoQubit and OneQubit into a single Qubits class. 
- Overall, for a larger scale implementation, it would be worthwhile to not create separate classes for quantum circuits with n numbers of qubits, as this leads to a lot of repeated code. Instead, it would be better to work with a list of qubits, and then have a quantum circuit that would work with a general number of qubits.
- The above refactoring would result in abstraction in many areas in the code; e.g. no more separate concrete classes for vectors/matrices, no separate classes for the GUI of each type, etc. 
  