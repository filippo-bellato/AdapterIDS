package myAdapter;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListAdapterTest {

    ListAdapter list;

    /**
     * Precondizioni: ogni metodo viene testato su una lista(ListAdapter list) al quale vengono aggiunti 4 oggetti di tipo stringa("primo", "secondo", "terzo", "quarto")
     * Per ogni metodo verifico che la sua applicazione sull'oggetto ritorni il risultato aspettato.
     */

    @Before
    public void setUp(){
        list = new ListAdapter();
        list.add("primo");
        list.add("secondo");
        list.add("terzo");
        list.add("quarto");

    }

    /**
     * add(Object): aggiungo un elemento alla lista
     *  - il metodo aggiunge correttamente l'oggetto passato in coda alla lista
     *  - la dimensione della lista aumenta di 1
     *  - il metodo lancia NullPointerException quando si prova ad inserire un elemento null
     *  - ritorna sempre true
     */

    @Test
    public void testAdd() {
        assertEquals(4, list.size());
        assertEquals(3, list.indexOf("quarto"));
        assertTrue(list.add("quinto"));
        assertEquals(5, list.size());
        assertEquals("quinto", list.get(4));
        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    /**
     * add(int, Object): aggiungo un nuovo elemento in posizione 2
     * - il metodo aggiunge correttamente l'oggetto nella posizione desiderata
     * - la dimensione della lista aumenta di 1
     * - tutti gli elementi alla destra dell'elemento inserito vengono spostati verso destra di una posizione
     * - il metodo lancia NullPointerException se si prova ad inserire un elemento null
     * - il metodo lancia IndexOutOfBoundsException se l'indice inserito non è valido
     */

    @Test
    public void testAddIndex() {
        assertThrows(NullPointerException.class, () -> {list.add(2, null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(10, "inserimento");});
        list.add(2, "aggiunto");
        assertEquals(2, list.indexOf("aggiunto"));
        assertEquals(4, list.indexOf("quarto"));
        assertEquals(5, list.size());
    }

    /**
     * addAll(HCollection): aggiungo una collection di 2 elementi alla lista
     * - il metodo aggiunge in coda alla lista gli elementi presenti nella collection
     * - la dimensione della lista aumenta di 2
     * - il metodo ritorna true se la lista risulta modificata, false altrimenti
     * - il metodo lancia NullPointerException se la Collection è null o se contiene un elemento null
     */

    @Test
    public void testAddAll() {
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        ListAdapter l1 = new ListAdapter();
        l1.add("quinto");
        l1.add("sesto");
        assertTrue(list.addAll(l1));
        assertEquals(5, list.indexOf("sesto"));
        ListAdapter l2 = new ListAdapter();
        assertTrue(!list.addAll(l2));
    }

    /**
     * addAll(int, HCollection): aggiungo una collection di 2 elementi in posizione 1
     * - il metodo aggiunge gli elementi della Collection nella posizione specificata
     * - la dimensione della lista aumenta di 2
     * - il metodo ritorna true se la lista risulta modificata, false altrimenti
     * - il metodo lancia NullPointerException se la Collection è null o se contiene un elemento null
     * - il metodo lancia IndexOutOfBoundsException se l'indice inserito non è valido
     * - il metodo sposta tutti gli elementi alla destra dell'indice di inserimento di una posizione(per ogni elemento contenuto nella Collection) verso destra
     */

    @Test
    public void testAddAllIndex() {
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        ListAdapter l1 = new ListAdapter();
        l1.add("quinto");
        l1.add("sesto");
        assertTrue(list.addAll(1, l1));
        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(7, l1);});
        assertEquals(1, list.indexOf("quinto"));
        assertEquals(2, list.indexOf("sesto"));
        assertEquals(5, list.indexOf("quarto"));
    }

    /**
     * clear(): chiamo il metodo clear()
     * - la lista diventa vuota (size() == 0 && isEmpty)
     * - se cerco un elemento qualsiasi nella lista esso non verrà trovato
     */

    @Test
    public void testClear() {
        list.clear();
        assertEquals(0, list.size());
        assertTrue(!list.contains("primo"));
        assertTrue(list.isEmpty());
    }

    /**
     * contains(Object): cerco due oggetti nella lista, uno contenuto e uno non contenuto
     * - il metodo ritorna true se l'oggetto è trovato, false altrimenti
     * - il metodo lancia NullPointerException se il parametro passato è null
     */

    @Test
    public void testContains() {
        assertTrue(list.contains("quarto"));
        assertTrue(!list.contains("quinto"));
        assertThrows(NullPointerException.class, () -> {list.contains(null);});
    }

    /**
     * containsAll(Collection): utilizzo il metodo su 1) Collection che contiene solo un elemento presente nella lista 2) Collection che contiene alcuni elementi presenti nella lista
     * - nel primo caso ritorna false, visto che non tutti gli elementi della Collection sono presenti nella lista
     * - nel secondo caso ritorna true, visto che tutti gli elementi della Collection sono presenti nella lista
     * - il metodo lancia NullPointerException se la Collection è null o se contiene un elemento null
     */

    @Test
    public void testContainsAll() {
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        ListAdapter l1 = new ListAdapter();
        l1.add("quinto");
        l1.add("primo");
        assertTrue(!list.containsAll(l1));
        l1.clear();
        l1.add("primo");
        l1.add("secondo");
        l1.add("terzo");
        assertTrue(list.containsAll(l1));
    }

    /**
     * equals(Object): confronto la lista con una serie di oggetti, alcuni dei quali non sono delle liste
     * - se l'oggetto non è una lista ritorna false
     * - se l'oggetto è una lista diversa dalla lista da testare ritorna false
     * - se l'oggetto è una lista con gli stessi elementi(nella stessa posizione della lista) ritorna true
     */

    @Test
    public void testEquals() {
        ListAdapter l = new ListAdapter();
        l.add("primo");
        assertTrue(!list.equals(l));
        Integer I = new Integer(4);
        assertTrue(!list.equals(I));
        ListAdapter l1 = new ListAdapter();
        l1.add("primo");
        l1.add("secondo");
        l1.add("terzo");
        l1.add("quarto");
        assertTrue(list.equals(l1));
    }

    /**
     * get(int): chiamo il metodo all'indice 0 e 3
     * - il metodo ritorna l'oggetto corrispondente all'indice specificato
     * - il metodo lancia IndexOutOfBoundsException se l'indice non è valido
     */

    @Test
    public void testGet() {
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(4);});
        assertEquals("primo", list.get(0));
        assertEquals("quarto", list.get(3));
    }

    /**
     * hashCode(): verifico che l'hashcode sia uguale se calcolato su due liste con gli stessi elementi
     * - il metodo ritorna due hashcode uguali se calcolato su due liste con gli stessi elementi
     */

    @Test
    public void testHashCode() {
        ListAdapter l1 = new ListAdapter();
        l1.add("primo");
        l1.add("secondo");
        l1.add("terzo");
        l1.add("quarto");
        assertEquals(l1.hashCode(), list.hashCode());
    }

    /**
     * indexOf(Object): chiamo il metodo su un elemento della lista. Aggiungendo lo stesso elemento in coda alla lista verifico che l'indice ritornato è comunque quello del primo elemento
     * - il metodo ritorna l'indice corretto anche se aggiungo un elemento già presente nella lista
     * - il metodo lancia NullPointerException se il parametro passato è null
     */

    @Test
    public void testIndexOf() {
        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
        assertEquals(3, list.indexOf("quarto"));
        list.add("primo");
        assertEquals(0, list.indexOf("primo"));
    }

    /**
     * isEmpty(): chiamo il metodo sulla lista e verifico che essa sia correttamente svuotata
     * - il metodo ritorna true
     * - la dimensione della lista diventa 0
     */

    @Test
    public void testIsEmpty() {
        assertTrue(!list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertTrue(0 == list.size());
    }

    /**
     * iterator(): creo una iteratore della lista e verifico che i suoi metodi funzionino correttamente
     * - l'iteratore con il metodo next() ritorna correttamente gli elementi della lista ordinati
     * - se l'iteratore è alla fine della lista il metodo hasNext() ritorna false
     * - se l'iteratore è alla fine della lista e chiamo il metodo next() lancia NoSuchElementException
     * - se chiamo il metodo remove() l'iteratore restituisce l'elemento della lista corretto e lo rimuove
     */

    @Test
    public void testIterator() {
        HIterator it = list.iterator();
        assertEquals("primo", it.next());
        assertEquals("secondo", it.next());
        assertEquals("terzo", it.next());
        assertEquals("quarto", it.next());
        assertThrows(NoSuchElementException.class, () -> {it.next();});
        assertTrue(!it.hasNext());

        HIterator ite = list.iterator();
        assertEquals("primo", ite.next());
        ite.remove();
        assertTrue(!list.contains("secondo"));
        assertThrows(IllegalStateException.class, () -> {ite.remove();});
        assertEquals("terzo", ite.next());
    }

    /**
     * lastIndexOf(Object): chiamo il metodo su un elemento della lista. Aggiungendo lo stesso elemento in coda alla lista verifico che l'indice ritornato è quello del nuovo oggetto
     * - il metodo ritorna l'indice corretto se aggiungo un elemento già presente nella lista
     * - il metodo lancia NullPointerException se il parametro passato è null
     */

    @Test
    public void testLastIndexOf() {
        list.add("primo");
        assertEquals(4, list.lastIndexOf("primo"));
    }

    /**
     * listIterator(): creo un ListIterator della lista e ne verifico il comportamento
     * - verifico che se l'iteratore è all'inizio della lista hasNext() ritorna true e hasPrevious() ritorna false
     * - verifico che aggiungendo un elemento con il metodo add() del ListIterator l'oggetto viene correttamente aggiunto alla lista
     * - verifico che due chiamate consecutive di next() e previous() ritornano lo stesso elemento
     * - se aggiungo un elemento null tramite l'iteratore il metodo lancia IllegalArgumentException
     * - se chiamo previous() quando l'iteratore è all'inizio della lista il metodo lancia NoSuchElementException
     */

    @Test
    public void testListIterator() {
        HListIterator it = list.listIterator();
        assertTrue(it.hasNext());
        assertTrue(!it.hasPrevious());
        it.add("prova");
        assertEquals(0, list.indexOf("prova"));
        assertEquals("prova", it.next());
        assertEquals("prova", it.previous());
        assertThrows(IllegalArgumentException.class, () -> {it.add(null);});
        assertThrows(NoSuchElementException.class, () -> {it.previous();});
    }

    /**
     * listIterator(int): creo un ListIterator della lista partendo dall'indice 1
     * - verifico che il metodo hasNext() e hasPrevious() ritorni true
     * - verifico che due chiamate consecutive di next() e previous() ritornano lo stesso elemento
     * - verifico che il metodo add() di ListIterator aggiunge l'elemento alla lista nella posizione corretta
     * - verifico che se aggiungo un elemento null il metodo lancia IllegalArgumentException
     */

    @Test
    public void testListIteratorIndex() {
        HListIterator it = list.listIterator(1);
        assertTrue(it.hasNext());
        assertTrue(it.hasPrevious());
        it.add("prova");
        assertEquals(2, list.indexOf("prova"));
        assertEquals("prova", it.next());
        assertEquals("prova", it.previous());
        assertThrows(IllegalArgumentException.class, () -> {it.add(null);});
    }

    /**
     * remove(int): rimuovo il primo elemento della lista
     * - l'elemento ritornato è l'elemento che è stato rimosso
     * - la dimensione della lista viene ridotta di 1 e tutti gli indici degli elementi alla destra dell'elemento rimosso diminuiscono di 1
     * - il metodo lancia IndexOutOfBoundsException se l'indice passato non è valido
     */

    @Test
    public void testRemoveIndex() {
        assertEquals("primo", list.remove(0));
        assertEquals(0, list.indexOf("secondo"));
        assertEquals(3, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(4);});
    }

    /**
     * remove(Object): aggiungo un elemento già presente nella lista
     * - il metodo rimuove il primo elemento(specificato dal parametro) trovato nella lista
     * - il metodo ritorna true se l'elemento specificato era presente nella lista, false altrimenti
     * - il metodo lancia NullPointerException se l'elemento da rimuovere è null
     */

    @Test
    public void testRemoveObject() {
        list.add("primo");
        assertThrows(NullPointerException.class, () -> {list.remove(null);});
        assertTrue(list.remove("primo"));
        assertEquals(3, list.indexOf("primo"));
        assertTrue(!list.remove("quinto"));
    }

    /**
     * removeAll(Collection): creo una collection con due elementi presenti nella lista e chiamo il metodo removeAll()
     * - il metodo rimuove dalla lista tutti gli elementi contenuti nella Collection
     * - il metodo ritorna true se la lista risulta modificata, false altrimenti
     * - il metodo lancia NullPointerException se la Collection è null o se contiene elementi null
     */

    @Test
    public void testRemoveAll() {
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        ListAdapter l1 = new ListAdapter();
        l1.add("primo");
        l1.add("secondo");
        l1.add("quinto");
        assertTrue(list.removeAll(l1));
        assertEquals("terzo", list.get(0));
        assertEquals(2, list.size());
        assertTrue(!list.removeAll(l1));
    }

    /**
     * retainAll(HCollection): creo una Collection con due elementi già contenuti nella lista e chiamo il metodo retainAll()
     * - il metodo rimuove tutti gli elementi della lista tranne quelli specificati nella Collection
     * - il metodo ritorna true se la lista è modificata, false altrimenti
     * - il metodo lancia NullPointerException se la Collection è null o se contiene un elemento null
     */

    @Test
    public void testRetainAll() {
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        ListAdapter l1 = new ListAdapter();
        l1.add("primo");
        l1.add("secondo");
        assertTrue(list.retainAll(l1));
        assertEquals(0, list.indexOf("primo"));
        assertTrue(!list.contains("terzo"));
        assertTrue(!list.retainAll(l1));
    }

    /**
     * set(int, Object): sostituisco un elemento della lista
     * - il metodo ritorna l'elemento appena rimosso dalla lista
     * - l'elemento appena inserito si trova all'indice specificato
     * - il metodo lancia IndexOutOfBoundsException se l'indice specificato non è valido
     * - il metodo lancia NullPointerException se provo a chiamare set() passando un elemento null
     */

    @Test
    public void testSet() {
        assertThrows(IndexOutOfBoundsException.class, () -> {list.set(4, "quinto");});
        assertThrows(NullPointerException.class, () -> {list.set(0, null);});
        assertEquals("quarto", list.set(3, "quinto"));
        assertTrue(!list.contains("quarto"));
        assertEquals(3, list.indexOf("quinto"));
    }

    /**
     * size(): chiamo il metodo size prime e dopo aver rimosso un elemento
     * - il metodo ritorna il numero corretto di elementi presenti nella lista
     */

    @Test
    public void testSize() {
        assertEquals(4, list.size());
        list.remove(1);
        assertEquals(3, list.size());
    }

    /**
     * subList(int, int): chiamo il metodo subList e verifico che la sublist ritornata sia funzionante
     * - la sublist ritornata contiene tutti gli elementi compresi tra i due indici
     * - la sublist ritornata è funzionante
     */

    @Test
    public void testSubList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {list.subList(-1, 6);});
        ListAdapter l1 = (ListAdapter) list.subList(0, 3);
        assertEquals("primo", l1.get(0));
        assertEquals(3, l1.size());
        l1.add("quinto");
        assertTrue(l1.contains("terzo"));
        assertTrue(4 == l1.size());
    }

    /**
     * toArray(): creo un array con gli stessi elementi della lista
     * - verifico che l'array di prova è uguale all'array ritornato dal metodo
     */

    @Test
    public void testToArray() {
        Object[] arr = {"primo", "secondo", "terzo", "quarto"};
        Object[] arr1 = list.toArray();
        for(int i = 0; i < arr.length; i++)
            assertEquals(arr[i], arr1[i]);
    }

    /**
     * toArray(Object): creo un array vuoto
     * - chiamo il metodo passando l'array vuoto come parametro
     * - verifico che l'array ritornato è correttamente modificato e contiene tutti gli elementi presenti nella lista
     * - il metodo lancia NullPointerException se viene passato null
     */

    @Test
    public void testToArrayObject() {
        Object[] arr = null;
        assertThrows(NullPointerException.class, () -> {list.toArray(arr);});
        Object[] arr1 = {};
        Object[] arr2 = list.toArray(arr1);
        for(int i = 0; i < arr1.length; i++)
            assertEquals(arr2[i], arr1[i]);
    }
}