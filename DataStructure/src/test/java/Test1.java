/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.mycompany.datastructure.DequeueImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Test1 {
    
    
      @Test
        public void testFirstQueue() {
            DequeueImpl<Integer> q = new DequeueImpl<>();
            assertTrue(q.isEmpty());
            int count = 100000;
            for (int i = 0; i < count; i++) {
                q.pushFirst(i);
                assertTrue(q.size() == i + 1);        
            }
            assertTrue(q.first() == count -1);
            assertTrue(q.size() == count);
           int current = count - 1;
            while (!q.isEmpty()) {
                assertTrue(q.popFirst()== current);
                current--;
            }
            assertTrue(q.isEmpty());
        }
        
        @Test
        public void testLastQueue() {
            DequeueImpl<Integer> q = new DequeueImpl<>();
            assertTrue(q.isEmpty());
            int count = 100000;
            for (int i = 0; i < count; i++) {
                q.pushLast(i);
                assertTrue(q.size() == i + 1);        
            }
            assertTrue(q.last()== count -1);
            assertTrue(q.size() == count);
           int current = count - 1;
            while (!q.isEmpty()) {
                assertTrue(q.popLast()== current);
                current--;
            }
            assertTrue(q.isEmpty());
        }
    
    
   @Test
    public void testPushFirstAndPopFirst() {
        DequeueImpl<Integer> deque = new DequeueImpl<>();
        deque.pushFirst(1);
        deque.pushFirst(2);
        deque.pushFirst(3);

        assertEquals(3, (int) deque.popFirst());
        assertEquals(2, (int) deque.popFirst());
        assertEquals(1, (int) deque.popFirst());
        assertTrue(deque.isEmpty());
    }
    
    @Test
    public void testPushLastAndPopLast() {
        DequeueImpl<String> deque = new DequeueImpl<>();
        deque.pushLast("A");
        deque.pushLast("B");
        deque.pushLast("C");

        assertEquals("C", deque.popLast());
        assertEquals("B", deque.popLast());
        assertEquals("A", deque.popLast());
        assertTrue(deque.isEmpty());
    }
    
    
    @Test
    public void testClear() {
        DequeueImpl<Integer> deque = new DequeueImpl<>();
        deque.pushFirst(1);
        deque.pushLast(2);
        deque.pushFirst(3);

        assertFalse(deque.isEmpty());
        deque.clear();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }
    
     @Test
    public void testSizeAndIsEmpty() {
        DequeueImpl<Double> deque = new DequeueImpl<>();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());

        deque.pushFirst(1.1);
        deque.pushLast(2.2);
        deque.pushFirst(3.3);

        assertFalse(deque.isEmpty());
        assertEquals(3, deque.size());

        deque.popFirst();
        deque.popLast();

        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
    }
    
     @Test
     public void testFirstAndLast() {
        DequeueImpl<Character> deque = new DequeueImpl<>();
        deque.pushFirst('A');
        deque.pushLast('B');
        deque.pushLast('C');

        assertEquals('A', (char) deque.first());
        assertEquals('C', (char) deque.last());
    }
     
     
   

}
