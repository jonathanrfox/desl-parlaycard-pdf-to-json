package com.parlay;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.parlay.ParlayCards;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;


public class App {

    public static String getPdfText(String pdfPath) {
        File file = new File(pdfPath);
        PDDocument document = null;
        PDFTextStripper stripper = null;
        String text = null;

        try {
            document = PDDocument.load(file);
            stripper = new PDFTextStripper();
            text = stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null)
                    document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return text;
    }

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers
            .newArgumentParser("ParlayParser")
            .defaultHelp(true)
            .description("Parses parlay pdfs into json.");

        parser.addArgument("pdf")
            .help("Pdf to parse");

        parser.addArgument("pdftype")
            .help("Type of parlay")
            .choices("std", "tsr", "sup", "rev");

        parser.addArgument("week")
            .help("Week of play")
            .type(Integer.class)
            .choices(Arguments.range(1, 18));

        Namespace ns = null;

        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        // get the document text
        String text = getPdfText(ns.get("pdf"));

        // System.out.println(ns.get("pdf"));
        // System.out.println(text);
        // System.exit(1);

        // transform text into a condensed list
        List<String> lines = ParlayUtils.clean(text);

        // factory method for creating a parlay
        ParlayCard parlay = ParlayCards.get(ns.get("pdftype"));

        // set the week
        parlay.setWeek(ns.get("week"));

        // // fill arraylist with games
        parlay.consume(lines);

        // // output the result as a json object to console
        Gson gson = new Gson();
        String json = gson.toJson(parlay.getGames());
        System.out.println(json);
    }
}
