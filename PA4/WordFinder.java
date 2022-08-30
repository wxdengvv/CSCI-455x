import java.io.FileNotFoundException;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

public class WordFinder {
   public static void main(String[] args) {

      String inFile;  //file name
      Scanner in = new Scanner(System.in);
      if(args.length!=0){
         inFile = args[0];
      }
      else{  //use default dict
         inFile = "src\\sowpods.txt";
      }


      try
      {
         AnagramDictionary dict = new AnagramDictionary(inFile);  //crate the dict with anagram map
         System.out.println("Type . to quit.");

         while(true){
            System.out.print("Rack? ");
            String input = in.next();
            if(input.equals(".")){
               in.close();
               break;
            }
            else{
               String inputWord = wordFilter(input);
               getAllWord(inputWord, dict);  //pass in the word and dictionary

            }

         }


      }
      catch (FileNotFoundException e) {
         System.out.println("ERROR: The dictionary " + inFile + " does not exist!");
         // Closes the Scanner
         in.close();
      }
      catch (IllegalDictionaryException e) {
         System.out.println(e.getMessage());
         // Closes the Scanner
         in.close();
      }


   }


   public static void getAllWord(String target, AnagramDictionary dict){
      //1st: find all subsets
      ArrayList<String> targSub = new ArrayList<>();
      Rack word = new Rack(target);
      ScoreTable scoreTable = new ScoreTable();
      targSub = word.allSubsets(target);

      //2nd: find anagram for every subset
      TreeMap<String,ArrayList<String>> allWords = new TreeMap<>();
      ArrayList<String> allAna = new ArrayList<>();
      for(int i = 0;i<targSub.size(); i++){
         allAna = dict.getAnagramsOf(targSub.get(i));
         if(allAna!=null){  //has anagram
         allWords.put(targSub.get(i),allAna);
         }
      }

      //3rd: get scores and sort
      Map<String,Integer> wordScore = new HashMap<>();
      for(Map.Entry<String, ArrayList<String>> cur : allWords.entrySet()){
         int score = scoreTable.getScore(cur.getKey());
         for(int i = 0; i<cur.getValue().size(); i++){  //put every anagram word and score in
            wordScore.put(cur.getValue().get(i), score);
         }

      }

      ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(wordScore.entrySet());
      Collections.sort(list, new ScoreComparator());

      //4th: print
      System.out.println("We can make "+wordScore.size()+" words from \"" + target + "\"");
      if(wordScore.size()!=0) {  //not 0 word
         System.out.println("All of the words with their scores (sorted by score):");
         for (Map.Entry<String, Integer> cur : list) {

            System.out.println(cur.getValue() + " " + cur.getKey());

         }
      }

   }

   static class ScoreComparator implements Comparator<Map.Entry<String,Integer>>{
      public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
         return Integer.compare(o2.getValue(), o1.getValue());
      }

   }

   public static String wordFilter(String target){
      StringBuilder word = new StringBuilder();
      for(int i = 0; i<target.length();i++){
         if((target.charAt(i) >= 'a' && target.charAt(i) <= 'z') || (target.charAt(i) >= 'A' && target.charAt(i) <= 'Z')){
            word.append(target.charAt(i));
         }
      }
      return word.toString();
   }


}
