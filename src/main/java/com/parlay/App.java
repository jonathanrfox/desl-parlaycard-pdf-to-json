package com.parlay;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

import com.google.gson.Gson;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import com.parlay.ParlayCard;
import com.parlay.ParlayCards;
import com.parlay.ParlayUtils;


public class App {

    public static String getPdfText(String pdfPath) throws IOException {
        File file = new File(pdfPath);
        PDDocument document = null;
        PDFTextStripper stripper = null;
        String text = null;

        try {
            document = PDDocument.load(file);
            stripper = new PDFTextStripper();
            stripper.setEndPage(1);
            text =  stripper.getText(document);
        } catch (IOException e) {
            throw new IOException("Could not load file and strip text.", e);
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

        // parse the arguments
        Namespace ns = null;

        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        // get the document text
        String text = null;

        try {
            text = getPdfText(ns.get("pdf"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // log the text from pdf
        // System.out.println(text);

        // transform text into a condensed list
        List<String> lines = ParlayUtils.clean(text);

        // factory method for creating a parlay
        ParlayCard parlay = ParlayCards.get(ns.get("pdftype"));

        // set the week
        parlay.setWeek(ns.get("week"));

        // consume list into new list of Game objects
        parlay.consume(lines);

        // // output the result as a json object to console
        Gson gson = new Gson();
        String json = gson.toJson(parlay.getGames());
        System.out.println(json);
    }
}
