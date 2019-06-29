import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {

        MorseCodeConvert morseCodeConvert;

        try {

            morseCodeConvert = new MorseCodeConvert("Codes.txt");

            int choice = 0;

            Scanner scanner = new Scanner(System.in);

            while (choice != 4) {

                System.out.println("Select one of the following choices: ");
                System.out.printf("%2s1. Print conversion codes.%n", "");
                System.out.printf("%2s2. Convert string.%n", "");
                System.out.printf("%2s3. Convert file.%n", "");
                System.out.printf("%2s4. Exit.%n", "");
                System.out.print("Choice (1-4): ");

                String s = scanner.next();

                // error checking
                if (s.charAt(0) >= '1' && s.charAt(0) <= '4')
                    choice = Character.getNumericValue(s.charAt(0));

                scanner.nextLine();
                try {
                    switch (choice) {
                        case 1:
                            morseCodeConvert.printMorseCodeList();
                            break;
                        case 2:
                            // ensures the convertString method can handle null and empty string inputs
                            morseCodeConvert.convertString(null);
                            morseCodeConvert.convertString("");
                            System.out.print("Enter line to convert: ");
                            String line = scanner.nextLine();
                            morseCodeConvert.convertString(line);
                            break;
                        case 3:
                            System.out.print("Enter file name: ");
                            String fileName = scanner.nextLine();
                            morseCodeConvert.convertFile(fileName);
                            break;
                        case 4:
                            break;
                        default:
                            System.err.println("Invalid choice. Try again!");
                    }
                }
                catch (Exception ex) {
                    System.err.println(ex.toString());
                }
            }

            scanner.close();
        }
        catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("Program terminated");
    }
}