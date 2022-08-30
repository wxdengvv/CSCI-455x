// Name:
// USC NetID:
// CS 455 PA4
// Spring 2022

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 A Rack of Scrabble tiles
 */

public class Rack {


   public Rack(String inputWord) {

      }

      //get the allSubsets method a better and integrate interface to call
   public static ArrayList<String> allSubsets(String target) {
      //use hashmap for no need of order
      Map<Character,Integer> stringNum = new HashMap<Character,Integer>();
      //get the string and mult for the helper method
      for(int i =0;i<target.length();i++){
         char letter = target.charAt(i);
         Integer numTime = stringNum.get(letter);
         if(numTime==null){
            stringNum.put(letter,1);
         }
         else {
            stringNum.put(letter,numTime+1);
         }
      }

      StringBuilder unique = new StringBuilder();  //need stringbuilder to add up the string
      int mult[] = new int[stringNum.size()];
      int i=0;  //the index of mult

      //add up all the chars and get the value of appearance time
      for (Map.Entry<Character, Integer> cur : stringNum.entrySet()) {
         unique.append(cur.getKey());
         mult[i++] = cur.getValue();
      }

      return allSubsets(String.valueOf(unique),mult,0);

   }


   /**
    Finds all subsets of the multiset starting at position k in unique and mult.
    unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    unique.charAt(i).
    PRE: mult.length must be at least as big as unique.length()
    0 <= k <= unique.length()
    @param unique a string of unique letters
    @param mult the multiplicity of each letter from unique.
    @param k the smallest index of unique and mult to consider.
    @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
    each subset is represented as a String that can have repeated characters in it.
    @author Claire Bono
    */
   static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos.  Suppose that char is 'a'...

      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
            // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }

      return allCombos;
   }




}
