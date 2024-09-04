package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class VariableStore {

    private Map<String, BigInteger> variables = new HashMap<>();

    public void setVariable(String name, BigInteger value) {
        variables.put(name, value);
    }

    public BigInteger getVariable(String name) {
        return variables.getOrDefault(name, null);
    }

    public void printVariableValue(String name) {
        if (isValidIdentifier(name)){
            BigInteger value = getVariable(name);
            if (value != null) {
                System.out.println(value);
            } else {
                System.out.println("Unknown variable");
            }
        } else {
            System.out.println("Invalid identifier");
        }
    }

    public boolean isValidIdentifier(String s) {
        return s.matches("^[a-zA-Z]+$");
    }
}
