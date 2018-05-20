import java.util.*;

public class Zarovnani {
	public static String repeatChar(int n, char c) {
		String result = new String();
		for (int i = 0; i < n; i++) {
			result += c;
		}
		return result;
	}

	public static ArrayList<ArrayList<String>> inputToLines(int width, String inputString) {
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();

		Scanner sc = new Scanner(inputString);

		int currentLineLength = -1;
		ArrayList<String> currentLine = new ArrayList<String>();

		while (sc.hasNext()) {
			String word = sc.next();

			currentLineLength += word.length() + 1;

			if (currentLineLength > width) {
				lines.add(currentLine);
				currentLine = new ArrayList<String>();
				currentLineLength = word.length();
			}
			currentLine.add(word);
		}

		System.out.println(lines);
		return lines;
	}

	public static String formatLeft(int mode, ArrayList<ArrayList<String>> lines) {
		String result = new String();
		for (ArrayList<String> line : lines) {
			for (String word : line) {
				result += word + " ";
			}
			result = result.substring(0, result.length() - 1) + "\n";
		}
		return result;
	}

	public static String formatRight(int mode, int width, ArrayList<ArrayList<String>> lines) {
		String result = new String();
		for (ArrayList<String> line : lines) {
			String currentLine = new String();
			for (String word : line) {
				currentLine += word + " ";
			}
			result += repeatChar(width - currentLine.length() + 1, ' ') + currentLine.substring(0, currentLine.length() - 1) + "\n";
			currentLine = "";
		}
		return result;
	}

	public static String formatCenter(int mode, int width, ArrayList<ArrayList<String>> lines) {
		String result = new String();
		for (ArrayList<String> line : lines) {
			String currentLine = new String();
			for (String word : line) {
				currentLine += word + " ";
			}
			result += repeatChar((width - currentLine.length()) / 2, ' ') + currentLine.substring(0, currentLine.length() - 1) + "\n";
			currentLine = "";
		}
		return result;
	}

	public static String doIt(int mode, int width, String inputString) {
		System.out.println(mode);
		System.out.println(width);

		ArrayList<ArrayList<String>> lines = inputToLines(width, inputString);

		switch (mode) {
			case 0: return formatLeft(mode, lines);
			case 1: return formatRight(mode, width, lines);
			case 2: return formatCenter(mode, width, lines);
			default: return new String();
		}
	}

	public static void main(String[] args) {
		int mode = 0;
		int width = 80;
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "--left": mode = 0;
							   break;
				case "--right": mode = 1;
								break;
				case "--center": mode = 2;
								 break;
				case "--justify": mode = 3;
								  break;
				case "-w": width = Integer.parseInt(args[i+1]);
						   i++;
						   break;
				default: System.out.printf("Unknown argument \"%s\"", args[i]);
						 return;
			}
		}
		String inputString = new String();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			inputString += sc.nextLine() + "\n";
		}
		System.out.println(doIt(mode, width, inputString));
	}
}
