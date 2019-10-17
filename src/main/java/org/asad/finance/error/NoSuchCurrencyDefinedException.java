package org.asad.finance.error;

public class NoSuchCurrencyDefinedException extends RuntimeException {

    public NoSuchCurrencyDefinedException() {
        super("NO Such Currency Defined");
    }
}
