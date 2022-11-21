
/*
 * Validates an ISBN number passed to it
 */
public class ISBNValidator implements IISBNValidator{

    /**
     * Checks is the given ISBN number is a valid ISBN13 number.
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */

	public boolean validate(String isbn13) {
	if (isbn13 == null || isbn13.length() != 13) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (((i % 2) * 2) + 1) 
            * (Integer.parseInt(String.valueOf(isbn13.charAt(i))));
        }
        int lastDigit = 10 - (sum % 10);

        return Integer.parseInt(String.valueOf(isbn13.charAt(12))) == lastDigit;
	}
}