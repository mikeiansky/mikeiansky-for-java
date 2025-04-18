package io.github.mikeiansky.java.algorithm;

import java.io.IOException;
import java.util.Scanner;

public class StringBigDataPlusDemo {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        char[] wordCharArr = word.toCharArray();
        int minCharIndex = 0;
        char minChar = wordCharArr[0];
        for (int i = 1; i < wordCharArr.length; i++) {
            char wc = wordCharArr[i];
            if (wc < minChar) {
                minChar = wc;
                minCharIndex = i;
            }
        }
        // swap
        char head = wordCharArr[0];
        wordCharArr[0] = wordCharArr[minCharIndex];
        wordCharArr[minCharIndex] = head;
        System.out.println(new String(wordCharArr));

    }

}
