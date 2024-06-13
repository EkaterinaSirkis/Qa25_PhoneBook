package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContact extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
        }
        app.getHelperContact().providerContacts();
    }
    @Test(groups = {"smoke"})
    public void removeFirstContact(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);

    }
    @Test
    public void removeAllFirstContact(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperUser().isNoContactHereDisplayed());


    }
}
