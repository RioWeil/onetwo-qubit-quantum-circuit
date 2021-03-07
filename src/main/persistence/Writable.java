package persistence;

import org.json.JSONObject;

// Represents a general writer for JSON files.
// Citation: code obtained from Writable class in JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // Citation: Taken from JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
