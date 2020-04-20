package amanet.tests.seven;

import amanet.enums.LanguagesEnum;
import amanet.page.seven.SevenHomPage;
import amanet.tests.UIBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SevenHomeTest extends UIBaseTest {
    @Test
    public void firstTest() {
        extentLogger = extent.createTest("First Test", "Check Languages");
        SevenHomPage sevenHomPage = new SevenHomPage(driver);
        verifyAllLanguages(sevenHomPage);
        sevenHomPage.mouseOverLanguage();
        List<String> languages = sevenHomPage.getLanguages();
        languages.forEach(t -> logger.info(t));
        Assert.assertEquals(languages.get(0), LanguagesEnum.English.getLanguage(), "Language must be equal");
        Assert.assertEquals(languages.get(1), LanguagesEnum.Deutsch.getLanguage(), "Language must be equal");
        Assert.assertEquals(languages.get(2), LanguagesEnum.Suomi.getLanguage(), "Language must be equal");
    }

    private void verifyAllLanguages(SevenHomPage sevenHomPage) {
        boolean isVerified = false;
        for (LanguagesEnum value : LanguagesEnum.values()) {
            for (int i = 0; i < 3; i++) {
                try {
                    sevenHomPage.mouseOverLanguage();
                    sevenHomPage.clickOnLanguage(value);
                    isVerified = sevenHomPage.verifyIfLanguagesExist(value);
                    if (isVerified) break;
                 } catch (Exception e) {
                    logger.error("Something went wrong while validating the languages\n" + e.getMessage());
                }
            }
            Assert.assertTrue(isVerified, "Website language had to change to " + value.getLanguage());
        }
    }
}
