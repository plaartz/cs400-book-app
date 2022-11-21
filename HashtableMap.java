
// --== CS400 Project One File Header ==--
// Name: Porter Laartz
// CSL Username: laartz
// Email: plaartz@wisc.edu
// Lecture #: 003 @2:25pm
import java.util.NoSuchElementException;

/*
 * HashtableMap data structure
 */
public class HashtableMap<KeyType, ValueType> {
    protected HashObject[] hashArray;
    private int size;

    /*
     * Constructor with capacity arg initializes array with capacity
     */
    public HashtableMap(int capacity) {
        hashArray = new HashObject[capacity];
    }

    /*
     * No arg constructor intializes array with capacity 15
     */
    public HashtableMap() {
        hashArray = new HashObject[15];
    }

    /**
     * Inserts a new (key, value) pair into the map if the map does not
     * contain a value mapped to key yet.
     * 
     * @param key   the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true if the (key, value) pair was inserted into the map,
     *         false if a mapping for key already exists and the
     *         new (key, value) pair could not be inserted
     */
    public boolean put(KeyType key, ValueType value) {
        if (key == null || this.containsKey(key)) {
            return false;
        }
        // If a key already exists at this spot in the array and chaining is needed
        if (hashArray[Math.abs(key.hashCode()) % hashArray.length] != null) {
            HashObject currentHashObject = hashArray[Math.abs(key.hashCode()) % hashArray.length];
            // Iterate to the end of the chain
            while (currentHashObject != null) {
                if (currentHashObject.getNext() == null) {
                    // Add a new hash object at the end of the chain and increment size
                    currentHashObject.setNext(new HashObject(key, value));
                    size++;
                    // Check if LF is greater than 70%
                    if (this.size() / (float) hashArray.length >= .7F) {
                        increaseHashCapacity();
                    }
                    return true;
                }
                currentHashObject = currentHashObject.getNext();
            }
            
        } else { // If no key exists at this sport in the array, create a new Hash object at this
                 // point and increment size
            hashArray[Math.abs(key.hashCode()) % hashArray.length] = new HashObject(key, value);
            size++;
            // Check if LF is greater than 70%
            if (this.size() / (float) hashArray.length >= .7F) {
                increaseHashCapacity();
            }
            return true;
        }

        return false;
    }

    /*
     * If the hashArray has more than 70% of its indexes filled with a hashObject,
     * double the hashArray's size and rehash all the hashObjects.
     * 
     */
    private void increaseHashCapacity() {
        // Clone the original hash array and create a new hashArray with double the
        // capacity with initial size 0
        HashObject[] tempArray = hashArray.clone();
        hashArray = new HashObject[hashArray.length * 2];
        this.size = 0;
        // Iterate through each object in the tempArray and put it in the new array
        for (HashObject hashObject : tempArray) {
            if (hashObject != null) {
                while (hashObject.getNext() != null) {
                    this.put((KeyType) hashObject.getNext().getKey(), (ValueType) hashObject.getNext().getValue());
                    hashObject.setNext(hashObject.getNext().getNext());
                }
                this.put((KeyType) hashObject.getKey(), (ValueType) hashObject.getValue());
            }
        }

    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     * 
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping
     *                                for the key
     */
    public ValueType get(KeyType key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException("null key");
        }
        HashObject currentHashObject = hashArray[Math.abs(key.hashCode()) % hashArray.length];
        // Iterates through each HashObject in this location of the array, until it
        // either finds the correct HashObject or it doesn't exist
        while (currentHashObject != null) {
            if (currentHashObject.getKey().equals(key)) {
                return (ValueType) currentHashObject.getValue();
            }
            currentHashObject = currentHashObject.getNext();
        }

        throw new NoSuchElementException("No mapping found");
    }

    /**
     * Checks if a key is stored in the map.
     * 
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map
     *         and false otherwise
     */
    public boolean containsKey(KeyType key) {
        if (key == null) {
            return false;
        }
        HashObject currentHashObject = hashArray[Math.abs(key.hashCode()) % hashArray.length];
        // Iterates through every HashObject in this location of the array to see if it
        // matches the key
        while (currentHashObject != null) {
            if (currentHashObject.getKey().equals(key)) {
                return true;
            }
            currentHashObject = currentHashObject.getNext();
        }
        return false;
    }

    /**
     * Returns the number of (key, value) pairs stored in the map.
     * 
     * @return the number of (key, value) pairs stored in the map
     */
    public int size() {
        return this.size;
    }

    /**
     * Removes a key and its value from the map.
     * 
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     *         or null if the map did not contain a mapping for key
     */
    public ValueType remove(KeyType key) {
        if (key == null || !this.containsKey(key)) {
            return null;
        }

        HashObject toRemove = null;
        // Iterate through each chain at the expected location of key
        if (hashArray[Math.abs(key.hashCode()) % hashArray.length] != null) {
            // If there is a chain of Hash Objects, find the one before the matching key,
            // and set its next to the one that's after the matching key.
            if (hashArray[Math.abs(key.hashCode()) % hashArray.length].getNext() != null) {
                // If first item in chain is the matching key
                if (hashArray[Math.abs(key.hashCode()) % hashArray.length].getKey().equals(key)) {
                    toRemove = hashArray[Math.abs(key.hashCode()) % hashArray.length];
                    hashArray[Math.abs(key.hashCode())
                            % hashArray.length] = hashArray[Math.abs(key.hashCode()) % hashArray.length].getNext();

                } else { // If item is possibly after the first item in the chain
                    HashObject currentHashObject = hashArray[Math.abs(key.hashCode()) % hashArray.length];
                    while (currentHashObject.getNext() != null) {
                        if (currentHashObject.getNext().getKey().equals(key)) {
                            toRemove = currentHashObject.getNext();
                            currentHashObject.setNext(currentHashObject.getNext().getNext());
                        } else {
                            currentHashObject = currentHashObject.getNext();
                        }
                    }
                }
            } else { // If no chaining exists
                if (hashArray[Math.abs(key.hashCode()) % hashArray.length].getKey().equals(key)) {
                    toRemove = hashArray[Math.abs(key.hashCode()) % hashArray.length];
                    hashArray[Math.abs(key.hashCode()) % hashArray.length] = null;
                }
            }
        }
        // If key exists to remove, decrement size and return the value that pairs with
        // the removed key
        if (toRemove != null) {
            size--;
            return (ValueType) toRemove.getValue();
        }
        // Else return null
        return null;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    public void clear() {
        // Iterate through the array and set all indices to null
        for (int i = 0; i < hashArray.length; i++) {
            hashArray[i] = null;
        }
        this.size = 0;
    }
    
}
