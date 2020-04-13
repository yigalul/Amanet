package amanet.page.w3schools;

import amanet.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class W3SchoolsPage extends BasePage {
    @FindBy(xpath = "/table/tbody")
    private WebElement table;

    public W3SchoolsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.w3schools.com/html/html_tables.asp");
    }


    public String getTableCellText(WebElement table, int searchColumn,
                                   String searchText, int returnColumnText) {
            for (int i = 2; i < 8; i++) {
                String text = table.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + searchColumn + "]")).getText();
                if (text.equals(searchText)) {
                    return table.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + returnColumnText + "]")).getText();
                }
            }
        return null;
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText,
                                       String expectedText) {
        String tableCellText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        if (expectedText.equals(tableCellText)) return true;

        return false;
    }

    public String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        boolean isValidNumber = (returnColumnText > 2 && returnColumnText < 7) == true ? true : false;
        isValidNumber = isValidNumber && (searchColumn > 0 && searchColumn < 4) == true ? true : false;
        String text= null;
        if (isValidNumber)
            text = table.findElement(By.xpath("//tbody/tr[" + returnColumnText + "]/td[" + searchColumn + "]")).getText();
        else{
            logger.error("The cell that you'r trying to get is not valid");
        }
        return (searchText.equals(text) == true) ? text : null;
    }
}
