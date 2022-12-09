package ReadWrite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static final String HEADER = "Word,Count\n";

    public static void main(String[] args) {

        //javac -sourcepath src -d classes src/readwrite/*.java
        //java -cp classes ReadWrite.Main paradise_lost.txt mainOutput.csv
        String rFile = args[0];
        System.out.printf("Processing %s.\n", rFile);
        
        HashMap <String, Integer> wordsMap = new HashMap<String, Integer>();

        try {
            //rFile is "src/milton/paradise_lost.txt" if file is in src/milton instead of day05
            //rFile = ("src/milton/"+fName);
            FileReader fr = new FileReader(rFile);
            BufferedReader br = new BufferedReader(fr);

            //1. Print 100 lines + count the number of words in the 100 lines
            //2. Count the number of times each word appears in the 100 lines
            //3. List all uniques words and count the number of times they appear in the 100 lines

            Integer numWords = 0;

            for(int i = 1; i <= 100 ; i++) {
                String line = br.readLine();
                if(line == null) {
                    break;
                }
                else {
                    System.out.println(line); //print each line
                    line = line.replaceAll("","");
                    String[] words = line.trim().split(" "); //split the words in each line
                    numWords += words.length; //add number of words per line to total count

                    for (String w : words) {
                        if(wordsMap.containsKey(w)) {
                            Integer count = wordsMap.get(w) + 1;
                            wordsMap.put(w,count);
                        }
                        else {
                            wordsMap.put(w,1);
                        }
                    }
                }
            }
            
            System.out.printf("There are %d words in the first 100 lines.\n", numWords);

            /* If printing on terminal

            for (String i : wordsMap.keySet()) {
            System.out.println(i.toLowerCase() + " appears " + wordsMap.get(i) + " times.");
            }
            */

            br.close();
            fr.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        catch (IOException e) {
            System.out.println("Unable to read file.");
        }

        //Create CSV file
        try {
            String wFile = args[1];
            FileOutputStream fos = new FileOutputStream(wFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            osw.write(HEADER);
            
            for (String i: wordsMap.keySet()) {
                String line = String.format("%s,%d\n", i, wordsMap.get(i));
                osw.write(line);
            }

            osw.flush();
            osw.close();
            fos.close();

        }

        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        catch (IOException e) {
            System.out.println("Unable to read file.");
        }

    }

}