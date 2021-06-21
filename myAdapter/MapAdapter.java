package myAdapter;

import java.util.*;

/**
 * @author Filippo Bellato
 */

public class MapAdapter implements HMap{

    private Hashtable table;
    private SetAdapter eSet;


    /**
     * Constructs an empty map
     */

    public MapAdapter(){
        table = new Hashtable();
        eSet = null;
    }



    /**
     * Removes all mappings from this map.
     */

    @Override
    public void clear() {
        table.clear();
        eSet.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key - key whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified key.
     * @throws NullPointerException - if the key is null.
     */

    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        if(key == null)
            throw new NullPointerException();

        return table.containsKey(key);
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     * @param value - value whose presence in this map is to be tested.
     * @return true if this map maps one or more keys to the specified value.
     * @throws NullPointerException - if the value is null.
     */

    @Override
    public boolean containsValue(Object value) throws NullPointerException{
        if(value == null)
            throw new NullPointerException();

        return table.containsValue(value);
    }

    /**
     * Returns a set view of the mappings contained in this map. Each element in the returned set is a Map.Entry.
     * @return a set view of the mappings contained in this map.
     */

    @Override
    public HSet entrySet() {
        return eSet;
    }

    /**
     * Compares the specified object with this map for equality. Returns true if the given object is also a map and the two Maps represent the same mappings.
     * @param o - object to be compared for equality with this map.
     * @return true if the specified object is equal to this map.
     */

    public boolean equals(Object o){
        if(!(o instanceof MapAdapter))
            return false;

        MapAdapter m = (MapAdapter) o;

        if(m.size() != this.size())
            return false;

        if(m.entrySet().equals(this.entrySet()))
            return true;
        else
            return false;
    }

    /**
     * Returns the value to which this map maps the specified key. Returns null if the map contains no mapping for this key.
     * @param key - key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or null if the map contains no mapping for this key.
     * @throws NullPointerException - key is null and this map does not not permit null keys
     */

    public Object get(Object key) throws NullPointerException{
        if(key == null)
            throw new NullPointerException();

        return table.get(key);
    }

    /**
     * Returns the hash code value for this map. The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view.
     * @return the hash code value for this map.
     */

    public int hashCode(){
        EntryAdapter[] arr = (EntryAdapter[]) eSet.toArray();
        int hashcode = 0;
        for(int i = 0; i < arr.length; i++)
            hashcode += arr[i].hashCode();

        return hashcode;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings.
     */

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    /**
     * Returns a set view of the keys contained in this map.
     * @return a set view of the keys contained in this map.
     */

    @Override
    public HSet keySet() {

        Enumeration en  = table.keys();

        SetAdapter temp = new SetAdapter();

        while(en.hasMoreElements())
            temp.add(en.nextElement());

        return temp;
    }

    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for this key, the old value is replaced by the specified value.
     * @param key - key with which the specified value is to be associated.
     * @param value - value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there was no mapping for key.
     * @throws NullPointerException - this map does not permit null keys or values, and the specified key or value is null.
     */

    @Override
    public Object put(Object key, Object value) throws NullPointerException{
        if(key == null || value == null)
            throw new NullPointerException();

        eSet.add(new EntryAdapter(key, value));

        return table.put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map. The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map.
     * @param t - Mappings to be stored in this map.
     * @throws NullPointerException - the specified map is null or the specified map contains null keys or values.
     */

    @Override
    public void putAll(HMap t) throws NullPointerException{
        if(t == null)
            throw new NullPointerException();

        HSet set = t.entrySet();
        EntryAdapter[] entryArr = (EntryAdapter[]) set.toArray();

        for(int i = 0; i < entryArr.length; i++)
            if(!table.containsKey(entryArr[i].getKey())){
                table.put(entryArr[i].getKey(), entryArr[i].getValue());
                eSet.add(entryArr[i]);
            }
    }

    /**
     * Removes the mapping for this key from this map if it is present.
     * @param key - key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or null if there was no mapping for key.
     * @throws NullPointerException - if the key is null and this map does not not permit null keys
     */

    @Override
    public Object remove(Object key) throws NullPointerException{
        if(key == null)
            throw new NullPointerException();

        eSet.remove(key);

        return table.remove(key);
    }

    /**
     * Returns the number of key-value mappings in this map. If the map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of key-value mappings in this map.
     */

    @Override
    public int size() {
        return Math.min(Integer.MAX_VALUE, table.size());
    }

    /**
     * Returns a collection view of the values contained in this map.
     * @return a collection view of the values contained in this map.
     */

    @Override
    public HCollection values() {
        Enumeration en = table.elements();
        CollectionAdapter temp = new CollectionAdapter();
        while(en.hasMoreElements())
            temp.add(en.nextElement());
        return temp;
    }

    public static class EntryAdapter implements Map.Entry {

        protected Object key;
        protected Object value;

        public EntryAdapter(Object k, Object v){
            this.key = k;
            this.value = v;
        }

        /**
         * Compares the specified object with this entry for equality. Returns true if the given object is also a map entry and the two entries represent the same mapping.
         * @param o - object to be compared for equality with this map entry.
         * @return true if the specified object is equal to this map entry.
         */

        public boolean equals(Object o){
            if(!(o instanceof EntryAdapter))
                return false;

            EntryAdapter e = (EntryAdapter) o;
            return (e.getKey() == this.key) && (e.getValue() == this.value);

        }

        /**
         * Returns the key corresponding to this entry.
         * @return the key corresponding to this entry.
         */

        @Override
        public Object getKey() {
            return this.key;
        }

        /**
         * Returns the value corresponding to this entry.
         * @return the value corresponding to this entry.
         */

        @Override
        public Object getValue() {
            return this.value;
        }

        /**
         * Returns the hash code value for this map entry. The hash code of a map entry e is defined to be:
         *      (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *      (e.getValue()==null ? 0 : e.getValue().hashCode())
         * @return the hash code value for this map entry.
         */

        public int hashCode(){
            return (this.getKey()==null   ? 0 : this.getKey().hashCode()) ^
                    (this.getValue()==null ? 0 : this.getValue().hashCode());
        }

        /**
         * Replaces the value corresponding to this entry with the specified value.
         * @param value - new value to be stored in this entry.
         * @return old value corresponding to the entry.
         * @throws NullPointerException - the backing map does not permit null values, and the specified value is null.
         */

        @Override
        public Object setValue(Object value) throws NullPointerException{
            if(value == null)
                throw new NullPointerException();

            Object temp = this.value;
            this.value = value;
            return temp;
        }
    }

    /**
     * Set that contains only Entry
     */

    private class SetAdapter implements HSet{

        protected Vector vector;

        @Override
        public boolean contains(Object o) {
            return vector.contains(o);
        }

        @Override
        public boolean containsAll(HCollection c) {
            Object[] arr = c.toArray();
            for(int i = 0; i < c.size(); i++)
                if(arr[i] == null)
                    throw new NullPointerException();

            for(int i = 0; i < c.size(); i++){
                if(!vector.contains(arr[i]))
                    return false;
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            return vector.isEmpty();
        }

        @Override
        public HIterator iterator() {
            return null;
        }

        @Override
        public boolean remove(Object o) {
            return vector.removeElement(o);
        }

        @Override
        public boolean removeAll(HCollection c) {
            Object[] arr = c.toArray();
            Object[] temp = vector.toArray();

            for(int i = 0; i < c.size(); i++){
                while(vector.contains(arr[i]))
                    vector.removeElement(arr[i]);
            }

            if(temp.length != vector.size())
                return true;
            else
                for(int i = 0; i < temp.length; i++)
                    if(temp[i] != vector.get(i))
                        return true;

            return false;
        }

        @Override
        public boolean retainAll(HCollection c) {
            Object[] arr = c.toArray();
            for(int i = 0; i < c.size(); i++)
                if(arr[i] == null)
                    throw new NullPointerException();

            Vector v = new Vector();

            for(int i = 0; i < size(); i++){
                if(c.contains(vector.elementAt(i)))
                    v.addElement(vector.elementAt(i));
            }

            if (!v.equals(vector)) {
                vector = v;
                return true;
            }

            return false;
        }

        @Override
        public int size() {
            return vector.size();
        }

        @Override
        public Object[] toArray() {
            Object[] arr = new Object[size()];

            vector.copyInto(arr);

            return arr;
        }

        @Override
        public Object[] toArray(Object[] a) {
            if(a == null)
                throw new NullPointerException();

            if(a.length < size())
                a = new Object[size()];

            vector.copyInto(a);
            return a;
        }

        public boolean add(Object o){
            if(vector.contains(o))
                return false;

            vector.addElement(o);
            return true;
        }

        public void clear(){
            vector.clear();
        }
    }

    private class CollectionAdapter implements HCollection{

        private Vector vector;

        @Override
        public boolean add(Object o) {
            vector.addElement(o);
            return true;
        }

        @Override
        public boolean addAll(HCollection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(HCollection c) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public HIterator iterator() {
            return null;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean removeAll(HCollection c) {
            return false;
        }

        @Override
        public boolean retainAll(HCollection c) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }
    }
}
