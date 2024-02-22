package boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PilaTest {
	
	Pila pila;
	int n = 10;
	
	@BeforeEach
	void init() {
		pila = new Pila();
	}
	
	@AfterEach
	void finish() {
		pila = null;
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 2, 10, 20, 23})
	void testAdd(int num) {
		pila.push(num);
	}
	
	@Test
	void testRemoveFromEmptyPile() {
		assertThat(pila.pop(), nullValue());
	}
	
	@Test
	void testRemoveFromNonEmptyList() {
		pila.push(n);
		assertThat(pila.pop(), is(n));
	}
	
	@Test
	void testEmptyList() {
		assertTrue(pila.isEmpty());
	}
	
	@Test
	void testNonEmptyList() {
		pila.push(n);
		assertFalse(pila.isEmpty());
	}
	
	@Test
	void testLastOneFromEmptyList() {
		assertThat(pila.top(), nullValue());
	}
	
	@Test
	void testLastOneFromNonEmptyList() {
		pila.push(n);
		assertThat(pila.top(), is(n));
	}

}