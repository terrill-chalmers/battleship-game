package io.chalmers.domain;

import java.io.PrintStream;
import java.util.Scanner;

public class MyScanner {
    private final Scanner scanner;
    private final PrintStream out;

    public MyScanner() {
        scanner = new Scanner(System.in);
        this.out = System.out;
    }

    public String askForInput(String prompt) {
        out.println(prompt);
        return scanner.next();
    }
}
