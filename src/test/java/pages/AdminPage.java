package pages;

import org.openqa.selenium.By;

public class AdminPage extends BasePage{

    private static final String FIELD_LABEL_XPATH = "//label[normalize-space()='%s']";
    private final By addBtnLoc = By.xpath("//button[normalize-space()='Add']");

    private By fieldLabel(String name) {
        return By.xpath(String.format(FIELD_LABEL_XPATH, name));
    }

    public void openAdminPage() {
        SideMenuPage sideMenuPage = new SideMenuPage();
        sideMenuPage.openMenu("Admin");
        waitForUrlContains("/admin/viewSystemUsers");
    }


    public void clickAdd() {
        onClick(addBtnLoc);
        waitForUrlContains("/admin/saveSystemUser");
    }

    public boolean isFieldDisplayed(String name) {
        return isDisplayed(fieldLabel(name));
    }



}
