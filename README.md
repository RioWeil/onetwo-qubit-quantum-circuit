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
See *qc_overview.pdf* file. Note that the file is currently a work in progress. 
In the meantime, chapters 1.1-2.2 of the qiskit textbook https://qiskit.org/textbook/ch-states/introduction.html
contain the relevant background information.

## User Stories
- As a user, I want to be able to fully manipulate a 1-qubit system by applying Pauli-XYZ, S, T, Identity, and Hadamard gates.
- As a user, I want to be able to fully manipulate a 2-qubit system by applying any combination of the above 7 gates,
  as well as a quantum CNOT gate. 
- As a user, I want to be able to add and/or remove multiple gates to a list of quantum gates that will operate on the system.
- As a user, I want to be able to view the current quantum state, see probability outcomes of measurement,
  and measure the state.