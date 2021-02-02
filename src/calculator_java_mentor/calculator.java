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

	public static boolean resolution(String type_format, String data_matcher) { // подаются введенные пользователем
																				// данные для проверки валидности
		Map<String, String> map_matchers = new HashMap<String, String>(); // карта с парой ключ-значение содержащая
																			// регулярные выражения

		map_matchers.put("arab", "\\d{1,2}\\s(\\+|-|\\*|/|)\\s\\d{1,2}");
		map_matchers.put("roman", "(X|IX|IV|V?I{0,3})\\s(\\+|-|\\*|/|)\\s(X|IX|IV|V?I{0,3})");

		return (data_matcher.matches(map_matchers.get(type_format))); // сравнение введенных пользователем данных и
																		// рег выр в карте, найденном по ключу(имени,
																		// переданном при вызове метода resolution())
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String calculator_data;
		String[] symbols;
		String type_format;
		int var1, var2;

		System.out.print(
				"Введите данные. Учтите, что калькулятор поддерживает лишь сложение, вычитание, умножение и деление чисел в диапазоне от нуля до десяти: ");

		calculator_data = in.nextLine();
		in.close();

		symbols = calculator_data.split(" ");

		if ((symbols[0].matches("\\d+")) && (symbols[2].matches("\\d+"))) {
			type_format = "arab";
		} else {
			type_format = "roman";
		}

		try {
			if (!resolution(type_format, calculator_data)) {
				throw new Exception("Недопустимый формат введенных данных");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		if (type_format == "arab") { // здесь всячески приводим разные данные к числовому типу
			var1 = Integer.parseInt(symbols[0]);
			var2 = Integer.parseInt(symbols[2]);
		} else {
			var1 = SymbolsRoman.convert_to_roman(symbols[0]);
			var2 = SymbolsRoman.convert_to_roman(symbols[2]);
		}

		Innercalculator result;
		result = new Innercalculator(var1, var2);

		IntegerConverter int_converter = new IntegerConverter();
		int output = 0;

		switch (symbols[1]) {
			case "+":
				output = result.addition();
				break;
			case "-":
				output = result.subtraction();

				if (type_format == "roman") {

					try {
						if (output <= 0)
							throw new Exception("Отрицательные и нулевые значения вычислений недопустимы");
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.exit(0);
					}
				}
				break;
			case "*":
				output = result.multiplication();
				break;
			case "/":
				try {
					output = result.division();
				} catch (ArithmeticException e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}
				break;
		}

		if (type_format == "arab") { // вывод в зависимости от типа алфавита
			System.out.println("Ответ: " + output);
		} else {
			System.out.println("Ответ: " + int_converter.intToRoman(output)); // перед выводом преобразовывается в
																				// римское число
		}
	}
}

/**
 * Innercalculator
 */
class Innercalculator { // тут мат вычисления
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

	public int division() {
		return this.var1 / this.var2;
	}
}

/**
 * Innercalculator_Roma
 */
class SymbolsRoman { // ключ-значение для интерпритации введенных римскими символами данных
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

class IntegerConverter { // это я не совсем понимаю, взял на википедии и немного урезал диапазон знчений,
							// т.к. больше ста калькулятор не выдаст
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
		initMap.put(100, "C");
		initMap.put(90, "XC");
		initMap.put(50, "L");
		initMap.put(40, "XL");
		initMap.put(10, "X");
		initMap.put(9, "IX");
		initMap.put(5, "V");
		initMap.put(4, "IV");
		initMap.put(1, "I");
		// initMap.put(0, "N"); ???
		units = Collections.unmodifiableNavigableMap(initMap);
	}
}
