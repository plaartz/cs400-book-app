import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides the frontend implementation of the BookMapper project
 */
public class BookMapperFrontend implements IBookMapperFrontend {

	private Scanner scnr;
	private IBookMapperBackend backend;
	private IISBNValidator validator;

	/**
	 * The constructor that the implementation this interface will
	 * provide. It takes the Scanner that will read user input as
	 * a parameter as well as the backend and the ISBNnalidator.
	 */
	public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator) {

		this.scnr = userInputScanner;
		this.backend = backend;
		this.validator = validator;

	}

	/**
	 * This method starts the command loop for the frontend, and will
	 * terminate when the user exists the app.
	 */
	public void runCommandLoop() {
		System.out.println("Welcome to the Book Mapper Application!");
		System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
		System.out.println();
		displayMainMenu();
	}

	// to help make it easier to test the functionality of this program,
	// the following helper methods will help support runCommandLoop():

	/**
	 * This method displays the main menu to the user
	 */
	@Override
	public void displayMainMenu() {
		// prints command options to System.out
		System.out.println("You are in the Main Menu:");
		System.out.println("\t1) Lookup ISBN");
		System.out.println("\t2) Search by Title Word");
		System.out.println("\t3) Set Author Name Filter");
		System.out.println("\t4) Exit Application");
		int inputVal = 0;

		inputVal = scnr.nextInt();

		scnr.nextLine();
		while (inputVal != 4) {
			if (inputVal == 1) {
				isbnLookup();
			}
			if (inputVal == 2) {
				titleSearch();
			}
			if (inputVal == 3) {
				System.out.println("You are in the Set Author Filter Menu: ");
				System.out.print("\tAuthor name must currently contain: ");
				System.out.println(backend.getAuthorFilter() == null ? "none" : backend.getAuthorFilter());
				System.out.print("\tEnter a new string for author names to contain (empty for any): ");
				backend.setAuthorFilter(scnr.nextLine());

			}
			if (inputVal <= 0 || inputVal > 4) {
				System.out.println("Invalid input");
				displayMainMenu();
			}
			System.out.println("You are in the Main Menu:");
			System.out.println("\t1) Lookup ISBN");
			System.out.println("\t2) Search by Title Word");
			System.out.println("\t3) Set Author Name Filter");
			System.out.println("\t4) Exit Application");
			inputVal = scnr.nextInt();
			scnr.nextLine();
		}
		System.out.println("Goodbye!");

	}

	@Override
	/*
	 * This method displays the list of books
	 */
	public void displayBooks(IterableHashtableMap<String, IBook> books) {
		System.out.println("Matches ("
				+ (backend.getAuthorFilter() == null ? "any author" : "author filter: " + backend.getAuthorFilter())
				+ ") " + books.size() + " of " + backend.getNumberOfBooks());
		int i = 1;
		for (IBook book : books) {
			System.out.println(
					i++ + ". \"" + book.getTitle() + "\" by " + book.getAuthors() + ", ISBN: " + book.getISBN13());
			System.out.println();
		}
		System.out.println("Matches ("
				+ (backend.getAuthorFilter() == null ? "any author" : "author filter: " + backend.getAuthorFilter())
				+ ") " + books.size() + " of " + backend.getNumberOfBooks());
	}

	@Override
	/*
	 * This method displays the book with the specified isbn
	 */
	public void isbnLookup() {
		// reads word from System.in, displays results
		System.out.println("You are in the Lookup ISBN Menu:");
		System.out.print("\tEnter ISBN to look up: ");
		String input = scnr.nextLine().trim();
		if (validator.validate(input)) {
			IBook book = (Book) backend.getByISBN(input);
			System.out
					.println("1. \"" + book.getTitle() + "\" by " + book.getAuthors() + ", ISBN: " + book.getISBN13());
		} else {
			System.out.println("Invalid input");
		}
		System.out.println();

		return;
	}

	/*
	 * This method displays the book with the specified title search
	 */
	public void titleSearch() {
		// reads author name from System.in, displays results
		System.out.println("You are in the Search for Title Word Menu: ");
		System.out.print("\tEnter a word to search for in book titles (empty for all books): ");
		String input = scnr.nextLine().trim();

		displayBooks(backend.searchByTitleWord(input));
		return;
	}

	public static void main(String[] args) {
		BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in), new BookMapperBackendPH(),
				new ISBNValidator());
		frontend.runCommandLoop();
	}

}
