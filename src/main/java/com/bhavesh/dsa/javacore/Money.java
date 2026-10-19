package com.bhavesh.dsa.javacore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * An immutable value type, written to demonstrate the rules interviewers
 * actually probe: the equals/hashCode contract, defensive immutability, and why
 * money is never a {@code double}.
 *
 * <p><b>Why not double.</b> Binary floating point cannot represent 0.1
 * exactly, so {@code 0.1 + 0.2} is 0.30000000000000004. Over a ledger those
 * errors accumulate and reconcile to the wrong total. {@link BigDecimal} stores
 * an unscaled integer and a scale, so decimal fractions are exact.
 *
 * <p><b>The equals/hashCode contract.</b> Equal objects must have equal hash
 * codes, or hash-based collections break - an entry stored under one instance
 * becomes unfindable with an equal one. Note that {@code BigDecimal.equals}
 * compares scale as well as value, so {@code 1.0} and {@code 1.00} are not
 * equal under it; normalising the scale in the constructor is what makes
 * equality behave the way a reader expects.
 *
 * <p><b>Immutability.</b> The fields are final and every operation returns a
 * new instance. That makes the type safe to share across threads and safe to
 * use as a map key, since its hash code cannot change after insertion.
 */
public final class Money implements Comparable<Money> {

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(String amount, String currencyCode) {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(currencyCode, "currencyCode");
        Currency currency = Currency.getInstance(currencyCode);
        // Normalise the scale so that 1.0 and 1.00 compare equal.
        BigDecimal normalised = new BigDecimal(amount)
                .setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
        return new Money(normalised, currency);
    }

    public Money plus(Money other) {
        requireSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money minus(Money other) {
        requireSameCurrency(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    public Money times(int factor) {
        return new Money(amount.multiply(BigDecimal.valueOf(factor)), currency);
    }

    public BigDecimal amount() {
        return amount;
    }

    public Currency currency() {
        return currency;
    }

    private void requireSameCurrency(Money other) {
        Objects.requireNonNull(other, "other");
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    "cannot combine " + currency.getCurrencyCode() + " with " + other.currency.getCurrencyCode());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // getClass rather than instanceof: a subclass could not satisfy
        // symmetry here. The class is final, so this is belt and braces.
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money other = (Money) o;
        return amount.equals(other.amount) && currency.equals(other.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public int compareTo(Money other) {
        requireSameCurrency(other);
        // compareTo uses BigDecimal.compareTo, which ignores scale - consistent
        // with equals here only because the constructor normalises the scale.
        return amount.compareTo(other.amount);
    }

    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " + amount.toPlainString();
    }
}
