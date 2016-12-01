package business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		Date check_in = new Date();
		Date check_out = new Date();
		check_out.setHours(check_in.getHours() + 2);

		Client c = new Client(1, "12312312312", "Luccas", 23, ClientGender.MALE, ClientCategory.GOLD, check_in, check_out);
		System.out.println(c);
	}

}