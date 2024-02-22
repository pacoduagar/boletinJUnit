package boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FridgeTest {
	
	Fridge fridge;
	String food = "sandwich";
	
	@BeforeEach
	void init() {
		fridge = new Fridge();
		fridge.put(food);
	}
	
	@AfterEach
	void finish() {
		fridge = null;
	}
	
	@ParameterizedTest(name = "Meter {0}")
	@ValueSource(strings = {"bread", "cheese", "ham"})
	void testAddMultipleFoods(String alimento) {
		assertTrue(fridge.put(alimento));
	}
	
	@Test
	void testAddSameFood() {
		assertFalse(fridge.put(food));
	}
	
	@Test
	void testSearchSameFood() {
		assertTrue(fridge.contains(food));
	}
	
	@Test
	void testSearchMissingFood() throws NoSuchItemException {
		fridge.take(food);
		assertFalse(fridge.contains(food));
	}
	
	@Test
    public void testRemoveMissingFood() throws NoSuchItemException {
		fridge.take(food);
        assertThrows(NoSuchItemException.class, () -> fridge.take(food));
    }

}
