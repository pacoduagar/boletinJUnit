package boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperadorYSubscripcionTest {

    OperadorAritmetico operator = new OperadorAritmetico();
    @Test
    public void testSuma() {
        assertEquals(5, OperadorAritmetico.suma(2, 3));
        assertEquals(-1, OperadorAritmetico.suma(2, -3));
    }

    @Test
    public void testDivision() throws Exception {
        assertEquals(5, OperadorAritmetico.division(10, 2));
        assertEquals(-5, OperadorAritmetico.division(10, -2));
        assertThrows(Exception.class, () -> OperadorAritmetico.division(5, 0));
    }

    @Test
    public void testDivisionByOne() throws Exception {
        assertEquals(10, OperadorAritmetico.division(10, 1));
        assertEquals(-10, OperadorAritmetico.division(10, -1));
    }

    Subscripcion sub;

    @BeforeEach
    void init() {
        sub = new Subscripcion(12, 12);
    }

    @AfterEach
    void finish() {
        sub = null;
    }

    @Test
    void testPositiveIntegerPrice() {
        assertEquals(sub.precioPorMes(), 1);
    }

    @Test
    void testNegativeIntegerPrice() {
        sub = new Subscripcion(-12, 12);
        assertEquals(sub.precioPorMes(), 0);
    }

    @Test
    void testNonIntegerPrice() {
        sub = new Subscripcion(1, 12);
        assertTrue(sub.precioPorMes() > 0);
    }

    @Test
    void testCancelSubscription() {
        sub.cancel();
        assertEquals(sub.precioPorMes(), 0);
    }

}

