import java.util.*;

public class Bookshelf {

   /**
    Representation invariant:
    The height of the books should be >0
    The bookshelf should not be null


    <put rep. invar. comment here>
    An Arraylist to store the height of books

    */

   // <add instance variables here>
   private ArrayList<Integer> bookShelf ;

   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      bookShelf = new ArrayList<Integer>();
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    *
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {

      bookShelf = pileOfBooks;
      assert isValidBookshelf();

   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      bookShelf.add(0,height);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      bookShelf.add(height);
      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      if (size() > 0) {
         return bookShelf.remove(0);
      }
      assert isValidBookshelf();
      return -1;
      //return 0;   // dummy code to get stub to compile

   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      if (size() > 0) {

         return bookShelf.remove(size() - 1);
      }
      assert isValidBookshelf();
      return -1;   // dummy code to get stub to compile
   }

   /*
    * Gets the height of the book at the given position.
    *
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {

      return bookShelf.get(position);   // dummy code to get stub to compile

   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      return bookShelf.size();

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      String strBshelf = String.valueOf(bookShelf);
      return strBshelf;

   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      for (int i = 0; i < bookShelf.size() - 1; i++) {
         if(bookShelf.get(i) > bookShelf.get(i + 1)) {
            return false;
         }
      }
      return true;

   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      if (bookShelf.size() > 0){
         for (int i = 0; i < bookShelf.size(); i++) {
            if (bookShelf.get(i) <= 0) {
               return false;
            }
         }
         return true;  // dummy code to get stub to compile

      }
      return false;

   }





}