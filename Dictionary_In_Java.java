/****************************************************************
 *
 * File: Dictionary.java
 *
 * Description: This program returns definitions and outputs them
 * in a way specified by the user based on what they type in search
 *
 ****************************************************************/

import java.util.*;

//Setting all the enum values
public enum Dictionary {

    ArrowN1("Arrow", "noun", "Here is one arrow: <IMG> -=>> </IMG>"),

    BookN1("Book", "noun", "A set of pages."),
    BookN2("Book", "noun", "A written work published in printed or electronic form."),
    BookV1("Book", "verb", "To arrange for someone to have a seat on a plane."),
    BookV2("Book", "verb", "To arrange for something on a particular date."),

    DistinctAdj1("Distinct","adjective","Familiar. Worked in Java."),
    DistinctAdj2("Distinct","adjective","Unique. No duplicates. Clearly different or of a different kind."),
    DistinctAdv1("Distinct","adverb","Uniquely. Written \"distinctly.\""),
    DistinctN1("Distinct","noun","A keyword in this assignment."),
    DistinctN2("Distinct","noun","A keyword in this assignment."),
    DistinctN3("Distinct","noun","A keyword in this assignment."),
    DistinctN4("Distinct","noun","An advanced search option."),
    DistinctN5("Distinct","noun","Distinct is a parameter in this assignment."),

    PlaceholderAdj1("Placeholder", "adjective", "To be updated..."),
    PlaceholderAdj2("Placeholder", "adjective", "To be updated..."),
    PlaceholderAdj3("Placeholder", "adverb", "To be updated..."),
    PlaceholderCon1("Placeholder", "conjunction", "To be updated..."),
    PlaceholderInter1("Placeholder", "interjection", "To be updated..."),
    PlaceholderN1("Placeholder", "noun", "To be updated..."),
    PlaceholderN2("Placeholder", "noun", "To be updated..."),
    PlaceholderN3("Placeholder", "noun", "To be updated..."),
    PlaceholderPrep1("Placeholder", "preposition", "To be updated..."),
    PlaceholderPron1("Placeholder", "pronoun", "To be updated..."),
    PlaceholderV1("Placeholder", "verb", "To be updated..."),

    ReverseAdj1("Reverse", "adjective", "On back side."),
    ReverseAdj2("Reverse", "adjective", "Opposite to usual or previous arrangement."),
    ReverseN1("Reverse", "noun", "A dictionary program's parameter."),
    ReverseN2("Reverse", "noun", "Change to opposite direction."),
    ReverseN3("Reverse", "noun", "The opposite."),
    ReverseN4("Reverse", "noun", "To be updated..."),
    ReverseN5("Reverse", "noun", "To be updated..."),
    ReverseN6("Reverse", "noun", "To be updated..."),
    ReverseN7("Reverse", "noun", "To be updated..."),
    ReverseV1("Reverse", "verb", "Change something to opposite."),
    ReverseV2("Reverse", "verb", "Go back"),
    ReverseV3("Reverse", "verb", "Revoke ruling."),
    ReverseV4("Reverse", "verb", "To be updated..."),
    ReverseV5("Reverse", "verb", "To be updated..."),
    ReverseV6("Reverse", "verb", "Turn something inside out.");

    //Set the variables corresponding to each enum, along with setter and getters
    private String word;
    private String speechPart;
    private String definition;

    Dictionary(String word, String speechPart, String definition) {
        this.word = word;
        this.speechPart = speechPart;
        this.definition = definition;
    }

    public String getWord() {
        return this.word.toUpperCase();
    }

    public String speechPart() {
        return this.speechPart;
    }

    public String definition(){
        return this.definition;
    }

    @Override
    public String toString() {
        return this.word + " [" + this.speechPart + "] : " + this.definition;
    }

    //Booleans used to help determine what the user enters and how the program would run
        public static Boolean setDistinct = false;
        public static Boolean setReverse = false;
        public static Boolean containsSpeech = false;
        public static String[] speechParts = {"noun", "adjective", "verb",  "adverb", "conjunction", "preposition","interjection", "pronoun", };

    //Function where most of the work happens
    public static void runDictionary(){

        //Makes new empty hashmap
        HashMap<String, ArrayList<Dictionary>> dictMap = new HashMap<>();

        //Loads in hashmap into data structure using a for Loop
        for (Dictionary LoadDict : Dictionary.values()) {

            String word = LoadDict.getWord();
            ArrayList<Dictionary> list = (dictMap.containsKey(word)) ? dictMap.get(word) : new ArrayList<>();
            list.add(LoadDict);
            dictMap.put(word, list);
        }

        //Setting up variables for scanner, counter, and when to quit
        Scanner input = new Scanner(System.in);
        Boolean checkForQ = true;
        int counter = 0;

        //Do While Loop for looping program
        do {
            //initializing more variables
            String word = null;
            String speechPart = null;
            int amountOfWords = 0;

            counter++;

            //User input
            System.out.print("Search [" + counter + "]: ");
            String inputByUser = input.nextLine();
            //Space splits up words
            String[] wordCount = inputByUser.split(" ");
            amountOfWords = wordCount.length;

            //Determines if error message is needed immediately
            if (wordCount.length > 4 || inputByUser.equalsIgnoreCase("")||(inputByUser.equalsIgnoreCase("!help"))) {
                System.out.println("\t|");
                howToMessage();
                System.out.println("\t|");
                continue;
            }
            if (wordCount.length > 0) {
                word = wordCount[0];
            }

            //Checks for speech flag and sets it
            if (wordCount.length > 1 && wordCount[1]!= null) {
                if (Arrays.asList(speechParts).contains(wordCount[1].toLowerCase())){
                // System.out.println("Speech Contained");
                    containsSpeech = true;
                    speechPart = wordCount[1];}
            }


            //Switch Expression with For Loop to determine if one entry or multiple
            for (int w = 0; w < amountOfWords; w++) {
//                System.out.println("w: " + w);
//                System.out.println("amountOfWords: " + amountOfWords);
            String inputCount = wordCount[w];
            String r = switch(amountOfWords) {

                case 1 -> {
//                    System.out.println("CASE 1");
                    yield "Nothing";
                }
                case 2,3,4-> {
//                    System.out.println("CASE 2,3,4");
                    choice1(inputCount, w);
                    yield ""; }
                default -> {
                    System.out.println("\t|");
                    howToMessage();
                    System.out.println("\t|");
                    yield "";
                }
            }; }

            //Next conditional uses boolean flags to determine what will happen to the list
            //Also checks if !q was entered
            if(!inputByUser.equalsIgnoreCase("!q")) {

                System.out.println("    |");
                ArrayList<Dictionary> List1 = dictMap.get(word.toUpperCase());
                ArrayList<Dictionary> List2 = (setDistinct) ? makeDistinctDefin(List1) : List1;
                ArrayList<Dictionary> List3 = (speechPart != null) ? makeSameSpeech(List2, speechPart) : List2;
                ArrayList<Dictionary> List4 = (setReverse) ? reverseDict(List3) :List3;

                //Prints out dictionary entries
                if (List4 != null && List4.size()>0) {
                    for(Dictionary printOut : List4) {
                        System.out.print("\t " + printOut + "\n");
                    }
                }
                else {
                    //In case an unrecognizable word is passed through
                    System.out.println("\t <NOT FOUND> To be considered for the next release. Thank you");
                    System.out.println("\t|\n\t|");
                    howToMessage();
                }
                System.out.println("\t|");
            } else {
                //Ends program if Q is entered
                checkForQ = false;
            }
            //Resets Booleans back to false
            setDistinct = false;
            setReverse = false;
            containsSpeech = false;

        } while(checkForQ);
        System.out.println("\n----THANK YOU----");

    }

    //Function where Boolean flags are set
    public static void choice1(String inputByUser, int wordAmount) {
        int countTest = 0;
                countTest++;
//        System.out.println("begin choice method");
        Boolean printErrorMessage = false; //Sets to false since error may not apply to each word
        if (wordAmount == 0 ) {
//            System.out.println("Skip this for first word.");
        }
        else if (inputByUser.equalsIgnoreCase("distinct") && wordAmount != 3) {
            setDistinct = true;
//            System.out.println("DISTINCT TRUE");
        } else if (inputByUser.equalsIgnoreCase("reverse")) {
            setReverse = true;
//            System.out.println("REVERSE TRUE");
        } else if (containsSpeech) {
//           System.out.println("SPEECH TRUE");
            containsSpeech = false;
        } else {
//            System.out.println("ERROR REACHED");
            printErrorMessage = true;
        }
//        System.out.println(countTest);
//        System.out.println("word: " + inputByUser);
//        System.out.println("Number: " + wordAmount);
//        System.out.println("end choice method");

        //This if Statement runs only if the ErrorMessage Flag is set to true
        if (printErrorMessage) {
//            System.out.println("entering error if statement");
            String ErrorMsg = switch (wordAmount) {
                case 0-> "0";
                case 1 -> {
                    System.out.println("\t|");
                    System.out.println("\t <The entered 2nd parameter " + inputByUser + " is NOT a part of speech.>\n" +
                            "\t <The entered 2nd parameter " + inputByUser + " is NOT 'distinct'.>\n" +
                            "\t <The entered 2nd parameter " + inputByUser + " is NOT 'reverse'.>\n" +
                            "\t <The entered 2nd parameter " + inputByUser + " was disregarded.>\n" +
                            "\t <The 2nd parameter should be a part of speech or 'distinct' or 'reverse'.>");
                    System.out.println("    |");
                    yield "1";
                }
                case 2 -> {
                    System.out.println("\t|");
                    System.out.println("\t <The entered 3rd parameter " + inputByUser + " is NOT 'distinct'.>\n" +
                            "\t <The entered 3rd parameter " + inputByUser + " is NOT 'reverse'.>\n" +
                            "\t <The entered 3rd parameter " + inputByUser + " was disregarded.>\n" +
                            "\t <The 3rd parameter should be 'distinct' or 'reverse'.>");
                    yield "2";
                }
                case 3 -> {
                    System.out.println("\t|");
                    System.out.println("\t <The entered 4th parameter " + inputByUser + " is NOT 'reverse'.>\n" +
                            "\t <The entered 4th parameter " + inputByUser + " was disregarded.>\n" +
                            "\t <The 4th parameter should be 'reverse'.>");
                    System.out.println("\t|");
                    yield "3";
                }

                default -> throw new IllegalStateException("Unexpected value: " + wordAmount);
            };
        }
    }

    //How to message in its own function since it repeats in different situations
    public static void howToMessage(){
        System.out.println("\t PARAMETER HOW-TO, please enter:\n" +
                "\t 1. A search key -then 2. An optional part of speech -then\n" +
                "\t 3. An optional 'distinct' -then 4. An optional 'reverse");
    }

    //Makes it so only distinct definitions are passed into a new ArrayList and return
    public static ArrayList<Dictionary> makeDistinctDefin(ArrayList<Dictionary> dList) {
        LinkedHashMap<String, Dictionary> clone = new LinkedHashMap<>();
        if (dList != null) {
            for (Dictionary d : dList) {
                String definition = d.definition();
                if (!clone.containsKey(definition)) {
                    clone.put(definition, d);
                }
            }
        }
        return new ArrayList<>(clone.values());
    }

    //Makes sure only the same speech chosen is displayed as it's passed into a new ArrayList and returned
    public static ArrayList<Dictionary> makeSameSpeech(ArrayList<Dictionary> dList, String speechPart) {
        ArrayList<Dictionary> clone = new ArrayList<>();
        if (dList != null) {
            for (Dictionary d : dList) {
                if (d.speechPart().equalsIgnoreCase(speechPart)) {
                    clone.add(d);
                }
            }
        }
        return clone;
    }

    //Uses a Collections function to reverse the entries of a newly cloned list
    public static ArrayList<Dictionary> reverseDict(ArrayList<Dictionary> dList)
    {
        ArrayList<Dictionary> clone = new ArrayList<>();
        if (dList != null) {
            clone.addAll(dList);
            Collections.reverse(clone);
        }
        return clone;
    }

    //Main method with welcome message and the runDictionary() method.
    public static void main(String[] args) {
        System.out.println("\n! Loading data...\n" +
                "! Loading completed...\n\n===== DICTIONARY 340 JAVA =====\n" +
                "----- Keywords: 19\n----- Definitions: 61\n");
        runDictionary();
    }

}
