package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RemoveContact extends TestBase{
    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
        }
    }
    @Test
    public void removeFirstContact(){
        //Assert size of ContactList less by one

    }
    @Test
    public void removeAllFirstContact(){
        //"No contact Here"

    }
}
