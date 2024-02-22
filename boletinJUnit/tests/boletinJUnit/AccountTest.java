package boletinJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTest {
	
	Account account;
	float initialBalance, fee;
	
	@BeforeEach
	void init() {
		account = new Account("Paul", 123456789020L, 789F);
		initialBalance = account.getBalance();
		fee = 2F;
	}
	
	@AfterEach
	void finish() {
		account = null;
		initialBalance = fee = 0;
	}
	
	@Test
	void testCreateAccount() {
		assertThat(account, notNullValue());
	}
	
	@ParameterizedTest(name = "Ingresar +{0}€")
	@MethodSource("amounts")
	void testPositiveBalance(float amount) {
		assertTrue(account.deposit(amount));
		assertEquals(account.getBalance(), initialBalance + amount);
	}
	
	@ParameterizedTest(name = "Ingresar -{0}€")
	@MethodSource("amounts")
	void testNegativeBalance(float amount) {
		assertFalse(account.deposit(-amount));
		assertEquals(account.getBalance(), initialBalance);
	}
	
	@ParameterizedTest(name = "Retirar +{0}€ con tarifa")
	@MethodSource("amounts")
	void testRemovePositive(float amount) {
		assertTrue(account.withdraw(amount, fee));
	}
	
	@ParameterizedTest(name = "Retirar -{0}€ con tarifa")
	@MethodSource("amounts")
	void testRemoveNegtive(float amount) {
		assertFalse(account.withdraw(-amount, fee));
	}
	
	@Test
	void testAddInterests() {
		account.addInterest();
		assertNotEquals(account.getBalance(), initialBalance);
	}
	
	@Test
	void testAccountNumberLong() {
		assertThat(account.getAccountNumber(), isA(long.class));
	}
	
	@Test
    void testToString() {
		String answer = account.toString();
		assertTrue(answer.contains(Long.toString(account.getAccountNumber())));
	    assertTrue(answer.contains(String.format("%.2f", initialBalance)));
    }
	
	static Stream<Float> amounts() {
        return Stream.of(10F, 200F, 500F);
    }

}
