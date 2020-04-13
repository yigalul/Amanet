package amanet.page.seven;

import amanet.enums.LanguagesEnum;
import amanet.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SevenHomPage extends BasePage {
    @FindBy(id = "ChooseLanguageDlgOpener")
    private WebElement chooseLanguage;
    @FindBy(id = "ChooseLanguageDlg")
    private WebElement languagesPicker;
    @FindBy(xpath = "//a[text() = 'English']")
    private WebElement clickOnEnglish;
    @FindBy(xpath = "//a[text() = 'Deutsch']")
    private WebElement clickOnDeutsch;
    @FindBy(xpath = "//a[text() = 'Suomi']")
    private WebElement clickOnSuomi;
    @FindBy(xpath = "//a[text() = 'LOGIN']")
    private WebElement verifyEnglish;
    @FindBy(xpath = "//a[text() = 'EINLOGGEN']")
    private WebElement verifyDeutsch;
    @FindBy(xpath = "//a[text() = 'KIRJAUTUMINEN']")
    private WebElement verifySuomi;

    public SevenHomPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.777.com/");
    }

    public void mouseOverLanguage(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mouseOver(chooseLanguage);
    }

    public List<String> getLanguages(){
        return this.getText(languagesPicker);
    }

    public void clickOnLanguage(LanguagesEnum languages){
        if (languages == LanguagesEnum.English)
            this.clickOnEnglish();
        else if (languages == LanguagesEnum.Suomi)
            this.clickOnSuomi();
        else if (languages == LanguagesEnum.Deutsch)
            this.clickOnDeutsch();
        else logger.error("Can't find element: " + languages.getLanguage());

    }

    public boolean verifyIfLanguagesExist(LanguagesEnum languages) {
        if (languages == LanguagesEnum.English)
            return this.verifyElement(verifyEnglish);
        else if (languages == LanguagesEnum.Suomi)
            return this.verifyElement(verifySuomi);
        else if (languages == LanguagesEnum.Deutsch)
            return this.verifyElement(verifyDeutsch);
        else return false;
    }

    public void clickOnEnglish(){
        this.click(clickOnEnglish);
    }

    public void clickOnDeutsch(){
        this.click(clickOnDeutsch);
    }

    public void clickOnSuomi(){
        this.click(clickOnSuomi);
    }

}
