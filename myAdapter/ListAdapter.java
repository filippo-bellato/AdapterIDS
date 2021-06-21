package myAdapter;


import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * @author Filippo Bellato
 */

public class ListAdapter implements HList{

    //adapted class
    private Vector vector;

    /**
     * Constructs an empty List
     */
    public ListAdapter(){
        this.vector = new Vector();
    }

    /**
     * Constructs a list starting from an existing vector
     * @param v - specified vector
     */

    public ListAdapter(Vector v){
        this.vector = v;
    }


    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */

    @Override
    public void add(int index, Object element) throws NullPointerException, IndexOutOfBoundsException {
        if(element == null)
            throw new NullPointerException();

        if (index < 0 || index > vector.size())
            throw new IndexOutOfBoundsException();

        vector.insertElementAt(element, index);
    }

    /**
     * Appends the specified element to the end of this list
     * @param o - element to be appended to this list.
     * @return true
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public boolean add(Object o) throws NullPointerException{
        if (o == null)
            throw new NullPointerException();

        vector.addElement(o);
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator
     * @param c - collection whose elements are to be added to this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException  - if the specified collection contains one or more null elements and this list does not support null elements, or if the specified collection is null.
     */

    @Override
    public boolean addAll(HCollection c) throws NullPointerException{
        if (c == null)
            throw new NullPointerException();


        if(c.isEmpty())
            return false;

        Object[] arr = c.toArray();
        for(int i = 0; i < c.size(); i++)
            if(arr[i] == null)
                throw new NullPointerException();

        for(int i = 0; i < c.size(); i++)
            vector.add(arr[i]);
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right
     * @param index - index at which to insert first element from the specified collection.
     * @param c - elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws IllegalArgumentException - if some aspect of one of elements of the specified collection prevents it from being added to this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */

    @Override
    public boolean addAll(int index, HCollection c) throws NullPointerException, IndexOutOfBoundsException{
        if(c == null)
            throw new NullPointerException();

        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        //if collection isEmpty() = true, return false
        if(c.isEmpty())
            return false;

        Object[] arr = c.toArray();
        for(int i = 0; i < c.size(); i++)
            if(arr[i] == null)
                throw new NullPointerException();

        for(int i = 0; i < c.size(); i++)
            vector.insertElementAt(arr[i], index++);

        return true;
    }

    /**
     * Removes all of the elements from this list.
     */

    @Override
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * Returns true if this list contains the specified element.
     * @param o - element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements
     */

    @Override
    public boolean contains(Object o) throws NullPointerException{
        if(o == null)
            throw new NullPointerException();

        return vector.contains(o);
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c - collection to be checked for containment in this list.
     * @return true if this list contains all of the elements of the specified collection.
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     */

    @Override
    public boolean containsAll(HCollection c) throws NullPointerException{
        if(c == null)
            throw new NullPointerException();

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

    /**
     * Compares the specified object with this list for equality. Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal.
     * @param o - the object to be compared for equality with this list.
     * @return true if the specified object is equal to this list.
     */


    @Override
    public boolean equals(Object o){
        if(!(o instanceof ListAdapter))
            return false;
        ListAdapter l = (ListAdapter) o;

        if (l.size() != this.size())
            return false;

        for(int i = 0; i < size(); i++)
            if(l.get(i) != this.get(i))
                return false;

        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - Returns the element at the specified position in this list.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range
     */

    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        return vector.elementAt(index);
    }

    /**
     * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
     *   hashCode = 1;
     *   Iterator i = list.iterator();
     *   while (i.hasNext()) {
     *       Object obj = i.next();
     *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *   }
     * @return the hash code value for this list.
     */


    @Override
    public int hashCode(){
        int hashCode = 1;
        HIterator i = this.iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o - element to search for.
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public int indexOf(Object o) throws NullPointerException{
        if(o == null)
            throw new NullPointerException();

        return vector.indexOf(o);
    }

    /**
     * Returns true if this list contains the specified element.
     * @return true if this list contains no elements.
     */

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence.
     */

    @Override
    public HIterator iterator() {
        return new Iterator(this);
    }

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o - element to search for.
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public int lastIndexOf(Object o) throws NullPointerException{
        if(o == null)
            throw new NullPointerException();

        return vector.lastIndexOf(o);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */

    @Override
    public HListIterator listIterator() {
        return new ListIterator(this);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @param index - index of first element to be returned from the list iterator
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */

    @Override
    public HListIterator listIterator(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        return new ListIterator(this, index);
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
     * @param index - the index of the element to removed.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        Object o = vector.elementAt(index);
        vector.removeElementAt(index);
        return o;
    }

    /**
     * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is unchanged.
     * @param o - element to be removed from this list, if present.
     * @return true if this list contained the specified element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public boolean remove(Object o) throws NullPointerException{
        if(o == null)
            throw new NullPointerException();

        return vector.removeElement(o);
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     * @param c - collection that defines which elements will be removed from this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if this list contains one or more null elements and the specified collection does not support null elements or if the specified collection is null.
     */

    @Override
    public boolean removeAll(HCollection c) throws NullPointerException{
        if(c == null)
            throw new NullPointerException();

        Object[] arr = c.toArray();
        for(int i = 0; i < c.size(); i++)
            if(arr[i] == null)
                throw new NullPointerException();

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

    /**
     * Retains only the elements in this list that are contained in the specified collection. In other words, removes from this list all the elements that are not contained in the specified collection.
     * @param c - collection that defines which elements this set will retain.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if this list contains one or more null elements and the specified collection does not support null elements or if the specified collection is null.
     */

    @Override
    public boolean retainAll(HCollection c) throws NullPointerException{
        if(c == null)
            throw new NullPointerException();

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

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index - index of element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws NullPointerException - if the specified element is null.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */

    @Override
    public Object set(int index, Object element) throws NullPointerException, IndexOutOfBoundsException{
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        if(element == null)
            throw new NullPointerException();

        Object o = vector.elementAt(index);
        vector.setElementAt(element, index);
        return o;
    }

    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this list.
     */

    @Override
    public int size() {
        return Math.min(this.vector.size(), Integer.MAX_VALUE);
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.)
     * @param fromIndex - low endpoint (inclusive) of the subList.
     * @param toIndex - high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws IndexOutOfBoundsException - for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
     */

    @Override
    public HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
        if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();

        ListAdapter list = new ListAdapter();
        for(int i = fromIndex; i < toIndex; i++)
            list.add(vector.elementAt(i));

        return list;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * @return an array containing all of the elements in this list in proper sequence.
     */

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];

        vector.copyInto(arr);

        return arr;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     * @param a - the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list.
     * @throws NullPointerException - if the specified array is null.
     */

    @Override
    public Object[] toArray(Object[] a) throws NullPointerException{
        if(a == null)
            throw new NullPointerException();

        if(a.length < size())
            a = new Object[size()];

        vector.copyInto(a);
        return a;
    }


    private class Iterator implements HIterator{

        protected ListAdapter list;
        protected int index;
        protected boolean next;

        public Iterator(ListAdapter v){
            index = 0;
            list = v;
            next = false;
        }

        /**
         * Returns true if the iteration has more elements.
         * @return true if the iterator has more elements.
         */

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration.
         * @throws NoSuchElementException - iteration has no more elements.
         */

        @Override
        public Object next() throws NoSuchElementException {
            if(!hasNext())
                throw new NoSuchElementException();
            next = true;

            return list.get(index++);
        }

        /**
         * Removes from the underlying collection the last element returned by the iterator. This method can be called only once per call to next.
         * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
         */

        @Override
        public void remove() {
            if(!next)
                throw new IllegalStateException();

            list.remove(index);
            next = false;
        }
    }


    private class ListIterator implements HListIterator{

        protected ListAdapter list;
        protected int index;
        protected boolean last;

        public ListIterator(ListAdapter v){
            list = v;
            index = -1;
            last = false;
        }

        public ListIterator(ListAdapter v, int in){
            list = v;
            index = in;
            last = false;
        }

        /**
         * Inserts the specified element into the list (optional operation). The element is inserted immediately before the next element that would be returned by next, if any, and after the next element that would be returned by previous, if any.
         * @param o - the element to insert.
         * @throws IllegalArgumentException - if the element is null.
         */

        @Override
        public void add(Object o) throws IllegalArgumentException{
            if(o == null)
                throw new IllegalArgumentException();
            list.add(++index, o);
            last = true;
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the forward direction.
         * @return true if the list iterator has more elements when traversing the list in the forward direction.
         */

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
         * @return true if the list iterator has more elements when traversing the list in the reverse direction.
         */

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        /**
         * Returns the next element in the list.
         * @return the next element in the list.
         * @throws NoSuchElementException - if the iteration has no next element.
         */

        @Override
        public Object next() throws NoSuchElementException{
            if(!hasNext())
                throw new NoSuchElementException();
            last = true;
            return list.get(index++);
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next.
         * @return the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
         */

        @Override
        public int nextIndex() {
            return Math.min(++index, list.size());
        }

        /**
         * Returns the previous element in the list.
         * @return the previous element in the list.
         * @throws NoSuchElementException - if the iteration has no previous element.
         */

        @Override
        public Object previous() throws NoSuchElementException{
            if(!hasPrevious())
                throw new NoSuchElementException();
            last = true;
            return list.get(--index);
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous.
         * @return the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
         */

        @Override
        public int previousIndex() {
            return index;
        }

        /**
         * Removes from the list the last element that was returned by next or previous
         * @throws IllegalStateException - neither next nor previous have been called, or remove or add have been called after the last call to * next or previous.
         */

        @Override
        public void remove() throws IllegalStateException{
            if(!last)
                throw new IllegalStateException();
            last = false;
            list.remove(index);
        }

        /**
         * Replaces the last element returned by next or previous with the specified element
         * @param o - the element with which to replace the last element returned by next or previous.
         * @throws IllegalArgumentException - if some aspect of the specified element prevents it from being added to this list.
         * @throws IllegalStateException - if neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
         */

        @Override
        public void set(Object o) throws IllegalStateException, IllegalArgumentException{
            if(!last)
                throw new IllegalStateException();

            if(o == null)
                throw new IllegalArgumentException();

            list.set(index, o);
        }

    }

    private class SubList{
        //inclusive
        protected int fromIndex;
        //exclusive
        protected int toIndex;
        protected ListAdapter list;

        public SubList(ListAdapter l, int from, int to){
            list = l;
            fromIndex = from;
            toIndex = to;
        }
    }
}
