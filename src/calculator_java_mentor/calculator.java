package calculator_java_mentor;

// import java.util.*;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;

public class calculator {

	public static boolean resolution(String name_variable, String data_matcher) { // подаются введенные пользователем
																					// данные для проверки валидности
		Map<String, String> map_matchers = new HashMap<String, String>(); // карта с парой ключ-значение содержащая
																			// регулярные выражения

		map_matchers.put("format", "[0-1]");
		map_matchers.put("arab", "\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}");
		map_matchers.put("roman", "");

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

		String name_variable = type_format ? "arab" : "roman";

		try {
			calculator_data = in.nextLine();
			in.close();
			// System.out.println(resolution(name_variable, calculator_data));
			if (!resolution(name_variable, calculator_data)) {
				throw new Exception("Недопустимый формат введенных данных");
			}
			// System.out.print("Ответ: ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		String[] symbols = calculator_data.split(" ");

		int var1, var2;

		if (type_format) {
			var1 = Integer.parseInt(symbols[0]);
			var2 = Integer.parseInt(symbols[2]);
			// result = new Innercalculator(Integer.parseInt(symbols[0]),
			// Integer.parseInt(symbols[2]));
		} else {
			var1 = InnercalculatorRoman.convert_to_roman(symbols[0]);
			var2 = InnercalculatorRoman.convert_to_roman(symbols[2]);
			// result = new Innercalculator(var1, var2);
		}

		Innercalculator result;
		result = new Innercalculator(var1, var2);

		IntegerConverter int_converter = new IntegerConverter();
		int output = 0;

		switch (symbols[1]) {
			case "+":
				output = result.addition();
				// System.out.println(result.addition());
				// output = type_format ? Integer.toString(result.addition()) :
				// int_converter.intToRoman(result.addition());
				break;
			case "-":
				output = result.subtraction();
				// System.out.println(result.subtraction());

				break;
			case "*":
				output = result.multiplication();
				// System.out.println(result.multiplication());

				break;
			case "/":
				try {
					if (Double.isInfinite(result.division())) {
						throw new ArithmeticException("Не делите на ноль");
					}
					// output = result.division();
					// System.out.println(result.division());

				} catch (ArithmeticException e) {
					System.out.println(e.getMessage());
				}
				break;
		}

		if (type_format) {
			System.out.println("Ответ: " + output);
		} else {
			System.out.println("Ответ: " + int_converter.intToRoman(output));
		}
		// System.out.println("Ответ: " + int_converter.intToRoman(output));
	}
}

/**
 * Innercalculator
 */
class Innercalculator {
	private int var1;
	private int var2;

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

/**
 * Innercalculator_Roma
 */
class InnercalculatorRoman {
	public static int convert_to_roman(String var) {
		Map<String, Integer> roman_symbols = new HashMap<String, Integer>();
		roman_symbols.put("I", 1);
		roman_symbols.put("II", 2);
		roman_symbols.put("III", 3);
		roman_symbols.put("IV", 4);
		roman_symbols.put("V", 5);
		roman_symbols.put("VI", 6);
		roman_symbols.put("VII", 7);
		roman_symbols.put("VIII", 8);
		roman_symbols.put("IX", 9);
		roman_symbols.put("X", 10);

		return roman_symbols.get(var);
	}
}

class IntegerConverter {
	public static String intToRoman(int number) {
		StringBuilder result = new StringBuilder();
		for (Integer key : units.descendingKeySet()) {
			while (number >= key) {
				number -= key;
				result.append(units.get(key));
			}
		}
		return result.toString();
	}

	private static final NavigableMap<Integer, String> units;
	static {
		NavigableMap<Integer, String> initMap = new TreeMap<>();
		initMap.put(1000, "M");
		initMap.put(900, "CM");
		initMap.put(500, "D");
		initMap.put(400, "CD");
		initMap.put(100, "C");
		initMap.put(90, "XC");
		initMap.put(50, "L");
		initMap.put(40, "XL");
		initMap.put(10, "X");
		initMap.put(9, "IX");
		initMap.put(5, "V");
		initMap.put(4, "IV");
		initMap.put(1, "I");
		units = Collections.unmodifiableNavigableMap(initMap);
	}
}
