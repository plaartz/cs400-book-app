import java.util.Iterator;

/*
 * Extension of HashtableMap.java that allows for iteration over it's elements
 */
public class IterableHashtableMap<KeyType, ValueType> extends HashtableMap<KeyType, ValueType>
        implements IterableMapADT<KeyType, ValueType> {

    /*
     * Constructor that initializes hashMap with capacity
     * 
     * @param capacity initial capacity of the hashmap
     */
    public IterableHashtableMap(int capacity) {
        hashArray = new HashObject[capacity];
    }

    /*
     * No arg constructor intializes array with capacity 15
     */
    public IterableHashtableMap() {
        hashArray = new HashObject[15];
    }

    /*
     * 
     * @return returns a HashIterator to iterate over a hashmap
     */
    @Override
    public Iterator<ValueType> iterator() {
        return new HashIterator<ValueType>(this);
    }

}