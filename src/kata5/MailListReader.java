package kata5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static kata5.Mail.isEmail;

public class MailListReader {
    private final List<String> domainsList = new ArrayList();
    
    public List<String> addDomains(List<Mail> items){
        for (Mail line : items) {
            domainsList.add(line.getDomain());
        }
        return domainsList;
    }
    
    public List<String> read(String fileName){

        List<String> mailList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));

            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                if (isEmail(line)) mailList.add(line);
            }  
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return mailList;
    }
}
