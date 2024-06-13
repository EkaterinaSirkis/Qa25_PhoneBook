package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test(groups = {"smoke"})
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        User user = new User()
                .setEmail("email" + i + "@gmail.com")
                .setPassword("Password1!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//a[text()='ADD']")));
        Assert.assertTrue(app.getHelperUser().isNoContactHereDisplayed());
    }
    //(description = "Bug report #12345",enabled = false)
    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setEmail("emailgmail.com")
                .setPassword("Password1!");



        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

    }
    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .setEmail("email@gmail.com")
                .setPassword("Password");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    @Test
    public void registrationExistUser(){
        User user = new User()
                .setEmail("mara@gmail.com")
                .setPassword("Mmar123456$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

    }
}
