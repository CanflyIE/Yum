
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class YumVsEtudTest {

    @Test
    public void test_isSuite() {
        int[] tab1 = {2, 1, 4, 3, 5};
        int[] tab2 = {2, 1, 4, 3, 6};
        int[] tab3 = {2, 2, 4, 3, 6};
        int[] tab4 = {1, 2, 4, 5, 6};

        Assertions.assertTrue(YumVsEtud.isSuite(tab1).get("suite4"));
        Assertions.assertTrue(YumVsEtud.isSuite(tab1).get("suite5"));
        Assertions.assertTrue(YumVsEtud.isSuite(tab2).get("suite4"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab4).get("suite4"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab2).get("suite5"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab3).get("suite4"));
        Assertions.assertFalse(YumVsEtud.isSuite(tab3).get("suite5"));
        Assertions.assertArrayEquals(tab1, new int[]{2, 1, 4, 3, 5});


    }

    @Test
    public void test_isMainPleine() {
        int[] tab1 = {3, 2, 3, 2, 3};
        int[] tab2 = {3, 2, 3, 3, 3};
        Assertions.assertTrue(YumVsEtud.isMainPleine(tab1));
        Assertions.assertFalse(YumVsEtud.isMainPleine(tab2));
        Assertions.assertArrayEquals(tab1, new int[]{3, 2, 3, 2, 3});
    }

    @Test
    public void test_tableADoublons() {
        int[] tab1 = {1, 2, 4, 3, 4};
        int[] tab2 = {1, 2, 4, 3, 5};
        Assertions.assertTrue(YumVsEtud.tableADoublons(tab1));
        Assertions.assertFalse(YumVsEtud.tableADoublons(tab2));
        Assertions.assertArrayEquals(tab1, new int[]{1, 2, 4, 3, 4});
    }

    @Test
    public void test_initTableDes() {
        int[] tab = new int[5];
        YumVsEtud.initTableDes(tab);
        Assertions.assertTrue(tab[0] != 0);
        Assertions.assertTrue(tab[1] != 0);
        Assertions.assertTrue(tab[2] != 0);
        Assertions.assertTrue(tab[3] != 0);
        Assertions.assertTrue(tab[4] != 0);
    }

    @Test
    public void test_PossedeValInvalide() {
        int[] tab1 = {1, 5, 3, 4, 6};
        int[] tab2 = {1, 2, 3, 4, 5};
        Assertions.assertTrue(YumVsEtud.tablePossedeValInvalide(tab1));
        Assertions.assertFalse(YumVsEtud.tablePossedeValInvalide(tab2));
        Assertions.assertArrayEquals(tab1, new int[]{1, 5, 3, 4, 6});
    }

    @Test
    public void test_relancerDesChoisis() {
        int[] tab = {1, 3, 5, 4, 6};
        Assertions.assertFalse(Arrays.equals(YumVsEtud.relancerDesChoisis(tab, new int[]{1, 5}), new int[]{1, 3, 5, 4, 6}));
    }

    @Test
    public void test_intToArray() {
        int integer = 12345;
        Assertions.assertArrayEquals(YumVsEtud.intToArray(integer), new int[]{5, 4, 3, 2, 1});
        Assertions.assertFalse(Arrays.equals(YumVsEtud.intToArray(integer), new int[]{5, 4, 3, 2, 2}));
    }

    @Test
    public void test_verifieLeChoix(){
        int[] tab1 = {0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0};

    }

    @Test
    public void test_TraitementDePossibilite(){
        int[] tabDes = {1, 4, 5, 4, 6};
        int[] feuilleDepointage = {0, -1, -1, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, 20, -1, 0, 0};
        int[] tableauPossibilite = {0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        Assertions.assertArrayEquals(tableauPossibilite, YumVsEtud.TraitementDePossibilite(tabDes,feuilleDepointage));

    }

}
