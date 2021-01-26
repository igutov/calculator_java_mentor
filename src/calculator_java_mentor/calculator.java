package calculator_java_mentor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class calculator {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Введите данные: ");

		String data = in.nextLine();

		// String check_data = ("(\\+*)\\d{11}");

		Pattern pattern = Pattern.compile("\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}"); // правила поиска рег выр
		Matcher matcher = pattern.matcher(data);

		if (matcher.find()) {
			System.out.println(matcher.group());
		} else {
			System.out.println("Error");
		}
		// while (matcher.find())
		// 	System.out.println(matcher.group());

		// System.out.println("56 ".matches("\\d{2}\\s"));
		// System.out.println("5 + 4".matches("\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}"));
	}

}