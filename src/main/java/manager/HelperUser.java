package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginRegistrationForm(){
//        WebElement loginTab = wd.findElement(By.cssSelector("a[href='/login']"));
//        loginTab.click();
        click(By.cssSelector("a[href='/login']"));
    }
    public void  fillLoginRegistrationForm(String email, String password){
//        WebElement emailInput = wd.findElement(By.name("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.name("email"), email);
//        WebElement passwordInput = wd.findElement(By.name("password"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
        type(By.name("password"), password);

    }
    public void submitLogin(){
        click(By.name("login"));

    }
    public void submitReg(){
        click(By.name("registration"));

    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }
    public void fillLoginRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.name("password"), user.getPassword());
    }

    public boolean isNoContactHereDisplayed() {
        WebDriverWait wait = new WebDriverWait(wd, 5);
        return wait.until
                (ExpectedConditions.textToBePresentInElement
                        (wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),"No Contacts here!"));
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
