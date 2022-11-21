import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AlgorithmEngineerTest {

    /*
     * Test HashIterator.java hasNext() method
     */
    public static boolean test1() {
        IterableHashtableMap<Integer, String> hashMap = new IterableHashtableMap<>(10);
        hashMap.put(0, "0");
        hashMap.put(2, "2");
        hashMap.put(22, "22");

        Iterator<String> iterate = new HashIterator<>(hashMap);
        if (!iterate.hasNext()) {
            return false;
        }
        iterate.next();

        if (!iterate.hasNext()) {
            return false;
        }
        iterate.next();

        if (!iterate.hasNext()) {
            return false;
        }
        iterate.next();

        if (iterate.hasNext()) {
            return false;
        }

        return true;
    }

    /*
     * Test HashIterator.java next() method
     */
    public static boolean test2() {
        IterableHashtableMap<Integer, String> hashMap = new IterableHashtableMap<>(10);
        hashMap.put(0, "0");
        hashMap.put(2, "2");
        hashMap.put(22, "22");
        hashMap.put(9, "9");
        Iterator<String> iterate = new HashIterator<>(hashMap);
        String expectedOutput = "02229";
        String output = "";

        while (iterate.hasNext()) {
            output += iterate.next();
        }
        if (!output.equals(expectedOutput)) {
            return false;
        }
        try {
            iterate.next();
        } catch (NoSuchElementException e) {
            return true;
        }

        return false;

    }

    /*
     * Test IterableHashtableMap.java works the same as HashtableMap.java
     */
    public static boolean test3() {
        IterableHashtableMap<Integer, String> hashMap = new IterableHashtableMap<>(10);
        hashMap.put(0, "0");
        hashMap.put(1, "1");
        hashMap.put(11, "11");
        hashMap.put(9, "9");
        if (!hashMap.get(0).equals("0") || !hashMap.get(1).equals("1") || !hashMap.get(11).equals("11")
                || !hashMap.get(9).equals("9")) {
            return false;
        }

        try {
            hashMap.get(111);
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    /*
     * Test ISBNValidator.java
     */
    public static boolean test4() {
        ISBNValidator isbnValid = new ISBNValidator();

        // Tests a valid isbn13
        if (!isbnValid.validate("9780439785969")) {
            return false;
        }
        // Tests isbn numbers that are too short or too long
        if (isbnValid.validate("012345678901") || isbnValid.validate("01234567890123")) {
            return false;
        }

        // Tests an invalid isbn13 number
        if (isbnValid.validate("1111111111111")) {
            return false;
        }

        return true;
    }

    /*
     * Test IterableHashtableMap.java iteration works properly
     */
    public static boolean test5() {
        IterableHashtableMap<Integer, String> hashMap = new IterableHashtableMap<>(10);
        hashMap.put(0, "0");
        hashMap.put(2, "2");
        hashMap.put(22, "22");
        hashMap.put(9, "9");
        String expectedOutput = "02229";
        String output = "";

        for (String value : hashMap) {
            output += value;
        }
        return output.equals(expectedOutput);
    }

    /*
     * Test Iteration of Book Object stored in a hashMap
     */
    public static boolean test6() {
        IterableHashtableMap<String, Book> hashMap = new IterableHashtableMap<>(10);
        hashMap.put("9781429785969", new Book("Title1", "Author1", "9781429785969"));
        hashMap.put("9780439785969", new Book("Title2", "Author2", "9780439785969"));
        hashMap.put("9782419785969", new Book("Title3", "Author3", "9782419785969"));

        String output = "";
        for (Book book : hashMap) {
            output += book.getTitle() + ", " + book.getAuthors() + ", " + book.getISBN13() + "\n";
        }
        return output.equals(
                "Title2, Author2, 9780439785969\nTitle1, Author1, 9781429785969\nTitle3, Author3, 9782419785969\n");
    }

    /*
     * Test ISBNValidator when using getKey() from book object
     */
    public static boolean test7() {
        IterableHashtableMap<String, Book> hashMap = new IterableHashtableMap<>(10);
        ISBNValidator validator = new ISBNValidator();
        hashMap.put("9781429785969", new Book("Title1", "Author1", "9781429785969"));
        hashMap.put("9780439785969", new Book("Title2", "Author2", "9780439785969"));
        hashMap.put("9782419785969", new Book("Title3", "Author3", "9782419785969"));

        for (Book book : hashMap) {
            if (!validator.validate(book.getISBN13())) {
                return false;
            }
        }
        return true;
    }

    public static boolean test8() {
        String expected = "Harry Potter 1";
        IBook book = new Book("Harry Potter 1", "J.K. Rowling", "9780000000000");
        return book.getTitle().equals("Harry Potter 1") && book.getAuthors().equals("J.K. Rowling")
                && book.getISBN13().equals("9780000000000");
    }

    public static boolean test9() {
        BookLoader loader = new BookLoader();
        try {
        IterableHashtableMap<String,IBook> bookArray = loader.loadBooks("./books.csv");
        return bookArray.get("9780439785969").getTitle().equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)") &&
                bookArray.get("9780439785969").getAuthors().equals("J.K. Rowling/Mary GrandPré")
                && bookArray.get("9780439785969").getISBN13().equals("9780439785969");
        } catch (Exception e) {
            return false;
        }
        
    }

    public static void main(String[] args) {
        System.out.println("AlgorithmEngineer Individual Test 1: " + (test1() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Individual Test 2: " + (test2() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Individual Test 3: " + (test3() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Individual Test 4: " + (test4() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Individual Test 5: " + (test5() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Integration Test 1: " + (test6() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Integration Test 2: " + (test7() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Partner (DataWrangler) Test 1: " + (test8() ? "passed" : "failed"));
        System.out.println("AlgorithmEngineer Partner (DataWrangler) Test 2: " + (test9() ? "passed" : "failed"));
    }
}
