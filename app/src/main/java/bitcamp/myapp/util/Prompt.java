package bitcamp.myapp.util;

import java.io.InputStream;
import java.util.*;

public class Prompt {

    private Scanner scanner;

    // default constructor 정의
    public Prompt() {
        this.scanner = new Scanner(System.in);
    }

    // 다른 입력 도구와 연결한다면
    public Prompt(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String inputString(String manual, Object... args) {
        System.out.printf(manual, args);
        return this.scanner.nextLine();
    }

    public int inputInt(String manual, Object... args) {
        return Integer.parseInt(this.inputString(manual, args));
    }

    public void close() {
        this.scanner.close();
    }

}