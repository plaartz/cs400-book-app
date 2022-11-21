public class Book implements IBook {
    String title;
    String authors;
    String Isbn;

    public Book(String title, String authors, String Isbn) {
        this.title = title;
        this.authors = authors;
        this.Isbn = Isbn;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getAuthors() {
        return this.authors;
    }

    @Override
    public String getISBN13() {
        return this.Isbn;
    }
    
}

