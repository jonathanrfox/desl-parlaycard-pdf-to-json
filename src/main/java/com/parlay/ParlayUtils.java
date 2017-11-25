package com.parlay;

import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.Double;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ParlayUtils {

    private static final Pattern START_PATTERN  = Pattern.compile(".*DENOTES.*");
    private static final Pattern LINE_PATTERN   = Pattern.compile("^\\s*\\d+");
    private static final Pattern TEAM_PATTERN   = Pattern.compile("(49)?[A-Z]+");
    private static final Pattern SPREAD_PATTERN = Pattern.compile("[-+]?\\s\\d*½");
    private static final Pattern OVER_PATTERN   = Pattern.compile("OVER\\s*\\d*½");
    private static final Pattern UNDER_PATTERN  = Pattern.compile("UNDER\\s*\\d*½");


    public static int findIndex(List<String> list, int startIdx, Pattern pat) {
        return IntStream.range(startIdx, list.size())
            .filter(i -> pat.matcher(list.get(i)).find())
            .findFirst()
            .orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    public static List<String> clean(String text) {
        List<String> lines = new ArrayList<>(Arrays.asList(text.split("\n")));

        // narrow search space
        int from = findIndex(lines, 0, START_PATTERN);

        // only keep lines that match ` 2 ...`
        return lines.subList(from + 1, lines.size())
            .stream()
            .filter(s -> LINE_PATTERN.matcher(s).find())
            .collect(Collectors.toList());
    }

    public static Map<String, String> splitLine(String line, char on) {
        line = line.trim();
        int maybeHp = line.indexOf(on);
        int hp = maybeHp >= 0 ? maybeHp : (int) line.length() / 2;

        Map<String, String> map = new HashMap<>();
        String lhs = line.substring(0, hp).trim();
        String rhs = line.substring(hp + 1).trim();

        if (lhs.contains("")) {
            map.put("home", lhs);
            map.put("away", rhs);
        } else {
            map.put("home", rhs);
            map.put("away", lhs);
        }

        return map;
    }

    public static String findOne(String s, Pattern p) {
        Matcher matcher = p.matcher(s);
        return matcher.find() ? matcher.group() : null;
    }

    public static String parseTeam(String line) {
        return findOne(line, TEAM_PATTERN);
    }

    public static Double cleanSpread(String s) {
        if (s != null)
            return new Double(s.replaceAll("[^-+\\d]", "") + ".5");
        return null;
    }

    public static Double parseSpread(String line) {
        String match = findOne(line, SPREAD_PATTERN);
        return cleanSpread(match);
    }

    public static Double parseOver(String line) {
        String match = findOne(line, OVER_PATTERN);
        return cleanSpread(match);
    }

    public static Double parseUnder(String line) {
        String match = findOne(line, UNDER_PATTERN);
        return cleanSpread(match);
    }
}
