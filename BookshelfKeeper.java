import java.util.*;
/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

   /**
    Representation invariant:

    <put rep. invar. comment here>

    */

   // <add instance variables here>
   private ArrayList<Integer> bookShelfStore ;
   private Bookshelf bookShelf ;
   private int operation;
   private int putPos;


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookShelf = new Bookshelf();
      operation = 0;
      bookShelfStore = new ArrayList<Integer>();

      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      if(sortedBookshelf.isSorted()){
         bookShelf = new Bookshelf();
         bookShelf = sortedBookshelf;
         bookShelfStore = new ArrayList<Integer>();

         operation = 0;

      }
      assert isValidBookshelfKeeper();

   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      if(position>=0 && position<bookShelf.size()){           //PRE

         if(position>=bookShelf.size()/2){                    //pick from the end of bookshelf
            for(int i = getNumBooks()-1; i>position; i--){
               bookShelfStore.add(bookShelf.removeLast());    //remove all till the position
               operation++;
            }
            bookShelf.removeLast();                            //remove the position one
            operation++;
            for (int i = bookShelfStore.size()-1; i >= 0; i--){
               bookShelf.addLast(bookShelfStore.get(i));        //put books back
               operation++;
            }
            bookShelfStore.clear();

            assert isValidBookshelfKeeper();

            return operation;

         }

         else{                                                   //pick from the front of bookshelf
            for(int i = 0; i < position; i++){
               bookShelfStore.add(bookShelf.removeFront());      //remove all till the position
               operation++;
            }
            bookShelf.removeFront();
            operation++;
            for (int i = bookShelfStore.size()-1; i >= 0; i--){  //put them back
               bookShelf.addFront(bookShelfStore.get(i));        //put books back
               operation++;
            }

         }
         bookShelfStore.clear();
         assert isValidBookshelfKeeper();
         return operation;

      }
      else {
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
         System.out.println("Exiting Program.");
         System.exit(0);
      }
      return -1;   // dummy code to get stub to compile
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
    * after the insertion.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {
      int dupliNum = 0;
      if (height > 0) {                                                             //PRE
         if(bookShelf.size()==1){
            if(height>bookShelf.getHeight(0)){putPos=1;}
            else {putPos=0;}
         }
         else {
            for (int i = 0; i < bookShelf.size() - 1; i++) {                             //find the position in bookshelf
               if (height > bookShelf.getHeight(i) && height < bookShelf.getHeight(i + 1)) {
                  putPos = i + 1;
                  break;
               } else if (height <= bookShelf.getHeight(0)) {                   //put the 1st place
                  putPos = 0;
                  break;
               } else if (height >= bookShelf.getHeight(bookShelf.size() - 1)) {//put the last place
                  putPos = bookShelf.size();
                  break;
               }
               else if(height == bookShelf.getHeight(i)){                               //EXTRA: have duplicate books
                  for(int j = i; j<bookShelf.size();j++){
                        if(height == bookShelf.getHeight(j)){
                           dupliNum++;                                                  //find duplicate numbers
                        }
                     }
                  if(bookShelf.size()-i-dupliNum>=i){                                   //have more books from the end, put from the end
                     putPos = i;
                     break;
                  }
                  else {                                                               //put from the front
                     putPos = i+dupliNum;
                     break;
                  }


               }
            }
         }


         if (putPos > bookShelf.size() / 2) {                    //put from the end of bookshelf
            for (int i = getNumBooks() - 1; i >= putPos; i--) {
               bookShelfStore.add(bookShelf.removeLast());        //remove all till the position
               operation++;
            }
            bookShelf.addLast(height);                            //add the position one
            operation++;
            for (int i = bookShelfStore.size() - 1; i >= 0; i--) {
               bookShelf.addLast(bookShelfStore.get(i));          //put books back
               operation++;
            }
            bookShelfStore.clear();
            return operation;

         }
         else {                                                //pick from the front of bookshelf
            for (int i = 0; i < putPos; i++) {
               bookShelfStore.add(bookShelf.removeFront());    //remove all till the position
               operation++;
            }
            bookShelf.addFront(height);
            operation++;
            for (int i = bookShelfStore.size() - 1; i >= 0; i--) {  //put them back
               bookShelf.addFront(bookShelfStore.get(i));
               operation++;
            }
            bookShelfStore.clear();
            return operation;
         }

      }
      else {
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
         System.out.println("Exiting Program.");
         System.exit(0);
      }
      return -1;   // dummy code to get stub to compile
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return operation;   // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return bookShelf.size();   // dummy code to get stub to compile
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed
    * by the number of bookshelf mutator calls made to perform the last pick or put operation,
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {
      String strBshelf = String.valueOf(bookShelf);
      assert isValidBookshelfKeeper();
      return strBshelf;
      //return "";   // dummy code to get stub to compile

   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      for (int i = 0; i < bookShelf.size() - 1; i++) {
         if(bookShelf.getHeight(i) > bookShelf.getHeight(i + 1)) {
            return false;
         }
      }
      return true;

   }

   // add any other private methods here


}
