package processing;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowersAndPublicReposCalculationsResponseTransformerTest {
    private static FollowersAndPublicReposCalculationsResponseTransformer transformer;

    @BeforeClass
    public static void beforeClass() throws Exception {
        transformer = new FollowersAndPublicReposCalculationsResponseTransformer();
    }

    @Test
    public void checkCalculations() {
        assertThat(transformer.calculate(0, 213)).isEqualTo("Not a number");
        assertThat(transformer.calculate(2, 0)).isEqualTo("6");
        assertThat(transformer.calculate(3, 4)).isEqualTo("12");
    }
}