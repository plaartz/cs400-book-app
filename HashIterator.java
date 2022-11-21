import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Custom hashIterator that allows for iterating over a hashmap with chained items.
 */
public class HashIterator<ValueType> implements Iterator<ValueType> {
    private HashObject current;
    private IterableHashtableMap hashMap;
    private int hashLoc;

    /*
     * Sets start point to be the first element in the hashMap and brings in the hashMap
     * 
     * @param hashMap The hashmap that is to be iterated over
     */
    public HashIterator(IterableHashtableMap hashMap) {
        this.hashLoc = 0;
        this.hashMap = hashMap;
        this.current = hashMap.hashArray[0];
    }

    /*
     * if there's a next element in the hashmap returns true
     * Iterates to it as well skipping null locations
     * 
     * @return true if there's a next item, false otherwise.
     */
    @Override
    public boolean hasNext() {
        while (this.current == null && hashLoc + 1 < this.hashMap.hashArray.length) {
            this.current = this.hashMap.hashArray[++hashLoc];
        }
        return this.current != null;
    }

    /*
     * Gets the next element in the hasmap
     * 
     * @return the value stored in the next item of the hashmap
     */
    @Override
    public ValueType next() throws NoSuchElementException {
         if (this.current==null) {
            throw new NoSuchElementException("No next element");
         }
        ValueType toReturn = (ValueType) this.current.getValue();

        // If next element is chained
        if (this.current.getNext() != null) {
            this.current = this.current.getNext();
        } else { // If next element exists elsewhere in the hashmap
            if (hashLoc+1 < this.hashMap.hashArray.length) {
                this.current = this.hashMap.hashArray[++hashLoc];
            } else {
                this.current = null;
            }
            

        }
        return toReturn;

    }

}