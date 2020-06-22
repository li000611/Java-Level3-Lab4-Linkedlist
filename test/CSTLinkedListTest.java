import lab4.CSTLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CSTLinkedListTest {
	public CSTLinkedList<String> list;
	
	@BeforeEach
	void setup() {
		list = new CSTLinkedList<>();
		list.clear();
		list.addLast("0");
		list.addLast("1");
		list.addLast("2");
		list.addLast("3");
	}

	@Test
	void testAddFirst() {
		list.clear();
		list.addFirst("firstNode");
		assertEquals("firstNode", list.getFirst());

		list.clear();
		list.addLast("1");
		list.addLast("2");
		list.addLast("3");
		list.addFirst("0");
		assertEquals("0",list.getFirst());

		list.clear();
		assertNull(null,list.getFirst());
	}

	@Test
	void testAddLast() {
		list.clear();
		list.addLast("lastNode");
		assertEquals("lastNode",list.getLast());

		list.clear();
		list.addLast("1");
		list.addLast("2");
		list.addLast("3");
		list.addFirst("0");
		assertEquals("3",list.getLast());

		list.clear();
		assertNull(null,list.getLast());
	}

	@Test
	void testRemoveFirst() {
		list.clear();
		assertNull(null,list.removeFirst());

		list.clear();
		list.addFirst("0");
		assertEquals("0", list.removeFirst());
		assertNull(list.getFirst());

		list.clear();
		list.addLast("1");
		list.addLast("2");
		list.addLast("3");
		list.addFirst("0");
		assertEquals("0", list.removeFirst());
	}
	
	@Test
	void testRemoveLast() {
		assertEquals("3",list.removeLast());

		list.clear();
		assertNull(null,list.removeLast());

		list.clear();
		list.addLast("1");
		list.addLast("2");
		list.addLast("3");
		list.addFirst("0");
		assertEquals("3", list.removeLast());
	}

	@Test
	void testRemove(){
		list.clear();
		assertNull(null,list.remove(0));

		list.clear();
		list.addLast("3");
		assertEquals("3", list.remove(0));

		list.clear();
		list.addLast("1");
		list.addLast("2");
		list.addFirst("0");
		assertEquals("0",list.remove(0));
		assertEquals("1", list.remove(0));
		assertEquals("2",list.remove(0));
	}

	@Test
	void testGetFirst(){
		assertEquals("0",list.getFirst());

		list.clear();
		assertNull(list.getFirst());

	}

	@Test
	void testGetLast(){
		assertEquals("3",list.getLast());

		list.clear();
		assertNull(list.getLast());
	}

	@Test
	void testIsEmpty() {
		assertFalse(list.isEmpty());

		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(4,list.size());

		list.clear();
		assertEquals(0,list.size());
	}
	
	@Test
	void testClear() {
		list.clear();
		assertNull(null,list.getFirst());
		assertEquals(0, list.size());
		
	}

	@Test
	void testInsert(){
		list.insert("0",3);
		assertEquals("0",list.get(3));

		list.clear();
		list.insert("0",0);
		assertEquals("0",list.get(0));

	}

}
