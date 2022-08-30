public class MineFieldTester {

   public static void main(String[] args) {

      boolean[][] smallMineField =
            {{false, false, false, false},
             {true, false, false, false},
             {false, true, true, false},
             {false, true, false, true}};

      MineField mineTest = new MineField(smallMineField);
      mineTest = new MineField(9,9,10);
      mineTest.populateMineField(2,3);
      System.out.println(mineTest.numAdjacentMines(6,2));

      VisibleField field = new VisibleField(mineTest);
      for(int i =0;i<3;i++){
         for(int j=0;j<9;j++){
            field.uncover(i,j);
         }
      }


   }

}
