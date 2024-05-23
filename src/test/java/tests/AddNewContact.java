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
        //int i = (int) (System.currentTimeMillis()/10)%3600;
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder().
                name("Name" + i).
                lastName("LastName").
                phone("050314"+i).
                email("email" + i+ "@gmail.com").
                address("address").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().clickSave();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        //Assert.assertTrue(app.getHelperContact().getMessage().contains("Name"));
    }
    @Test
    public void AddNewContactWrongName(){
        Contact contact = Contact.builder().
                name("").
                lastName("LastName").
                phone("05031491133").
                email("email@gmail.com").
                address("address").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().clickSave();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }
    @Test
    public void AddNewContactWrongLastName(){
        Contact contact = Contact.builder().
                name("Name").
                lastName("").
                phone("05031491133").
                email("email@gmail.com").
                address("address").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().clickSave();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }
    @Test
    public void AddNewContactWrongPhone(){
        Contact contact = Contact.builder().
                name("Name").
                lastName("LastName").
                phone("").
                email("email@gmail.com").
                address("address").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().clickSave();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }
    @Test
    public void AddNewContactWrongEmail(){
        Contact contact = Contact.builder().
                name("Name").
                lastName("LastName").
                phone("050").
                email("emailgmail.com").
                address("address").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().clickSave();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid: "));
    }
    @Test
    public void AddNewContactWrongAddress(){
        Contact contact = Contact.builder().
                name("Name").
                lastName("LastName").
                phone("050").
                email("emailgmail.com").
                address("").
                description("israel").
                build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().clickSave();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

}
