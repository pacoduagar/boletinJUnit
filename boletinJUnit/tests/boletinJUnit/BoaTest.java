package boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoaTest {
	
	// Sesión de cobertura del 100% para OperadorAritmetico
	Boa boa;
	
	@BeforeEach
	void init() {
        boa = new Boa("enroskada", 13, "granola bars");
	}
	
	@AfterEach
	void finish() {
		boa = null;
	}
	
    void init(String nombre, int longitud, String comidaFavorita) {
        boa = new Boa(nombre, longitud, comidaFavorita);
    }
	
    @ParameterizedTest(name = "Salud de {0}")
    @MethodSource("boas")
    void testHealthyBoa(String nombre, int longitud, String comidaFavorita, boolean boaSana) {
    	init(nombre, longitud, comidaFavorita);
    	assertEquals(boa.isHealthy(), boaSana);
    }

    @Test
    void testFitBoaInCage() {
    	assertTrue(boa.fitsInCage(20));
    }
    
    @Test
    void testNonFitBoaInCage() {
    	assertFalse(boa.fitsInCage(10));
    }

    static Stream<Arguments> boas() {
        return Stream.of(
    		Arguments.of("linea", 4, "canuto", false),
    		Arguments.of("honor", 9, "gloria", true)
    	);
    }

}
