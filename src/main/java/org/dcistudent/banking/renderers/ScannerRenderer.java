package org.dcistudent.banking.renderers;

public final class ScannerRenderer {
    private static final String SEPARATOR =
            "================================================================================"
    ;

    public static void renderSeparated(String line) {
        System.out.printf("%n%s%n%s%n", line, SEPARATOR);
    }

    public static void renderSeparater() {
        System.out.printf("%s%n", SEPARATOR);
    }

    public static void renderInput(String message) {
        System.out.printf("%s: ", message);
    }

    public static void renderInputChoice() {
        System.out.print("Choice: ");
    }
}
