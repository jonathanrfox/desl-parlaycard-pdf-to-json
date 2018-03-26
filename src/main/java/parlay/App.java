package parlay;

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

import extensions.PDFVisibleTextStripper;


public class App {

    private static Logger LOGGER = Logger.getLogger(App.class.getName());

    private static Namespace parseArgs(String[] args) {
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

        Namespace namespace = null;
        try {
            namespace = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            LOGGER.log(Level.SEVERE, "Could not parse arguments.", e);
            System.exit(1);
        }
        return namespace;
    }

    private static String getPdfText(String pdfPath) {
        File file = new File(pdfPath);
        String pdfText = null;

        try (PDDocument document = PDDocument.load(file)) {
            PDFVisibleTextStripper stripper = new PDFVisibleTextStripper();
            stripper.setEndPage(1);
            pdfText = stripper.getText(document);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not load PDF.", e);
            System.exit(1);
        }
        return pdfText;
    }

    public static void main(String[] args) {
        Namespace namespace = parseArgs(args);
        LOGGER.info("Arguments supplied:\n"
            + "\tpdf: " + namespace.get("pdf") + "\n"
            + "\tpdfType: " + namespace.get("pdftype") + "\n"
            + "\tweek: " + namespace.get("week"));

        String pdfText = getPdfText(namespace.get("pdf"));
        LOGGER.info("Text returned from pdf:\n" + pdfText);

        List<String> lines = ParlayUtils.clean(pdfText);
        ParlayCard parlay = ParlayCard.create(namespace.get("pdftype"));
        parlay.setWeek(namespace.get("week"));
        parlay.consume(lines);

        Gson gson = new Gson();
        String json = gson.toJson(parlay.getGames());
        System.out.print(json);
    }
}
