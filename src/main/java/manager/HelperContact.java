package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase{

    public HelperContact(WebDriver wd) {
        super(wd);
    }
    public void openAddContactForm(){
        pause(500);
        //By.cssSelector("a[href='/add']"
        //click(By.xpath("//a[@href='/add']"));
        click(By.xpath("//a[text()='ADD']"));
    }

    public void fillAddContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"),contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone()+"");
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());

    }
    public void clickSave(){
        click(By.xpath("//b[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for(WebElement element:list){
           if (element.getText().equals(name)){
               return true;
           }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for(WebElement element:list){
            if (element.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }
    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of ContactList before remove is ->>" + before);

        removeContact();

        int after = countOfContacts();
        logger.info("Number of ContactList before remove is ->>" + after);

        return  before-after;
    }

    private void removeContact() {
        click(By.className("contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(500);
    }

    private int countOfContacts() {
        return wd.findElements(By.className("contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts() {
        while (countOfContacts() != 0){
            removeContact();
        }
    }

    public void providerContacts() {
        if(countOfContacts()<3){
            for (int i = 0; i < 3; i++) {
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Harry")
                .lastName("Poter")
                .phone("555666111" + i)
                .email("harry1" + i + "@gmail.com")
                .address("Hogwards")
                .description("Friend")
                .build();
         openAddContactForm();
         fillAddContactForm(contact);
         clickSave();
         pause(500);
    }
}
