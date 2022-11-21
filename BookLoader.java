import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookLoader implements IBookLoader{
    // private String fileString;
    private IterableHashtableMap<String,IBook> list;
    public static void main(String[] args) {
        
    }
    public BookLoader(){
        this.list = new IterableHashtableMap<>(100);
    }
    /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public IterableHashtableMap<String,IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepathToCSV), "UTF-8");
        scanner.nextLine();
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            scanner.next();
            String title = scanner.next();
            String authors = scanner.next();
            scanner.next();
            scanner.next();
            String isbn = scanner.next();
            try {
            this.list.put(isbn,new Book(title, authors, isbn));
            scanner.nextLine();
            }
            catch (NoSuchElementException e){
                System.out.println("End of Line " + title);
            }
        }
        scanner.close();
        return this.list;
    }

    public IterableHashtableMap<String,IBook> getList(){
        return this.list;
    }

}
