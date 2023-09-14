package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class InnerJoinFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
            return input.replaceAll("(?i)inner", "\n    inner");
    }
}
