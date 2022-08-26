import java.util.*;
import java.util.Scanner;
import java.io.PrintStream;

public class BookshelfTester
{
   public static void main(String[] args)   {

      //TESTCASES-----------------------------------------------------------
      //INITIAL
      System.out.println("TEST CASES------------------------------------------------------------------------------------");
      ArrayList<Integer> books = new ArrayList<>();
      Bookshelf bookShelf = new Bookshelf(books);
      BookshelfKeeper bookShelfKeeper = new BookshelfKeeper(bookShelf);

      System.out.println("Enter books as [7, 10, 14, 18, 22, 26, 30, 37]");
      books.addAll(Arrays.asList(7,10,14,18,22,26,30,37));
      System.out.println("The bookshelf now is: "+bookShelf.toString());

      System.out.println("PICK POSITION 3");   //test boundary
      System.out.println("Expected operation: 7          Operation is: "+bookShelfKeeper.pickPos(3));
      System.out.println("Expected: [7, 10, 14, 22, 26, 30, 37]      The bookshelf is: "+bookShelfKeeper.toString());

      System.out.println("PICK POSITION 4");
      System.out.println("Expected operation: 5          Operation is: "+bookShelfKeeper.pickPos(4));
      System.out.println("Expected: [7, 10, 14, 22, 30, 37]      The bookshelf is: "+bookShelfKeeper.toString());

      System.out.println("PUT HEIGHT 18");   //test boundary
      System.out.println("Expected operation: 5          Operation is: "+bookShelfKeeper.putHeight(18));
      System.out.println("Expected: [7, 10, 14, 18, 22, 30, 37]      The bookshelf is: "+bookShelfKeeper.toString());

      System.out.println("PUT HEIGHT 5");   //test boundary
      System.out.println("Expected operation: 1          Operation is: "+bookShelfKeeper.putHeight(5));
      System.out.println("Expected: [5, 7, 10, 12, 14, 22, 30, 37]      The bookshelf is: "+bookShelfKeeper.toString());

      System.out.println("PUT HEIGHT 42");   //test boundary
      System.out.println("Expected operation: 1          Operation is: "+bookShelfKeeper.putHeight(42));
      System.out.println("Expected: [7, 10, 12, 14, 22, 30, 37, 42]      The bookshelf is: "+bookShelfKeeper.toString());



      //WITH INPUT---------------------------------------------------------------
      System.out.println("WITH REAL INPUT-------------------------------------------------------------------------------");

      // INITIAL BOOKS
      ArrayList<Integer> Books = new ArrayList<>();
      Bookshelf BookShelf = new Bookshelf(Books);
      BookshelfKeeper BookShelfKeeper = new BookshelfKeeper(BookShelf);

      Scanner input = new Scanner(System.in);

        System.out.println("Please enter initial arrangement of books followed by newline:");
        String line = input.nextLine().trim();
        Scanner in = new Scanner(line);
        // To put the initial books
        while (in.hasNextInt()) {
            int heightOfBook = in.nextInt();
            // PRE
            if (heightOfBook <= 0) {
                System.out.println("ERROR: Height of a book must be positive.");
                System.out.println("Exiting Program.");
               System.exit(0);
            }
            Books.add(heightOfBook);
        }
        // PRE: if they are sorted
        if (!BookShelf.isSorted()) {
            System.out.println("ERROR: Heights must be in non-decreasing order.");
            System.out.println("Exiting Program.");
            System.exit(0);
        }
        System.out.println("The bookshelf now is: "+BookShelf.toString());
        System.out.print("Please enter your operation: ");
        // To give operations
        String operation;
        int num;    // input numbers after operation
        int currOpe=0;

        while(input.hasNextLine()){
           operation = input.next();

           if(operation.equals("pick")){
              num = input.nextInt();
              currOpe = BookShelfKeeper.pickPos(num) - currOpe;
              System.out.println("Operations: "+currOpe+"   Total operations are: "+BookShelfKeeper.getTotalOperations());
              currOpe = BookShelfKeeper.getTotalOperations();
              System.out.println("The bookshelf is: "+BookShelfKeeper.toString());
              System.out.print("Please enter your operation: ");

              continue;
           }
           else if(operation.equals("put")){
              num = input.nextInt();
              currOpe = BookShelfKeeper.putHeight(num) - currOpe;
              System.out.println("Operations: "+currOpe+"   Total operations are: "+BookShelfKeeper.getTotalOperations());
              currOpe = BookShelfKeeper.getTotalOperations();
              System.out.println("The bookshelf is: "+BookShelfKeeper.toString());
              System.out.print("Please enter your operation: ");

              continue;
           }
           else if(operation.equals("end")){
              break;
           }
           else{
              System.out.println("ERROR: Please enter right operation!");
              System.out.print("Please enter your operation: ");
              continue;}

        }

   }

}