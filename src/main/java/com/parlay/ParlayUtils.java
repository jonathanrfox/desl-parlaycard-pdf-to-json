package com.parlay;

import java.lang.ArrayIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.parlay.TeamFinder;


public class ParlayUtils {

    private static final Pattern START_PATTERN = Pattern.compile(".*DENOTES.*");
    private static final Pattern END_PATTERN = Pattern.compile(".*(\\d+\\.)|(Parlay\\sPayoffs).*");
    private static final Pattern LINE_PATTERN = Pattern.compile("^\\s*PRO.*");
    private static final Pattern TEAM_PATTERN = Pattern.compile("(49)?[A-Z\\s]+");
    private static final Pattern SPREAD_PATTERN = Pattern.compile("[-+]?\\s\\d*½|[-+]\\s\\d*½?");

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
        int to = findIndex(lines, from, END_PATTERN);

        // keep only lines that do not match: "\\s*PRO.*"
        return lines.subList(from + 1, to)
            .stream()
            .filter(s -> !LINE_PATTERN.matcher(s).find())
            .collect(Collectors.toList());
    }

    public static List<String> findAll(String s, Pattern p) {
        Matcher matcher = p.matcher(s);
        List<String> found = new ArrayList<>();

        while (matcher.find())
            found.add(matcher.group());

        return found;
    }

    public static String findOne(String s, Pattern p) {
        Matcher matcher = p.matcher(s);
        return matcher.find() ? matcher.group() : null;
    }

    public static String joinAndReplaceWhitespace(List<String> list) {
        return list.stream()
            .reduce("", String::concat)
            .replaceAll("\\s", "");
    }

    public static String parseTeam(String line) {
        List<String> matches = findAll(line, TEAM_PATTERN);
        String candidate = joinAndReplaceWhitespace(matches);
        return TeamFinder.find(candidate);
    }

    public static String parseSpread(String line) {
        String match = findOne(line, SPREAD_PATTERN);
        if (match != null)
            match = match.replaceAll("[\\s½]", "") + ".5";
        return match;
    }

    public static String parseOver(String line) {
        return parseSpread(line);
    }

    public static String parseUnder(String line) {
        return parseSpread(line);
    }

    // public static void parseTeamsAndSpreads(Game game, String line) {
    //     String[] split = line.split(TEAM_SPLIT_PATTERN, 2);
    //     String lhs = split[0], rhs = split[1];

    //     game.setLhTeam( findMatch(lhs, TEAM_PATTERN) );
    //     game.setLhSpread( findMatch(lhs, SPREAD_PATTERN) );

    //     game.setRhTeam( findMatch(rhs, TEAM_PATTERN) );
    //     game.setRhSpread( findMatch(rhs, SPREAD_PATTERN) );
    // }

    // public static void parseOverUnder(Game game, String line) {
    //     String[] split = line.split(OVER_UNDER_SPLIT_PATTERN, 2);
    //     String lhs = split[0], rhs = split[1];

    //     game.setOver( findMatch(lhs, SPREAD_PATTERN) );
    //     game.setUnder( findMatch(rhs, SPREAD_PATTERN) );
    // }
}
