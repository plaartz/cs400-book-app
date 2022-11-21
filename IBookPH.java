
public class IBookPH implements IBook{

	private String title;
	
	private String author;
	
	private String isbn;
	
	public IBookPH(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return "Diary";
	}
	
	public String getAuthors() {
		return "Dave";
	}

	@Override
	public String getISBN13() {
		// TODO Auto-generated method stub
		return "0000000000000";
	}
}