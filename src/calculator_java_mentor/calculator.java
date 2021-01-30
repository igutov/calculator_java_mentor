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
		map_matchers.put("arab", "\\[0-1]\\s(\\+|-|\\*|/|)\\s\\[0-1]");
		map_matchers.put("roman", "\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}");

		return (data_matcher.matches(map_matchers.get(name_variable))); // сравнение введенных пользователем данных и
																		// рег выр в карте, найденном по ключу(имени,
																		// переданном при вызове метода resolution())
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String calculator_data;
		Boolean type_format;

		do {
			System.out.print("Выберите формат чисел: Арабские/Римские(1/0): ");
			calculator_data = in.nextLine();
			type_format = calculator_data.equals("1");
		} while (!resolution("format", calculator_data));

		System.out.print(
				"Введите данные. Учтите, что калькулятор поддерживает лишь сложение, вычитание, умножение и деление чисел в диапазоне от нуля до десяти: ");

		calculator_data = in.nextLine();

		String name_variable = type_format ? "roman" : "arab";
		// while (!resolution(name_variable, calculator_data)) {
		// System.out.print("Допустимый формат ввода(х + у): ");
		// calculator_data = in.nextLine();
		// }

		try {
			if (!resolution(name_variable, calculator_data)) {
				throw new NumberFormatException("Недопустимый формат введенных данных");
			}
			in.close();
			// System.out.print("Ответ: ");
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

		String[] symbols = calculator_data.split(" ");

		Innercalculator result = new Innercalculator(Integer.parseInt(symbols[0]), Integer.parseInt(symbols[2]));

		switch (symbols[1]) {
			case "+":
				System.out.println(result.addition());
				break;
			case "-":
				System.out.println(result.subtraction());
				break;
			case "*":
				System.out.println(result.multiplication());
				break;
			case "/":
				try {
					if (Double.isInfinite(result.division())) {
						throw new ArithmeticException("Не делите на ноль");
					}
					System.out.println(result.division());
				} catch (ArithmeticException e) {
					System.out.println(e.getMessage());
				}
				break;
		}
	}
}

/**
 * Innercalculator
 */
class Innercalculator {
	public int var1;
	public int var2;

	Innercalculator(int var1, int var2) {
		this.var1 = var1;
		this.var2 = var2;
	}

	public int addition() {
		return this.var1 + this.var2;
	}

	public int subtraction() {
		return this.var1 - this.var2;
	}

	public int multiplication() {
		return this.var1 * this.var2;
	}

	public double division() {
		return ((double) this.var1 / this.var2);
	}
}