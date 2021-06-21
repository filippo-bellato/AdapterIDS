package myAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MapAdapterTest {

    MapAdapter map;

    /**
     * Precondizioni: creazione di una mappa vuota e aggiunta di 4 entry(chiave, valore)
     */

    @Before
    public void setUp(){
        map = new MapAdapter();
        map.put("chiave1", "valore1");
        map.put("chiave2", "valore2");
        map.put("chiave3", "valore3");
        map.put("chiave4", "valore4");
    }

    /**
     * clear(): chiamo il metodo sulla mappa
     * - la mappa viene correttamente svuotata (size() == 0 && isEmpty())
     */

    @Test
    public void testClear() {
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    /**
     * containsKey(Object): cerco una chiave nella mappa
     * - il metodo ritorna true se la chiave è presente nalla mappa, false altrimenti
     * - il metodo lancia NullPointerException se si passa un valore null
     */

    @Test
    public void testContainsKey() {
        assertThrows(NullPointerException.class, () -> {map.containsKey(null);});
        assertTrue(map.containsKey("chiave4"));
        assertTrue(!map.containsKey("chiave5"));
    }

    /**
     * containsValue(Object): cerco un valore nella mappa
     * - il metodo ritorna true se il valore è presente nella mappa, false altrimenti
     * - il metodo lancia NullPointerException se si passa un valore null
     */

    @Test
    public void testContainsValue() {
        assertThrows(NullPointerException.class, () -> {map.containsValue(null);});
        assertTrue(map.containsValue("valore2"));
        assertTrue(!map.containsValue("valore6"));
    }

    /**
     * entrySet(): chiamo il metodo entrySet() che ritorna un HSet
     * - il metodo restituisce un set di dimensione uguale alla dimansione della mappa
     * - nel set è possibile aggiungere delle Entry(chiave valore)
     */

    @Test
    public void testEntrySet() {
        HSet set = map.entrySet();
        assertEquals(4, set.size());
        MapAdapter.EntryAdapter entry = new MapAdapter.EntryAdapter("chiave1", "valore1");
        assertTrue(set.contains(entry));
    }

    /**
     * equals(Object): chiamo il metodo su una mappa uguale alla mappa data
     * - se l'oggetto passato è una mappa ritorna true se tutte le entry sono uguali
     * - se l'oggetto passato non è una mappa ritorna false
     */

    @Test
    public void testEquals() {
        MapAdapter m = new MapAdapter();
        m.put("chiave1", "valore1");
        m.put("chiave2", "valore2");
        m.put("chiave3", "valore3");
        m.put("chiave4", "valore4");
        assertTrue(map.equals(m));
        assertTrue(!map.equals(new Integer(4)));
    }

    /**
     * get(Object): cerco una chiave
     * - il metodo ritorna il valore corrispondente se la chiave è contenuta nella mappa, null altrimenti
     * - il metodo lancia NullPointerException se il parametro passato è null
     */

    @Test
    public void testGet() {
        assertThrows(NullPointerException.class, () -> {map.get(null);});
        assertEquals("valore2", map.get("chiave2"));
        assertNull(map.get("chiave5"));
    }

    /**
     * hashCode(): verifico che l'hashcode di due mappe uguali sia uguale
     * - se le mappe sono uguali, l'hascode è uguale
     */

    @Test
    public void testHashCode() {
        MapAdapter m = new MapAdapter();
        m.put("chiave1", "valore1");
        m.put("chiave2", "valore2");
        m.put("chiave3", "valore3");
        m.put("chiave4", "valore4");
        assertTrue(m.hashCode() == map.hashCode());
    }

    /**
     * isEmpty(): chiamo il metodo clear()
     * - il metodo restituisce true se la mappa è vuota
     */

    @Test
    public void testIsEmpty() {
        assertTrue(!map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
    }

    /**
     * keySet(): chiamo il metodo keySet() che restituisce un HSet
     * - il set contiene tutte le chiavi presenti nella mappa
     */

    @Test
    public void testKeySet() {
        HSet set = map.keySet();
        assertTrue(set.contains("chiave1"));
        assertTrue(set.contains("chiave4"));
    }

    /**
     * put(Object, Object): aggiungo degli elementi alla mappa
     * - se la mappa contiene già un valore associato alla chiave ritorna il valore precedente e lo sostituisce
     * - se la mappa non contiene già quella chiave aggiunge la entry e ritorna null
     * - se uno dei parametri è null il metodo ritorna NullPointerException
     */

    @Test
    public void testPut() {
        assertThrows(NullPointerException.class, () -> {map.put("chiave5", null);});
        assertThrows(NullPointerException.class, () -> {map.put(null, "valore5");});
        assertNull(map.put("chiave5", "valore5"));
        assertEquals("valore1", map.put("chiave1", "nuovoValore"));
        assertEquals("nuovoValore", map.get("chiave1"));
    }

    /**
     * putAll(HMap): chiamo il metodo passando una mappa
     * - il metodo aggiunge alla mappa tutte le entry contenute nella mappa passata
     * - il metodo lancia NullPointerException se il parametro passato è null
     */

    @Test
    public void testPutAll() {
        assertThrows(NullPointerException.class, () -> {map.putAll(null);});
        MapAdapter m = new MapAdapter();
        m.put("chiave1", "valore7");
        m.put("chiave2", "valore8");
        m.put("chiave5", "valore5");
        m.put("chiave6", "valore6");
        map.putAll(m);
        assertEquals("valore5", map.get("chiave5"));
        assertEquals("valore6", map.get("chiave6"));
        assertEquals("valore1", map.get("chiave1"));
    }

    /**
     * remove(Object): chiamo il metodo e rimuovo una chiave
     * - il metodo rimuove la entry associata alla chiave e ritorna il valore associato alla chiave
     * - se la mappa non contiene la chiave ritorna null
     * - la mappa diminuisce di 1 la sua dimensione
     * - il metodo lancia NullPointerException se si passa una chiave null
     */

    @Test
    public void testRemove() {
        assertThrows(NullPointerException.class, () -> {map.remove(null);});
        assertEquals("valore1", map.remove("chiave1"));
        assertEquals(3, map.size());
        assertNull(map.remove("chiave5"));
    }

    /**
     * size(): rimuovo una entry e chiamo il metodo clear()
     * - la dimensione della mappa diminuisce di 1 se viene rimossa una entry
     * - la dimansione è 0 se chiamo il metodo clear()
     */

    @Test
    public void testSize() {
        assertEquals(4, map.size());
        map.remove("chiave4");
        assertEquals(3, map.size());
        map.clear();
        assertEquals(0, map.size());
    }

    /**
     * values(): creao una HCollection chiamando il metodo values()
     * - la HCollection ritornata contiene i valori presenti nella mappa
     */

    @Test
    public void testValues() {
        HCollection coll = map.values();
        assertTrue(coll.contains("valore1"));
        assertTrue(coll.contains("valore4"));
    }
}