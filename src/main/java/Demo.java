import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
public static void main(String[] args) throws ParseException {

	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	
	Date date1 = format.parse("11:00");
	Date date2 = format.parse("03:00");
	
	long time = (date1.getTime() + date2.getTime());
	
	System.out.println(((time/60)/60)/1000);
}
}
