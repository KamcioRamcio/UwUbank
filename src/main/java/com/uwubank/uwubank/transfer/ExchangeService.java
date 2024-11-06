package com.uwubank.uwubank.transfer;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeService {

    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();

    static {
        EXCHANGE_RATES.put("USD-EURO", 0.85);
        EXCHANGE_RATES.put("EURO-USD", 1.18);
        EXCHANGE_RATES.put("USD-PLN", 3.75);
        EXCHANGE_RATES.put("PLN-USD", 0.27);
        EXCHANGE_RATES.put("EURO-PLN", 4.40);
        EXCHANGE_RATES.put("PLN-EURO", 0.23);
    }


    public double getExchangeRate(String currencyFrom, String currencyTo) {
        if (currencyFrom.equals(currencyTo)) {
            return 1.0;
        }

        String key = currencyFrom + "-" + currencyTo;
        Double rate = EXCHANGE_RATES.get(key);

        if (rate != null) {
            return rate;
        } else {
            throw new IllegalArgumentException("Exchange rate not available for " + currencyFrom + " to " + currencyTo);
        }
    }
}
