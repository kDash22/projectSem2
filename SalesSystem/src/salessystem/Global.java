package salessystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Used for storing global methods and variables
public class Global {

    public static final String companyName = "Company name";
    public static final String rootUser = "rootuser2295";
    public static final String rootPassword = "RootUser2295!@#";

    //formats a given LocalDateTime variable into the given format
    public static String dateTimeFormat(LocalDateTime date ){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    //used to print a list
    public static <T> void printList(List<T> things){
        if(things.isEmpty()){
            System.out.println("The list is empty.");

        }else {
            for (T thing : things) {
                System.out.println(thing);

            }
        }
    }

    //used to convert a list into a printable String
    public static <T> String getListString(List<T> things){
        if(things.isEmpty()){
            return "The list is empty.";
        }

        StringBuilder sb = new StringBuilder();
        for(T thing : things){
            sb.append(thing).append("\n");
        }
        return sb.toString();
    }

}
