# Calculator Program

This project is a simple console-based calculator implemented in Java. It supports basic arithmetic operations such as addition, subtraction, multiplication, and division. Additionally, it handles variables, allowing you to assign values to identifiers and use them in calculations. The program also includes command handling for basic functionality like help and exit.

## Features

- **Arithmetic Operations**: Supports addition (`+`), subtraction (`-`), multiplication (`*`), and division (`/`).
- **Variable Assignment**: You can assign values to variables and use them in expressions.
- **Postfix Expression Parsing**: Converts infix expressions to postfix for easier evaluation.
- **Command Handling**: Handles commands like `/exit` and `/help`.
- **Supports Negative Numbers**: Handles negative numbers and operations involving them.
- **Parentheses Support**: Handles parentheses for complex expressions.

## Project Structure

The project contains the following Java classes:

### 1. **`Calculator`**
   This class is responsible for:
   - Converting infix expressions to postfix notation.
   - Evaluating postfix expressions.
   - Handling assignments of values to variables.

### 2. **`VariableStore`**
   This class stores and manages variable values. It provides methods to:
   - Set and get variable values.
   - Validate if a string is a valid variable identifier.
   - Print the value of a variable.

### 3. **`CommandProcessor`**
   This class is responsible for processing user commands. It recognizes:
   - `/exit`: Terminates the program.
   - `/help`: Prints a simple help message explaining the program's functionality.
   - Other unknown commands will print "Unknown command."

### 4. **`Main`**
   This is the entry point of the program. It initializes the `Calculator`, `VariableStore`, and `CommandProcessor` classes and handles the user input loop.

## Usage

### Basic Arithmetic Operations
You can input arithmetic expressions directly. The supported operations are:

- `+` for addition
- `-` for subtraction
- `*` for multiplication
- `/` for division


### Parentheses Support
You can use parentheses to prioritize operations in expressions.

**Example:**
Input: (2 + 3) * 4 Output: 20

### Variable Assignment
You can assign values to variables and use them in subsequent calculations.

**Example:**
Input: x = 10 Input: y = 5 Input: x + y Output: 15

### Commands
- **`/exit`**: Exits the program.
- **`/help`**: Displays a help message describing the available functionality.

**Example:**
Input: /help Output: The program calculates the sum or subtraction of numbers.

## Input Rules

- Valid identifiers for variables consist of alphabetic characters (e.g., `x`, `abc`).
- Arithmetic expressions should be properly formatted. For example, multiple consecutive operators like `++` or `//` are not allowed.
- Expressions with unmatched parentheses will be marked as invalid.
- Valid assignments follow the format `variable = value`.

## Example Usage
Input: a = 5 Input: b = 7 Input: c = a + b Output: 12

Input: /help Output: The program calculates the sum or subtraction of numbers.

Input: /exit Output: Bye!

