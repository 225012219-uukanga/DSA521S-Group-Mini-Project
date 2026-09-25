// Task A3 — Postfix Expression Evaluation: Stack
// Custom array-based stack (no built-in java.util.Stack used). Independent
// exercise — not part of the service-centre menu.
public class PostfixEvaluator {

    private double[] items;
    private int top; // index of the top element; -1 means empty

    public PostfixEvaluator(int capacity) {
        items = new double[capacity];
        top = -1;
    }

    public void push(double value) {
        if (top == items.length - 1) {
            throw new RuntimeException("Stack overflow.");
        }
        items[++top] = value;
    }

    public double pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow — cannot pop from empty stack.");
        }
        return items[top--];
    }

    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack: [ ]");
            return;
        }
        StringBuilder sb = new StringBuilder("Stack (bottom -> top): [ ");
        for (int i = 0; i <= top; i++) {
            sb.append(items[i]).append(" ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    // Evaluate a postfix expression given as space-separated tokens, e.g. "5 3 + 2 *"
    public double evaluate(String expression) {
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (isOperator(token)) {
                double b = pop(); // second operand (popped first)
                double a = pop(); // first operand
                double result = applyOperator(a, b, token);
                push(result);
                System.out.println("Applied '" + token + "': " + a + " " + token + " " + b
                        + " = " + result);
            } else {
                double number = Double.parseDouble(token);
                push(number);
                System.out.println("Pushed number: " + number);
            }
            displayStack();
        }

        if (top != 0) {
            throw new RuntimeException("Malformed postfix expression.");
        }
        return peek();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("x") || token.equals("×") || token.equals("/") || token.equals("÷");
    }

    private double applyOperator(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": case "x": case "×": return a * b;
            case "/": case "÷":
                if (b == 0) throw new ArithmeticException("Division by zero.");
                return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    public static void main(String[] args) {
        PostfixEvaluator evaluator = new PostfixEvaluator(50);

        System.out.println("=== Evaluating postfix expression: 5 3 + 2 * ===");
        double result = evaluator.evaluate("5 3 + 2 *");
        System.out.println("\nFinal result: " + result);

        System.out.println("\n=== Evaluating postfix expression: 6 2 / 3 - ===");
        PostfixEvaluator evaluator2 = new PostfixEvaluator(50);
        double result2 = evaluator2.evaluate("6 2 / 3 -");
        System.out.println("\nFinal result: " + result2);
    }
}
