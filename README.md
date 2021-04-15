# 1 & 2 Qubit Quantum Circuit Simulation App

## Description
This app simulates the evolution of quantum bits through application of quantum gates, as well as through the process of measurement. 

## Brief Technical Overview
See *qc_overview.pdf* file.

## Even Briefer Demo Video
https://www.youtube.com/watch?v=DlA9VmnsXTQ

## How to use
1. Clone repository
2. Run /src/main/ui/Main.java
3. A quantum circuit (either with one or two qubits) can be initialized from the start menu. 
4. Quantum gates can be added and/or removed to the circuit, and can be applied to the qubit. As this is done, the quantum circuit, quantum state of the qubits, and measurement probabilities will be updated.
5. The qubit can be measured via the Measure the qubit(s) button.
6. The current quantum circuit and qubit state(s) can be saved to a local json file by typing in the desired filename into the "Save current qubit state as _.json" field and hitting enter. Saved quantum circuits can be loaded in by typing in the filename into the "Load qubit state from _.json" textbox and hitting enter.
