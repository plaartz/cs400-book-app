import java.util.ArrayList;
import java.util.List;

public class BookMapperBackendPH implements IBookMapperBackend{

	
	private IterableHashtableMap<String,IBook> list;
	private String filter;

	public BookMapperBackendPH() {
		BookLoader loader = new BookLoader();
		try {
		this.list = loader.loadBooks("./books.csv");
		} catch (Exception e) {
			this.list = new IterableHashtableMap<>(10);
			System.out.print("Books file not found");
		}
	}
	
	@Override
	public void addBook(IBook book) {
		// TODO Auto-generated method stub
		list.put(book.getISBN13(),book);
		
	}

	@Override
	public int getNumberOfBooks() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void setAuthorFilter(String filterBy) {
		if (filterBy==null) {
			resetAuthorFilter();
			return;
		}
		this.filter = filterBy;
	}

	@Override
	public String getAuthorFilter() {
		return this.filter;
	}

	@Override
	public void resetAuthorFilter() {
		this.filter = null;
		
	}

	@Override
	public IterableHashtableMap<String,IBook> searchByTitleWord(String word) {
		IterableHashtableMap<String,IBook> tempList = new IterableHashtableMap<>(list.hashArray.length);
		for (IBook book: list) {
			if (book.getTitle().toLowerCase().contains(word.toLowerCase())) {
				if (this.filter==null || book.getAuthors().contains(filter)) {
					tempList.put(book.getISBN13(),book);
				}
			}
		}
		return tempList;
	}

	@Override
	public IBook getByISBN(String ISBN) {
		// TODO Auto-generated method stub
		for (IBook book: list) {
			if (book.getISBN13().equals(ISBN)) {
				return book;
			}
		}
		return null;
	}

}
