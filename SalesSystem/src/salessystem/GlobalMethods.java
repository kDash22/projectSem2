package salessystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class GlobalMethods {

    public static String dateTimeFormat(LocalDateTime date ){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    public static <T> void printList(List<T> things){
        if(things.isEmpty()){
            System.out.println("The list is empty.");

        }else {
            for (T thing : things) {
                System.out.println(thing);

            }
        }
    }
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
