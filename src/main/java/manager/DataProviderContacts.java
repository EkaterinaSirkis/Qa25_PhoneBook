package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContacts {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Contact.builder().
                        name("Tony").
                        lastName("Molly").
                        phone("05031111111").
                        email("tony@gmail.com").
                        address("NY").
                        build()
        });
        list.add(new Object[]{
                Contact.builder().
                        name("Moly").
                        lastName("Tony").
                        phone("050311222221").
                        email("Molly@gmail.com").
                        address("NY").
                        build()
        });

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Contact.builder().
                        name("Moly").
                        lastName("Tony").
                        phone("123").
                        email("Molly@gmail.com").
                        address("NY").
                        build()
        });
        list.add(new Object[]{
                Contact.builder().
                        name("Moly").
                        lastName("Tony").
                        phone("123456789101113141516").
                        email("Molly@gmail.com").
                        address("NY").
                        build()
        });
        list.add(new Object[]{
                Contact.builder().
                        name("Moly").
                        lastName("Tony").
                        phone("wwwwwwwwwwww").
                        email("Molly@gmail.com").
                        address("NY").
                        build()
        });
        list.add(new Object[]{
                Contact.builder().
                        name("Moly").
                        lastName("Tony").
                        phone("").
                        email("Molly@gmail.com").
                        address("NY").
                        build()
        });

        return list.iterator();

    }
    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while(line!=null){
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
