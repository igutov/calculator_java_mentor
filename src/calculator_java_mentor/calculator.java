package calculator_java_mentor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;

public class calculator {

	public static boolean resolution(String name_variable, String data_matcher) { // подаются введенные пользователем
																					// данные для проверки валидности
		Map<String, String> map_matchers = new HashMap<String, String>(); // карта с парой ключ-значение содержащая
																			// регулярные выражения

		map_matchers.put("format", "[0-1]");
		map_matchers.put("arab", "\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}");
		map_matchers.put("roman", "\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}");

		return (data_matcher.matches(map_matchers.get(name_variable))); // сравнение введенных пользователем данных и
																		// рег выр в карте, найденном по ключу(имени,
																		// переданном при вызове метода resolution())
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String type_format;

		do {
			System.out.print("Выберите формат чисел: Арабские/Римские(0/1): ");
			type_format = in.nextLine();
		} while (!resolution("format", type_format));

		// Pattern pattern = Pattern.compile("\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}"); //
		// правила поиска рег выр
		// Matcher matcher = pattern.matcher(type_format);

		// System.out.print(type_format);
		// String data = in.nextLine();

		// String check_data = ("(\\+*)\\d{11}");

		// Pattern pattern = Pattern.compile("\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}"); //
		// правила поиска рег выр
		// Matcher matcher = pattern.matcher(data);

		// if (matcher.find()) {
		// System.out.println(matcher.group());
		// } else {
		// System.out.println("Error");
		// }
		// while (matcher.find())
		// System.out.println(matcher.group());

		// System.out.println("56 ".matches("\\d{2}\\s"));
		// System.out.println("5 + 4".matches("\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}"));
	}

}