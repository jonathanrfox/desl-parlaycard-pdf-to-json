package com.parlay;

import java.lang.ArrayIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


public class ParlayUtils {

    private static final Pattern START_PATTERN = Pattern.compile(".*DENOTES.*");
    private static final Pattern END_PATTERN = Pattern.compile(".*(\\d+\\.)|(Parlay\\sPayoffs).*");
    // private static final Pattern LINE_PATTERN = Pattern.compile("^\\s*\\d+\\s*[A-Z].*");
    private static final Pattern LINE_PATTERN = Pattern.compile("^\\s*PRO.*");

    public static int findIndex(List<String> list, int startIdx, Pattern pat) {
        int idx = IntStream.range(startIdx, list.size())
            .filter(i -> pat.matcher(list.get(i)).find())
            .findFirst()
            .orElseThrow(ArrayIndexOutOfBoundsException::new);

        return idx;
    }

    public static List<String> clean(String text) {
        List<String> lines = new ArrayList<>(Arrays.asList(text.split("\n")));

        // narrow search space
        int from = findIndex(lines, 0, START_PATTERN);
        int to = findIndex(lines, from, END_PATTERN);

        // keep only lines that do not match: "\\s*PRO.*"
        List<String> filtered = lines.subList(from + 1, to)
            .stream()
            .filter(s -> !LINE_PATTERN.matcher(s).find())
            .collect(Collectors.toList());

        return filtered;
    }
}
