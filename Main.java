package http;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author busraicoz
 */
public class Main {
    
public static void main(String[] args) throws IOException {
    
    Http http = new Http();
    String[] toMail = {"*****","******"};
    String subject = "Url HTTP Status Codes";
    http.controlfromfile("input.txt",http.getContent());
    http.sendMail(toMail,subject,http.getContent());
	    
}}
