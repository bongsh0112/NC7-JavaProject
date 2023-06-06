package bitcamp.myapp.util;

import java.util.*;

public class Prompt {

    static Scanner scanner = new Scanner(System.in);

    public static String inputString(String manual) {

        System.out.print(manual);
        return scanner.nextLine();

    }

    public static void scannerCloser() {
        scanner.close();
    }

}