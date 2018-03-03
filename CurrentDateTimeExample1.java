import java.text.SimpleDateFormat;  
import java.util.Date;  
public class CurrentDateTimeExample1 {  
public static void main(String[] args) {  
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  
    System.out.println(formatter.format(date));  
}  
}  
// import java.time.format.DateTimeFormatter;  
// import java.time.LocalDateTime;    
// public class CurrentDateTimeExample1 {    
  // public static void main(String[] args) {    
   // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   // LocalDateTime now = LocalDateTime.now();  
   // System.out.println(dtf.format(now));  
  // }    
// }    