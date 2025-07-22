package BaiThucHanhSo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bai5 extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput = new StringBuilder();

    public Bai5() {
        setTitle("Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "C", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        JPanel equalPanel = new JPanel(new BorderLayout());
        JButton equalBtn = new JButton("=");
        equalBtn.addActionListener(this);
        equalPanel.add(equalBtn, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.CENTER);
        add(equalPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();

        switch (cmd) {
            case "=":
                try {
                    double result = evaluate(currentInput.toString());
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                } catch (Exception ex) {
                    display.setText("Lỗi");
                    currentInput.setLength(0);
                }
                break;
            case "C":
                currentInput.setLength(0);
                display.setText("");
                break;
            default:
                currentInput.append(cmd);
                display.setText(currentInput.toString());
        }
    }

    private double evaluate(String expr) {
        java.util.List<Double> numbers = new java.util.ArrayList<>();
        java.util.List<Character> operators = new java.util.ArrayList<>();
        expr = expr.replaceAll(" ", "");

        int i = 0;
        while (i < expr.length()) {
            StringBuilder num = new StringBuilder();

            if ((i == 0 || "+-*/".indexOf(expr.charAt(i - 1)) != -1) && expr.charAt(i) == '-') {
                num.append('-');
                i++;
            }

            while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                num.append(expr.charAt(i));
                i++;
            }

            numbers.add(Double.parseDouble(num.toString()));

            if (i < expr.length()) {
                char op = expr.charAt(i);
                if ("+-*/".indexOf(op) != -1) {
                    operators.add(op);
                    i++;
                } else {
                    throw new RuntimeException("Toán tử không hợp lệ: " + op);
                }
            }
        }

        for (int j = 0; j < operators.size(); ) {
            char op = operators.get(j);
            if (op == '*' || op == '/') {
                double a = numbers.get(j);
                double b = numbers.get(j + 1);
                double res = (op == '*') ? a * b : a / b;
                numbers.set(j, res);
                numbers.remove(j + 1);
                operators.remove(j);
            } else {
                j++;
            }
        }

        double result = numbers.get(0);
        for (int j = 0; j < operators.size(); j++) {
            double b = numbers.get(j + 1);
            result = (operators.get(j) == '+') ? result + b : result - b;
        }

        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai5().setVisible(true));
    }
}
