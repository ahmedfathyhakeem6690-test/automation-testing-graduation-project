package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;
import pages.SideMenuPage;

import java.util.List;

public class UiTest extends BaseTest {

    @Test
    @Epic("UI")
    @Feature("Footer")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the footer branding text and that its link opens orangehrm.com in a new tab")
    public void footerBrandingLink() {
        FooterPage footer = new FooterPage();

        Allure.step("Scroll to the footer", footer::scrollToFooter);

        Allure.step("Assert the footer text contains OrangeHRM, Inc", () ->
                Assert.assertTrue(footer.getFooterText().contains("OrangeHRM, Inc"),
                        "Footer text does not contain OrangeHRM, Inc"));


        Allure.step("Click the OrangeHRM, Inc link", footer::clickBrandLinkAndSwitchToNewTab);

        Allure.step("Assert the new tab URL contains orangehrm.com", () ->
                Assert.assertTrue(footer.waitForUrlContains("orangehrm.com"),
                        "New tab URL does not contain orangehrm.com"));

    }

    @Test
    @Epic("UI")
    @Feature("Sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the sidebar contains the main menu items")
    public void sidebarMenuItems() {
        SideMenuPage sideMenu = new SideMenuPage();
        List<String> expected = List.of("Admin", "PIM", "Leave", "Time", "Recruitment",
                "My Info", "Performance", "Dashboard", "Directory");

        Allure.step("Login as admin", this::loginAsAdmin);

        Allure.step("Assert the sidebar contains all expected menu items", () -> {
            List<String> actual = sideMenu.getMenuNames();
            List<String> missing = expected.stream().filter(n -> !actual.contains(n)).toList();
            Assert.assertTrue(missing.isEmpty(), "Missing sidebar items: " + missing);
        });
    }


}
