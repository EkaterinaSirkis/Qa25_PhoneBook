package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContact extends TestBase{
    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
        }
    }
    @Test
    public void AddNewContactAllSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder().
                name("Name").
                lastName("LastName").
                phone("0503149113").
                email("email@gmail.com").
                address("address").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().ClickSave();
        Assert.assertTrue(app.getHelperContact().getMessage().contains("Name"));

    }

}
