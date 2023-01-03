package kata5;

import java.util.regex.Pattern;

public class Mail {
    private final String mail;
    private static final Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern userNamepattern = Pattern.compile("^[\\w-\\.]+@");

    public Mail(String mail) {
        this.mail = mail;
    }
    
    public String getDomain(){
        String[] domain = userNamepattern.split(this.mail);
        String result = "";
        for (String car : domain) 
            result = result + car;
        return result;
    }
    
    public static boolean isEmail(String line) {
        return pattern.matcher(line).matches();
    }
    
}
