package com.parlay;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static Logger LOGGER = Logger.getLogger(App.class.getName());
    private static Namespace namespace;

    private static void initArgParser(String[] args) {
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

        try {
            namespace = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            LOGGER.log(Level.SEVERE, "Couldn't parse args", e);
            System.exit(1);
        }
    }

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
                LOGGER.log(Level.WARNING, "Could not close document", e);
            }
        }

        return text;
    }

    public static void main(String[] args) {
        initArgParser(args);

        LOGGER.info("Arguments supplied:\n"
            + "\tpdf: " + namespace.get("pdf") + "\n"
            + "\tpdfType: " + namespace.get("pdftype") + "\n"
            + "\tweek: " + namespace.get("week"));

        String text = null;

        try {
            text = getPdfText(namespace.get("pdf"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't get pdf text", e);
            System.exit(1);
        }

        LOGGER.info("Text returned from pdf:\n" + text);

        List<String> lines = ParlayUtils.clean(text);
        ParlayCard parlay = ParlayCards.get(namespace.get("pdftype"));
        parlay.setWeek(namespace.get("week"));
        parlay.consume(lines);

        // output the result as a json object to console
        Gson gson = new Gson();
        String json = gson.toJson(parlay.getGames());
        System.out.println(json);
    }
}
