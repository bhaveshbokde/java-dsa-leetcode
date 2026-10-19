package com.bhavesh.dsa.javacore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("arithmetic is exact, unlike double")
    void arithmeticIsExact() {
        Money sum = Money.of("0.10", "USD").plus(Money.of("0.20", "USD"));
        assertEquals(Money.of("0.30", "USD"), sum);
        // The same computation in double is famously not 0.3.
        assertTrue(0.1 + 0.2 != 0.3, "this is why money is not a double");
    }

    @Test
    @DisplayName("scale is normalised so 1.0 and 1.00 are equal")
    void scaleNormalised() {
        assertEquals(Money.of("1.0", "USD"), Money.of("1.00", "USD"));
        assertEquals(Money.of("1", "USD"), Money.of("1.00", "USD"));
    }

    @Test
    @DisplayName("equal instances share a hash code, so hash collections work")
    void equalsHashCodeContract() {
        Money a = Money.of("5.00", "USD");
        Money b = Money.of("5.0", "USD");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode(), "equal objects must share a hash code");

        Set<Money> set = new HashSet<>();
        set.add(a);
        assertTrue(set.contains(b), "an equal instance must be findable in a HashSet");

        Map<Money, String> map = new HashMap<>();
        map.put(a, "stored");
        assertEquals("stored", map.get(b));
    }

    @Test
    @DisplayName("equals is reflexive, symmetric and handles null and other types")
    void equalsContractEdges() {
        Money a = Money.of("2.50", "USD");
        Money b = Money.of("2.50", "USD");
        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(b, a);
        assertNotEquals(null, a);
        assertNotEquals("2.50 USD", a);
    }

    @Test
    @DisplayName("different currencies are never equal and cannot be combined")
    void currencyIsPartOfIdentity() {
        assertNotEquals(Money.of("5.00", "USD"), Money.of("5.00", "EUR"));
        assertThrows(IllegalArgumentException.class,
                () -> Money.of("5.00", "USD").plus(Money.of("5.00", "EUR")));
    }

    @Test
    @DisplayName("operations return new instances, leaving the originals untouched")
    void immutability() {
        Money original = Money.of("10.00", "USD");
        Money result = original.plus(Money.of("5.00", "USD"));
        assertEquals(Money.of("10.00", "USD"), original, "the original must be unchanged");
        assertEquals(Money.of("15.00", "USD"), result);
    }

    @Test
    @DisplayName("subtraction and multiplication")
    void subtractAndMultiply() {
        assertEquals(Money.of("7.50", "USD"), Money.of("10.00", "USD").minus(Money.of("2.50", "USD")));
        assertEquals(Money.of("30.00", "USD"), Money.of("10.00", "USD").times(3));
        assertEquals(Money.of("-5.00", "USD"), Money.of("0.00", "USD").minus(Money.of("5.00", "USD")));
    }

    @Test
    @DisplayName("compareTo is consistent with equals and sorts correctly")
    void comparableContract() {
        Money small = Money.of("1.00", "USD");
        Money large = Money.of("2.00", "USD");
        assertTrue(small.compareTo(large) < 0);
        assertTrue(large.compareTo(small) > 0);
        assertEquals(0, small.compareTo(Money.of("1.00", "USD")));

        List<Money> values = new ArrayList<>(List.of(
                Money.of("3.00", "USD"), Money.of("1.00", "USD"), Money.of("2.00", "USD")));
        values.sort(null);
        assertEquals(List.of(Money.of("1.00", "USD"), Money.of("2.00", "USD"), Money.of("3.00", "USD")),
                values);
    }

    @Test
    @DisplayName("rounding uses HALF_UP at the currency's fraction digits")
    void rounding() {
        assertEquals(Money.of("2.35", "USD"), Money.of("2.345", "USD"));
        // JPY has zero fraction digits.
        assertEquals("JPY 100", Money.of("99.5", "JPY").toString());
    }

    @Test
    @DisplayName("invalid inputs are rejected")
    void invalidInputs() {
        assertThrows(NullPointerException.class, () -> Money.of(null, "USD"));
        assertThrows(NullPointerException.class, () -> Money.of("1.00", null));
        assertThrows(IllegalArgumentException.class, () -> Money.of("1.00", "NOTACURRENCY"));
        assertThrows(NumberFormatException.class, () -> Money.of("not a number", "USD"));
    }
}
