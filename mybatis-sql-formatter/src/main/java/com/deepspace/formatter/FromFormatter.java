package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class FromFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
            return input.replaceAll("(?i)from", "\n     from");
    }
}
