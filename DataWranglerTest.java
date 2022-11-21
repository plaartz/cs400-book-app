import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DataWranglerTest {
    public static void main(String[] args) {
        runAll();
    }

    public static void runAll() {
        System.out.println("DataWrangler Individual Test 1: " + (test1() ? "passed" : "failed"));
        System.out.println("DataWrangler Individual Test 2: " + (test2() ? "passed" : "failed"));
        System.out.println("DataWrangler Individual Test 3: " + (test3() ? "passed" : "failed"));
        System.out.println("DataWrangler Individual Test 4: " + (test4() ? "passed" : "failed"));
        System.out.println("DataWrangler Individual Test 5: " + (test5() ? "passed" : "failed"));
        System.out.println("DataWrangler Integration Test 1: " + (test6() ? "passed" : "failed"));
        System.out.println("DataWrangler Integration Test 2: " + (test7() ? "passed" : "failed"));
        System.out.println("DataWrangler Partner (AlgorithmEngineer) Test 1: " + (test8() ? "passed" : "failed"));
        System.out.println("DataWrangler Partner (AlgorithmEngineer) Test 2: " + (test9() ? "passed" : "failed"));
    }

    /**
     * Tests title
     * 
     * @return
     */
    public static boolean test1() {
        String fileName = "./books.csv";
        BookLoader loader = new BookLoader();
        try {
            IterableHashtableMap<String, IBook> Array = loader.loadBooks(fileName);
            if (Array.get("9780439785969").getTitle()
                    .equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)")) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * tests authors
     * 
     * @return
     */
    public static boolean test2() {
        String fileName = "./books.csv";
        BookLoader loader = new BookLoader();
        try {
            IterableHashtableMap<String, IBook> Array = loader.loadBooks(fileName);
            if (Array.get("9780618517657").getAuthors().equals("J.R.R. Tolkien")) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Tests isbn13
     * 
     * @return
     */
    public static boolean test3() {
        String fileName = "./books.csv";
        BookLoader loader = new BookLoader();
        try {
            IterableHashtableMap<String, IBook> Array = loader.loadBooks(fileName);
            if (Array.get("9780374280390").getISBN13().equals("9780374280390")) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Makes sure when asking for an index that doesnt exist an index out of bounds
     * exception is thrown
     * 
     * @return
     */
    public static boolean test4() {
        String fileName = "./books.csv";
        BookLoader loader = new BookLoader();
        try {
            IterableHashtableMap<String, IBook> Array = loader.loadBooks(fileName);
           Array.get("9999999999999");
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
	e.printStackTrace();
		return false;
	}
	return false;
    }

    /**
     * When passing in a null file the exception is handled inside of Book Loader
     * 
     * @return
     */
    public static boolean test5() {
        try {
            String fileName = "Null";
            BookLoader loader = new BookLoader();
            IterableHashtableMap<String, IBook> Array = loader.loadBooks(fileName);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * Testing interation of Book Object stored in a hashMap
     * 
     * @return
     */

    public static boolean test6() {
        IterableHashtableMap<String, Book> hashMap = new IterableHashtableMap<>(10);
        hashMap.put("1", new Book("Title1", "Author1", "1"));
        hashMap.put("2", new Book("Title2", "Author2", "2"));

        String output = "";
        for (Book book : hashMap) {
            output += book.getTitle() + " ";
        }
        return output.equals("Title2 Title1 ");
    }

    /**
     * Testing geting author of book objects through hashMap.
     * 
     * @return
     */

    public static boolean test7() {
        IterableHashtableMap<String, Book> hashMap = new IterableHashtableMap<>(10);
        hashMap.put("1", new Book("Title1", "Author1", "1"));
        hashMap.put("2", new Book("Title2", "Author2", "2"));

        String output = "";
        for (Book book : hashMap) {
            output += book.getAuthors() + " ";
        }
        return output.equals("Author2 Author1 ");
    }

    /**
     * Testing interation of Book Object stored in a hashMap
     * 
     * @return
     */
    public static boolean test8() {
        IISBNValidator validator = new ISBNValidator();

        // Test a valid isbn13
        if (!validator.validate("9780393324815")) {
            return false;
        }

        // Test an invalid isbn13 number

        if (validator.validate("0000000000000")) {
            return false;
        }
        return true;

    }

    /**
     * Testing iIterableHashtableMap.java iteration works properly
     * 
     * @return
     */

    public static boolean test9() {
        IterableHashtableMap<Integer, String> hashMap = new IterableHashtableMap<>(10);
        hashMap.put(0, "0");
        hashMap.put(1, "1");
        hashMap.put(2, "2");
        String output = "";

        for (String value : hashMap) {
            output += value;
        }
        return output.equals("012");
    }

}
