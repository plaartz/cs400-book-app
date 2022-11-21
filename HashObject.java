
// --== CS400 Project One File Header ==--
// Name: Porter Laartz
// CSL Username: laartz
// Email: plaartz@wisc.edu
// Lecture #: 003 @2:25pm

/*
 * HashObject which pairs keys and values together as well. Has chaining capabilities with next
 */
public class HashObject<KeyType,ValueType> {
    private KeyType key;
    private ValueType value;
    private HashObject next;

    public HashObject(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public HashObject() {
        this.key = null;
        this.value = null;
    }

    public ValueType getValue() {
        return this.value;
    }
    public KeyType getKey() {
        return this.key;
    }

    public void setNext(HashObject next) {
        this.next = next;
    }
    public HashObject getNext() {
        return this.next;
    }
}