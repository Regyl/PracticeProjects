package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class AndFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
            return input.replaceAll("(?i)and", "\n      and");
    }
}
