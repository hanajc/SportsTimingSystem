import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Simulator {
	ChronoTimer ct;
	boolean simulatorOn;

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 */
	public Simulator() throws IOException {

		simulatorOn = true;
		ct = new ChronoTimer();
		Scanner s = new Scanner(System.in);
		System.out.print("Read file from console or file (c/f): ");
		String input = s.nextLine();
		if (input.equalsIgnoreCase("f")) {
			System.out.print("Enter the file name: ");
			String fileName = s.nextLine();
			try (Scanner sc = new Scanner(new File(fileName))) {

				// parsing each line into a string
				while (sc.hasNextLine() && simulatorOn) {

					// splitting lines by delimiter (any number of whitespaces)
					generalParser(sc.nextLine().trim().split("\\s+"));
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}
		} else if (input.equalsIgnoreCase("c")) {
			String uInput = "";
			System.out.println("ChronoTimer simulator: powering chronoTimer on.");
			ct.POWER();
			while (!uInput.equals("Q")) {
				System.out.println("Enter Full command to be sent to parser (WITH SPACES) Q to quit:");
				String time = Time.time2formattedString(Time.getLocalTime()) + " ";
				uInput = s.nextLine();
				System.out.println("Performing action on:" + (time + uInput));
				if (!uInput.equals("Q")) {
					generalParser((time + uInput).trim().split("\\s+")); // original
				}
			}
		}
		s.close();
	}

	/**
	 * parses input text file and executes commands
	 * 
	 * @param tokens
	 * @throws IOException
	 */
	private void generalParser(String[] tokens) throws IOException {

		// @@@@@@@@@@ FOR TESTING @@@@@@@@@@

		System.out.println("Command ==> " + tokens[1]);

		// @@@@@@@@@@ END TESTING BIT @@@@@@@@@@

		switch (tokens[1]) {
		default:
			System.out.println("The feature, \"" + tokens[1] + "\" has not been implemented yet.");
		case "POWER":
			ct.POWER();
			break;
		case "NEWRUN":
			ct.NEWRUN();
			break;
		case "TOG":
			ct.TOG(Integer.parseInt(tokens[2]));
			break;
		case "TRIG":
			ct.TRIG(Integer.parseInt(tokens[2]));
			break;
		case "PRINT":
			ct.PRINT();
			break;
		case "EXPORT":
			ct.EXPORT(Integer.parseInt(tokens[2]));
			break;
		case "ENDRUN":
			ct.ENDRUN();
			break;
		case "TIME":
			// tokens[2] to set time
			ct.TIME(tokens[2]);
			break;
		case "NUM":
			ct.NUM(tokens[2]);
			break;
		case "EVENT":
			ct.EVENT(tokens[2]);
			break;
		case "EXIT":
			simulatorOn = false;
			break;
		case "CONN":
			ct.CONN(tokens[2], Integer.parseInt(tokens[3]));
		}

	}

	public static void main(String[] s) throws IOException {
		new Simulator();
	}
}