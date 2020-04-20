package amanet.tests.w3schools;

import amanet.page.w3schools.W3SchoolsPage;
import amanet.tests.UIBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class W3SchoolsTest extends UIBaseTest {

    @Test
    public void getFromTable() {
        extentLogger = extent.createTest("GetFromTable", "Get From Table");
        W3SchoolsPage w3SchoolsPage = new W3SchoolsPage(this.driver);
        WebElement customersTable = this.driver.findElement(By.id("customerss"));
        String value = w3SchoolsPage.getTableCellText(
                customersTable,
                1,
                "Ernst Handel",
                3);

        Assert.assertEquals(value, "Austria", "Something wrong with the return values");

    }

    @Test
    public void verifyFromTable() {
        extentLogger = extent.createTest("VerifyFromTable", "Verify From Table");
        W3SchoolsPage w3SchoolsPage = new W3SchoolsPage(this.driver);
        WebElement customersTable = this.driver.findElement(By.id("customers"));
        w3SchoolsPage.getTableCellText(
                customersTable,
                1,
                "Ernst Handel",
                3);


        boolean VerificationFromTable = w3SchoolsPage.verifyTableCellText(customersTable,
                1,
                "Ernst Handel",
                3,
                "Austria");

        Assert.assertTrue(VerificationFromTable, "Something wrong with the verification");

    }

    @Test
    public void GettingFromTableByXpath() {
        extentLogger = extent.createTest("GettingFromTableByXpath", "Getting From Table By Xpath");
        W3SchoolsPage w3SchoolsPage = new W3SchoolsPage(this.driver);
        WebElement customersTable = this.driver.findElement(By.id("customers"));

        String strToValidate = w3SchoolsPage.getTableCellTextByXpath(customersTable,
                1,
                "Centro comercial Moctezuma",
                3);

        Assert.assertEquals(strToValidate, "Centro comercial Moctezuma");

    }
}
