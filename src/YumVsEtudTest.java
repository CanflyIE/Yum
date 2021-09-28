import org.junit.jupiter.api.*;

public class YumVsEtudTest {

    @Test
    public void test_isSuite() {
        int[] tab1 = {2, 1, 4, 3, 5};
        int[] tab2 = {2, 1, 4, 3, 6};
        int[] tab3 = {2, 2, 4, 3, 6};
        Assertions.assertTrue(YumVsEtud.isSuite(tab1).get("suite4"));
        Assertions.assertTrue(YumVsEtud.isSuite(tab1).get("suite5"));
        Assertions.assertTrue(YumVsEtud.isSuite(tab2).get("suite4"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab2).get("suite5"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab3).get("suite4"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab3).get("suite5"));
    }


}
