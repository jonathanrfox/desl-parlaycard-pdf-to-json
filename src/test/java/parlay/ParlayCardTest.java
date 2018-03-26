package parlay;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class ParlayCardTest {

    private Class actualClass;
    private Class expectedClass;

    public ParlayCardTest(String parlayType, Class expectedClass) {
        this.actualClass = ParlayCard.create(parlayType).getClass();
        this.expectedClass = expectedClass;
    }

    @Parameterized.Parameters
    public static List<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"std", RegularCard.class},
                {"tsr", RegularCard.class},
                {"sup", RegularCard.class},
                {"rev", IrregularCard.class}
            });
    }

    @Test
    public void shouldReturnCorrectInstance() {
        assertEquals(this.actualClass, this.expectedClass);
    }
}
