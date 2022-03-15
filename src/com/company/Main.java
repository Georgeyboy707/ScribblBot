package com.company;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

public class Main {
    public static void main(String[] args) throws AWTException {

        //todo: Words
        String rawList = "";

        String[] list = rawList.split(",");    //separates into individual words from rawList to list[]

        //todo INPUT   ==========================
        int reqLen = 10;    //total number of characters, inc spaces
        int spaceReq = 1;   //0 = no space, 1 = space
        int posSpace = 5;   //how many letters in the first word
        //todo INPUT   ==========================

        //removes whitespace either side of the words
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i].trim();
        }

        //Bubble sort length of words
        boolean swapped = true;
        String tempVar = "-1";
        while (swapped) {
            swapped = false;
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i].length() > list[i + 1].length()) {

                    tempVar = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = tempVar;

                    swapped = true;
                }
            }
        }

        //Keep words that are the required length, puts them into finalList
        String[] finalList = new String[list.length];
        int count = 0;
        int nullCount = 0;

        for (String s : list) {
            if (s.length() == reqLen) {
                finalList[count] = s;
                count++;
            } else {
                finalList[count + nullCount] = "-1";
                nullCount++;
            }
        }

        //Keep words that have a space in them in the correct place or those that don't according to spaceReq
        //makes sure there is no null because it doesn't like that
        String[] ultraFinalList = new String[list.length];
        for (int i = 0; i < finalList.length; i++) {
            ultraFinalList[i] = "-1";
        }
        //if space is required, then check if it is in the right place (posSpace)
        //else, only keep words with no spaces at all
        int x = 0;
        if (spaceReq == 1) {
            while (!finalList[x].equals("-1")) {
                if (finalList[x].charAt(posSpace) == ' ') {
                    ultraFinalList[count] = finalList[x];
                    count++;
                }
                x++;
            }
        } else {
            while (!finalList[x].equals("-1")) {
                if (!finalList[x].contains(" ")) {
                    ultraFinalList[count] = finalList[x];
                    count++;
                }
                x++;
            }
        }

        //prints out the words from ultraFinalList
        System.out.println("");
        System.out.println("");
        System.out.println("==== Words that are " + reqLen + " characters long and have " + spaceReq + " spaces ====");
        System.out.println("");

        for (String s : ultraFinalList) {
            if (!s.contains("-1")) {
                System.out.println(s);
                output(s);  //calls output to type in words to the text box of scribbl
            }
        }
    }

    //this outputs into a text box (thx max)
    public static void output(String word) throws AWTException {
        Robot robot = new Robot();

        int letterCode = 0;

        for (int i = 0; i < word.length(); i++) {
            letterCode = word.charAt(i);
            //prints the unicode character specified by letterCode
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(letterCode));
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}