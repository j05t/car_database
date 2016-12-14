package autoboerse;

import java.util.List;

/**
* This class was taken from persistence-with-jpa-examples
* 
* @author  Harald Habiger
* @version 1.0
*/

public class Printer {

	public static <T extends Object> void print(List<T> list, String header) {

		System.out.println();
		System.out.println(header);

		if (list == null) {
			System.out.println(" list is null ! ");
			return;
		}
		if (list.size() == 0) {
			System.out.println(" list is empty ! ");
			return;
		}

		for (int index = 0; index < list.size(); index++)
			System.out.println(list.get(index));

	}
}
