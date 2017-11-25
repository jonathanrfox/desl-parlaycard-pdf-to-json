import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ParsingTest {
    private String testData = "";

    // public void shouldBuildTheCorrectList() {
    //     String test = " 1 49 ERS    nbc  - 3½ 8:30p 2 BRON COS   + 3½\n";
    //     int realTestHalfLen = (int) test.length() / 2;
    //     int idealTestHalfLen = test.indexOf(':');

    //     String lhs = " 1 49 ERS    nbc  - 3½ 8:", rhs = "30p 2 BRON COS   + 3½\n";

    //     // if (idealTestHalfLen != -1) {
    //     //     lhs = test.substring(0, idealTestHalfLen);
    //     //     rhs = test.substring(idealTestHalfLen);
    //     // } else {
    //     //     lhs = test.substring(0, realTestHalfLen);
    //     //     rhs = test.substring(realTestHalfLen);
    //     // }

    //     System.out.println(lhs);
    //     System.out.println(rhs);

    //     Pattern pat = Pattern.compile("(49)?[A-Z\\s]+");
    //     Pattern pat2 = Pattern.compile("[-+]\\s\\d*½");

    //     List<String> possibleTeamNames = new ArrayList<>();
    //     List<String> possibleSpreads = new ArrayList<>();

    //     Matcher mat = pat.matcher(test);

    //     while (mat.find()) {
    //         String group = mat.group().replaceAll("\\s", "");
    //         if (!group.isEmpty())
    //             possibleTeamNames.add(group);
    //     }

    //     Matcher mat2 = pat2.matcher(test);

    //     while (mat2.find()) {
    //         String group = mat2.group().replaceAll("\\s", "");
    //         if (!group.isEmpty())
    //             possibleSpreads.add(group);
    //     }

    //     System.out.println(possibleTeamNames);
    //     System.out.println(possibleSpreads);

    //     // Matcher mat = pat.matcher(lhs);
    //     // while (mat.find())
    //     //     System.out.println("\""+mat.group()+"\"");

    //     // Matcher mat2 = pat2.matcher(lhs);
    //     // if (mat2.find())
    //     //     System.out.println("\""+mat2.group()+"\"");

    //     // Matcher mat3 = pat.matcher(rhs);
    //     // while (mat3.find())
    //     //     System.out.println("\""+mat3.group()+"\"");

    //     // Matcher mat4 = pat2.matcher(rhs);
    //     // if (mat4.find())
    //     //     System.out.println("\""+mat4.group()+"\"");

    //     assertEquals(1, 1);
    // }

    // @Test
    // public void shouldReplaceJunkWithNothingAndSpacesWithEquals() {
    //     String original = " 61 RAMS  espn  - 2½ 10:20p 62 49ERS  + 2½\n";

    //     String testLine = original.trim();
    //     testLine = testLine.replaceAll("[a-z0-35-8\\-:+½]", "");
    //     testLine = testLine.replaceAll("\\s+", "=");
    //     System.out.println(testLine);

    //     String testLine2 = original.trim();
    //     testLine2 = testLine2.replaceAll("[A-Za-z/]", "");
    //     testLine2 = testLine2.replaceAll("\\s\\s+", "=");
    //     System.out.println(testLine2);

    //     assertEquals(1, 1);
    // }

    // @Test
    // public void shouldReplaceJunkWithNothingAndSpacesWithEquals2() {
    //     String original = " 63 LA/SF OVER  44½  64 LA/SF UNDER   44½\n";
    //     SplitString ss = new SplitString(original, '/', 2);
    //     System.out.println(ss.lhs);
    //     System.out.println(ss.rhs);
    //     String testLine = original.trim();
    //     testLine = testLine.replaceAll("[A-Z/]", "");
    //     testLine = testLine.replaceAll("\\s+", "=");
    //     System.out.println(testLine);

    //     assertEquals(1, 1);
    // }
}
