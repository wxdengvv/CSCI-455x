import java.util.ArrayList;

public class ScoreTable {
   /*
   (1 point)-A, E, I, O, U, L, N, S, T, R----------0,4,8,11,13,14,17,18,19,20,
(2 points)-D, G----------3,6
(3 points)-B, C, M, P------------1,2,12,15
(4 points)-F, H, V, W, Y----------5,7,21,22,24
(5 points)-K------------10,
(8 points)- J, X---------9,23
(10 points)-Q, Z---------16,25
   */

   private int totalS;  //total score of a string
   private int letterS; //the subtraction of letter and 'a'
   private int point;  //the final point

   public ScoreTable() {

   }
   public int getScore(String target) {
      String words = target.toLowerCase();  //all use lowercase

      totalS = 0;
      for(int i =0; i<target.length();i++){
         letterS = (words.charAt(i)-'a');
         //1 point: 0,4,8,11,13,14,17,18,19,20
         if(letterS==0||letterS==4||letterS==8||letterS==11||letterS==13||letterS==14||letterS==17||letterS==18||letterS==19||letterS==20){
            point = 1;
         }
         //2 points: 3,6
         else if(letterS==3||letterS==6){
            point = 2;
         }
         //3 points: 1,2,12,15
         else if(letterS==1||letterS==2||letterS==12||letterS==15){
            point = 3;
         }
         //4 points: 5,7,21,22,24
         else if(letterS==5||letterS==7||letterS==21||letterS==22||letterS==24){
            point = 4;
         }
         //5 points: 10
         else if(letterS==10){
            point = 5;
         }
         //8 points: 9,23
         else if(letterS==9||letterS==23){
            point = 8;
         }
         //10 points: 16,25
         else if(letterS==16||letterS==25){
            point = 10;
         }

         totalS+=point;
      }

      return totalS;

   }






}
