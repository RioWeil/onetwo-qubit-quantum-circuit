package ui.gui;

import model.OneQubitQuantumCircuit;
import model.QuantumCircuit;

import java.awt.*;
import java.util.LinkedList;

// Represents a drawer/drawing for the quantum gates in the circuit.
// Idea from https://www.codespeedy.com/draw-shapes-in-java-swing/
public class GateDrawer extends Canvas {
    final int centerY = 30;
    final int spacing = 10;
    final int sideLength = 20;
    final int xcorrection = sideLength / 3;
    final int ycorrection = sideLength * 3 / 4;
    LinkedList<String> gatelist;
    int numQubits;
    int width;


    // EFFECTS: Creates a new QuantumCircuitGateDrawer
    public GateDrawer(QuantumCircuit qubits, int width) {
        super();
        gatelist = qubits.getGateList();
        if (qubits.getClass() == OneQubitQuantumCircuit.class) {
            numQubits = 1;
        } else {
            numQubits = 2;
        }
        this.width = width;
    }

    // MODIFIES: g
    // EFFECTS: Paints the canvas with the quantum gates.
    @Override
    public void paint(Graphics g) {
        if (numQubits == 1) {
            drawOneQubitGates(g);
        } else {
            drawTwoQubitGates(g);
        }
    }

    // MODIFIES: g
    // EFFECTS: Paints the canvas with 1-qubit quantum gates
    private void drawOneQubitGates(Graphics g) {
        int x = spacing;
        int y = sideLength;
        g.drawLine(0, centerY, width * 3, centerY);
        for (String gate: gatelist)  {
            drawSingleGate(g, gate, x + xcorrection, y + ycorrection);
            x += spacing + sideLength;
        }
    }

    // MODIFIES: g
    // EFFECTS: Draws a single square with gate inside of it at the specified coordinate. I "gate" is not drawn.
    private void drawSingleGate(Graphics g, String gate, int x, int y) {
        if (!gate.equals("I")) {
            g.setColor(Color.white);
            g.fillRect(x - xcorrection, y - ycorrection, sideLength, sideLength);
            g.setColor(Color.black);
            g.drawRect(x - xcorrection, y - ycorrection, sideLength, sideLength);
            g.drawString(gate, x, y);
        }
    }

    // MODIFIES: g
    // EFFECTS: Paints the canvas with 2-qubit quantum gates
    private void drawTwoQubitGates(Graphics g) {
        int x = spacing;
        int y1 = spacing / 2;
        int y2 = centerY + spacing / 2;
        setBackground(Color.white);
        g.drawLine(0, centerY / 2, width * 3, centerY / 2);
        g.drawLine(0, centerY * 3 / 2, width * 3, centerY * 3 / 2);
        for (String gate: gatelist) {
            if (gate.equals("CN")) {
                drawCN(g, x, y2, y1);
            } else {
                drawSingleGate(g, gate.substring(0, 1), x + xcorrection, y1 + ycorrection);
                drawSingleGate(g, gate.substring(1, 2), x + xcorrection, y2 + ycorrection);
            }
            x += spacing + sideLength;
        }
    }

    // MODIFIES: g
    // EFFECTS: Draws CN gate at specified x coordinate and y2 coordinate
    private void drawCN(Graphics g, int x, int y2, int y1) {
        g.drawOval(x, y2, sideLength, sideLength);
        g.drawLine(x + sideLength / 2, y2 + sideLength, x + sideLength / 2, y1 + sideLength / 2);
        g.fillOval(x + sideLength / 4, y1 + sideLength / 4, sideLength / 2, sideLength / 2);
    }

}
