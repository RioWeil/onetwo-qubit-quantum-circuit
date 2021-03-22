package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

// Represents a list of gates to be applied to the quantum state, that the user can add/remove gates from.
public class Gates {
    private LinkedList<String> gatelist;

    // EFFECTS: Creates a new empty list of gates.
    public Gates() {
        gatelist = new LinkedList<>();
    }

    // REQUIRES: gate is one of "X", "Y", "Z", "H", "T", "S", "I", or a two-character combination of those 7, or "CN".
    // MODIFIES: this
    // EFFECTS: Adds the provided string to the list of gates.
    public void addGate(String gate) {
        gatelist.addLast(gate);
    }

    // EFFECTS: Produces the first gate in the list.
    public String seeNextGate() {
        return gatelist.getFirst();
    }

    // REQUIRES: gatelist is non-empty.
    // MODIFIES: this
    // EFFECTS: Removes the first gate in the list
    public void removeNextGate() {
        gatelist.remove();
    }

    // REQUIRES: gatelist is non-empty.
    // MODIFIES: this
    // EFFECTS: Produces the first gate in the list, and removes it from the list.
    public String getNextGate() {
        return gatelist.remove();
    }

    // EFFECTS: Produces true if gatelist is empty.
    public boolean isEmpty() {
        return (gatelist.size() == 0);
    }

    // EFFECTS: Produces how many gates are currently in the list.
    public int length() {
        return gatelist.size();
    }

    // EFFECTS: Produces JSONArray containing all current gates and their positions in the list.
    public JSONArray gatesToJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json;
        for (int i = 0; i < length(); i++) {
            json = new JSONObject();
            json.put("index", i);
            json.put("gate", gatelist.get(i));
            jsonArray.put(json);
        }
        return jsonArray;
    }

    // EFFECTS: Returns complete list of gates
    public LinkedList<String> getGateList() {
        return gatelist;
    }
}
