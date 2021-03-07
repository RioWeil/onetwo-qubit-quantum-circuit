package persistence;

import exceptions.WrongQubitNumberException;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads in a QuantumCircuit from JSON data stored in file.
// Citation: code obtained from JsonReader class in JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // Citation: Taken from JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Citation: Taken from JsonSerializationDemo
    // EFFECTS: reads OneQubitQuantumCircuit from file and returns it
    public OneQubitQuantumCircuit readOne() throws IOException, WrongQubitNumberException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        if (! (jsonObject.getInt("numQubits") == 1)) {
            throw new WrongQubitNumberException();
        }
        return parseOneQubitQuantumCircuit(jsonObject);
    }

    // Citation: Taken from JsonSerializationDemo
    // EFFECTS: reads TwoQubitQuantumCircuit from file and returns it
    public TwoQubitQuantumCircuit readTwo() throws IOException, WrongQubitNumberException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        if (! (jsonObject.getInt("numQubits") == 2)) {
            throw new WrongQubitNumberException();
        }
        return parseTwoQubitQuantumCircuit(jsonObject);
    }

    // Citation: Taken from JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // Citation: Modified from JsonSerializationDemo
    // EFFECTS: parses OneQubitQuantumCircuit from JSON Object and returns it
    private OneQubitQuantumCircuit parseOneQubitQuantumCircuit(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("amplitudes");
        Complex amp0 = getAmplitude(jsonArray, 0);
        Complex amp1 = getAmplitude(jsonArray, 1);
        OneQubitQuantumCircuit oqqc = new OneQubitQuantumCircuit(amp0, amp1);
        addGates(oqqc, jsonObject);
        return oqqc;
    }

    // Citation: Modified from JsonSerializationDemo
    // EFFECTS: parses TwoQubitQuantumCircuit from JSON Object and returns it
    private TwoQubitQuantumCircuit parseTwoQubitQuantumCircuit(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("amplitudes");
        Complex amp00 = getAmplitude(jsonArray, 0, 0);
        Complex amp01 = getAmplitude(jsonArray, 0, 1);
        Complex amp10 = getAmplitude(jsonArray, 1, 0);
        Complex amp11 = getAmplitude(jsonArray, 1, 1);
        TwoQubitQuantumCircuit tqqc = new TwoQubitQuantumCircuit(amp00, amp01, amp10, amp11);
        addGates(tqqc, jsonObject);
        return tqqc;
    }

    // REQUIRES: basisVector is 0 or 1
    // EFFECTS: parses complex amplitude of eigenstates for OneQubit from JSON object
    private Complex getAmplitude(JSONArray jsonArray, int basisVector) {
        double re = 0;
        double im = 0;
        for (Object json: jsonArray) {
            JSONObject nextAmp = (JSONObject) json;
            if (nextAmp.getInt("basisVector") == basisVector) {
                re = nextAmp.getDouble("re");
                im = nextAmp.getDouble("im");
            }
        }
        return new Complex(re, im);
    }

    // REQUIRES: basisVector1, basisVector2 are 0 or 1
    // EFFECTS: parses complex amplitude of eigenstates for TwoQubits from JSON object
    private Complex getAmplitude(JSONArray jsonArray, int basisVector1, int basisVector2) {
        boolean check1;
        boolean check2;
        double re = 0;
        double im = 0;
        for (Object json: jsonArray) {
            JSONObject nextAmp = (JSONObject) json;
            check1 = nextAmp.getInt("basisVector1") == basisVector1;
            check2 = nextAmp.getInt("basisVector2") == basisVector2;
            if (check1 && check2) {
                re = nextAmp.getDouble("re");
                im = nextAmp.getDouble("im");
            }
        }
        return new Complex(re, im);
    }


    // Citation: Modified from JsonSerializationDemo
    // MODIFIES: qc
    // EFFECTS: parses gates from JSON object and adds it to the QuantumCircuit
    private void addGates(QuantumCircuit qc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gates");
        for (Object json: jsonArray) {
            JSONObject nextGate = (JSONObject) json;
            addGate(qc, nextGate);
        }
    }

    // Citation: Modified from JsonSerializationDemo
    // MODIFIES: qc
    // EFFECTS: parses gate from JSON object and adds it to the QuantumCircuit
    private void addGate(QuantumCircuit qc, JSONObject jsonObject) {
        String gate = jsonObject.getString("gate");
        qc.addGate(gate);
    }


}
