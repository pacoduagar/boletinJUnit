package boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StackTest {
	
	Pila stack;
	int n = 10;
	
	@BeforeEach
	void init() {
		stack = new Pila();
	}
	
	@AfterEach
	void finish() {
		stack = null;
	}
	
	@Test
	void testAdd() {
		int[] numbers = {0, 2, 10, 20, 23};
        for (int num : numbers) {
            stack.push(num);
        	}
	}
	
	@Test
	void testRemoveFromEmptyPile() {
		assertThat(stack.pop(), nullValue());
	}
	
	@Test
	void testRemoveFromNonEmptyList() {
		stack.push(n);
		assertThat(stack.pop(), is(n));
	}
	
	@Test
	void testEmptyList() {
		assertTrue(stack.isEmpty());
	}
	
	@Test
	void testNonEmptyList() {
		stack.push(n);
		assertFalse(stack.isEmpty());
	}
	
	@Test
	void testLastOneFromEmptyList() {
		assertThat(stack.top(), nullValue());
	}
	
	@Test
	void testLastOneFromNonEmptyList() {
		stack.push(n);
		assertThat(stack.top(), is(n));
	}

}
