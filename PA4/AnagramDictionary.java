// Name:
// USC NetID:
// CS 455 PA4
// Spring 2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 A dictionary of all anagram sets.
 Note: the processing is case-sensitive; so if the dictionary has all lower
 case words, you will likely want any string you test to have all lower case
 letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   //the common of anagram is the unique string and the mult times of appearance
   //the map should store a string that shows the common chars and mults and all the anagrams of it
   //treemap to have order, which can be helpful to search
   private Map<String, ArrayList<String>> strWithAna = new TreeMap<>();



   /**
    Create an anagram dictionary from the list of words given in the file
    indicated by fileName.
    @param fileName  the name of the file to read from
    @throws FileNotFoundException  if the file is not found
    @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
         IllegalDictionaryException {

      File file = new File(fileName);  //read in the file


      try (Scanner in = new Scanner(file)) {
         while (in.hasNext()) {

            // Gets the word from file
            String word = in.next();
            //check the char and num of mult to compare
            String charNum = transfer(word);
            //compare whether it's a anagram of a word
            ArrayList<String> anaWord = strWithAna.get(charNum);

            if(anaWord==null){  //new char and mult
               anaWord=new ArrayList<String>();
               anaWord.add(word);
               strWithAna.put(charNum,anaWord);
            }
            else {  //already has one
               //check whether is a duplicate word
               for(int i = 0; i< anaWord.size(); i++){
                  if(word.equals(anaWord.get(i))){
                     throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: "+word);
                  }

               }
               //not a duplicate, then add it
               anaWord.add(word);
               strWithAna.put(charNum,anaWord);
            }

         }
      }


   }


   /**
    Get all anagrams of the given string. This method is case-sensitive.
    E.g. "CARE" and "race" would not be recognized as anagrams.
    @param s string to process
    @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      String target = transfer(string);
      return strWithAna.get(target); // DUMMY CODE TO GET IT TO COMPILE
   }

   public String transfer(String string) {
      // a2b1 and b1a2 are the same, so we need a treemap to have the sort
      TreeMap<Character, Integer> charNum = new TreeMap<Character, Integer>();
      //get the chars and mult
      for(int i = 0; i<string.length(); i++){
         char letter = string.charAt(i);
         Integer numTime = charNum.get(letter);
         if(numTime==null){
            charNum.put(letter,1);
         }
         else {
            charNum.put(letter,numTime+1);
         }
      }
      //make them a string
      StringBuilder target = new StringBuilder();
      for (Map.Entry<Character, Integer> cur : charNum.entrySet()) {
         target.append(cur.getKey());
         target.append(Integer.toString(cur.getValue()));
      }
      return target.toString();
   }


}
