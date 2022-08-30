/*
Copyright (c) 2022, Vicky Ye
All rights reserved.

This source code is licensed under the BSD-style license found in the
LICENSE file in the root directory of this source tree. 
*/

import java.io.*;
import java.util.*;

public class countSongs {
    public static void main(String[] args) throws IOException {
        System.out.println("Thanks for using this Spotify data visualizer.");
        System.out.println("If you haven't already, check out the README file to understand how to use this.");
        System.out.println("-------------------");
        System.out.print("What is your username? ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.println(
                "Your data has been recognized");
        System.out.println("- Gathering Data... -\n");
        try { // delays lol fake
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Map<String, Integer> tally = new HashMap<String, Integer>();
        // read in data
        BufferedReader f = new BufferedReader(new FileReader("myData.txt"));
        // read until end of file
        String line;
        while ((line = f.readLine()) != null) {
            // parse each line
            if (line.trim().equals("{")) {
                f.readLine(); // skips the date i listened to the song
                f.readLine(); // skips artist bc im bad
                String songName = f.readLine();
                f.readLine(); // skips time played because i am mad

                songName = songName.substring(songName.indexOf(":") + 3, songName.length() - 2);

                if (!tally.containsKey(songName)) {
                    tally.put(songName, 1);
                } else {
                    tally.put(songName, tally.get(songName) + 1);
                }
            }
        }

        // close the reader
        f.close();

        // process the data - sort it in descnedin gorder
        LinkedHashMap<String, Integer> reversedOrder = new LinkedHashMap<>();
        tally.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reversedOrder.put(x.getKey(), x.getValue()));

        System.out.println("How many top tracks do you want to display? ");
        System.out.print("Enter a number: ");
        int N = sc.nextInt();
        sc.close();
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("Here are your top " + N + " tracks:");

        int count = 1;
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("forDisplay.txt")));
        out.println(username);
        out.println(N);
        for (Map.Entry<String, Integer> data : reversedOrder.entrySet()) {
            String key = data.getKey();
            Integer val = data.getValue();
            System.out.println("   " + count + ". " + key + " -------- " + val);
            out.println(key + ", " + val);
            if (N-- == 1) {
                break;
            }
            count++;
        }

        out.close();
        System.out.println("--------------------------------");
        System.out.println("thank you for using this i am already in front of your house");
    }
}
