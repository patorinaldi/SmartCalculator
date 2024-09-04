package calculator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class Calculator {


    public void calculate(String[] tokens, VariableStore variableStore) {

        String postFix = infixToPostfix(tokens, variableStore);
        if (postFix == null){
            return;
        }
        String[] numbers = postFix.split(" ");
        Stack<String> numberStack = new Stack<>();

        for (String number : numbers){
            if (isNumeric(number) || variableStore.isValidIdentifier(number)){
                numberStack.push(number);
            }

            if (isOperator(number.charAt(0)) && number.length() == 1 ){
                String b = numberStack.pop();
                String a = numberStack.pop();
                BigInteger result = solve(new BigInteger(a), b,number.charAt(0), variableStore);
                numberStack.push(result.toString());
            }
        }

        System.out.println(new BigInteger(numberStack.pop()));

    }


    public BigInteger solve(BigInteger previous, String s, char symbol, VariableStore variableStore) {
        BigInteger number = variableStore.isValidIdentifier(s) ? new BigInteger(String.valueOf(variableStore.getVariable(s))) : new BigInteger(s);

        if (symbol == '+') {
            return previous.add(number);
        } else if (symbol == '-') {
            return previous.subtract(number);
        } else if (symbol == '*') {
            return previous.multiply(number);
        } else if (symbol == '/') {
            return previous.divide(number);
        }
        return BigInteger.ZERO;
    }

    public void handleAssignments(String[] tokens, VariableStore variableStore) {
        if (!variableStore.isValidIdentifier(tokens[0])) {
            System.out.println("Invalid identifier");
        } else if (isNumeric(tokens[2])) {
            variableStore.setVariable(tokens[0], new BigInteger(tokens[2]));
        } else if (variableStore.isValidIdentifier(tokens[2])) {
            BigInteger value = variableStore.getVariable(tokens[2]);
            if (value != null) {
                variableStore.setVariable(tokens[0], value);
            } else {
                System.out.println("Unknown variable");
            }
        } else if (tokens[2].equals("-")){
            variableStore.setVariable(tokens[0], new BigInteger(tokens[3]).negate());
        } else {
            System.out.println("Invalid assignment");
        }
    }

    public String[] curateInput(String input) {
        input = input.replaceAll(" ", "");
        input = input.replaceAll("\\+-|-\\+", "-");
        input = input.replaceAll("--", "+");
        input = input.replaceAll("\\+{2,}", "+");
        input = input.replaceAll("\\+-|-\\+", "-");
        input = input.replaceAll("[^a-zA-Z0-9+*/=()-]", "");
        return input.split("(?<=[-+*/=()])|(?=[-+*/=()])|(?<=\\d)(?=[(])|(?<=\\))(?=\\d)|(?<=\\d)(?=[+*/-])|(?<=\\))(?!\\s)|(?<=\\s)(?=\\()");
    }


    public boolean isValidExpression(String[] tokens) {

        if (isOperator(tokens[tokens.length - 1].charAt(0))) {
            return false;
        }

        if (tokens[0].equals("=")) {
            return false;
        }

        if (tokens.length > 4 && tokens[1].equals("=")) {
            return false;
        }

        String input = Arrays.toString(tokens).replaceAll(",","").replaceAll(" ","");

        if ((input.contains("(") && !input.contains(")")) || (!input.contains("(") && input.contains(")"))){
            return false;
        }

        return !input.matches(".*[*/]{2,}.*");

    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public boolean isNumeric(String s) {
        return s.matches("-?\\d+");
    }

    public boolean isPriority(String prior, String current) {
        if (prior.equals(current)) {
            return false;
        }

        if (prior.matches("[*/]") && current.matches("[-+]")) {
            return false;
        } else if (prior.matches("[-+]") && current.matches("[-+]")){
            return false;
        } else return !prior.matches("[*/]") || !current.matches("[*/]");

    }


    public String infixToPostfix(String[] tokens, VariableStore variableStore) {
        StringBuilder output = new StringBuilder();
        Stack<String> operatorStack = new Stack<>();
        for (String token : tokens) {
            if (isNumeric(token)) {
                output.append(token);
                output.append(" ");
            }

            if (variableStore.isValidIdentifier(token)) {
                BigInteger value = variableStore.getVariable(token);
                if (value != null) {
                    output.append(value);
                    output.append(" ");
                } else {
                    System.out.println("Unknown variable");
                    return null;
                }
            }

            if (isOperator(token.charAt(0))) {
                if (operatorStack.isEmpty() || isPriority(operatorStack.peek(), String.valueOf(token.charAt(0)))) {
                    operatorStack.push(token);
                } else {
                    while (!operatorStack.isEmpty()) {
                        if (operatorStack.peek().equals("(")) {
                            break;
                        }
                        output.append(operatorStack.pop());
                        output.append(" ");
                    }
                    operatorStack.push(token);
                }
            }

            if (token.charAt(0) == '(') {
                operatorStack.push(token);
            }

            if (token.charAt(0) == ')') {
                while (true) {
                    if (operatorStack.isEmpty()) {
                        break;
                    }
                    if (operatorStack.peek().equals("(")){
                        operatorStack.pop();
                        break;
                    }
                    output.append(operatorStack.pop());
                    output.append(" ");
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop());
            output.append(" ");
        }
        return output.toString();
    }
}