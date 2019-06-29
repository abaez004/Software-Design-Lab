/**
 * Test file for Assignment #4.
 * 
 * DO NOT MODIFY
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TreeSet;

public class TestMain {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public static void main(String[] args) throws Exception {

        PhoneList phoneList = new PhoneList();
        Scanner scanner = new Scanner(new File("TestData.txt"));

        // skip file header
        String line = scanner.nextLine();

        while (scanner.hasNextLine()) {

            line = scanner.nextLine();

            String[] arr = line.split(",");
            boolean result;

            switch (arr[0]) {
                case "S":
                    Smart smart = new Smart(Double.parseDouble(arr[5]), arr[1], arr[2], Double.parseDouble(arr[3]), formatter.parse(arr[4]));
                    result = phoneList.add(smart);
                    if (!result)
                        System.err.println("Duplicate ID found, skipping: " + line);
                    break;
                case "W":
                    Wireless wireless = new Wireless(Double.parseDouble(arr[5]), arr[1], arr[2], Double.parseDouble(arr[3]), formatter.parse(arr[4]));
                    result = phoneList.add(wireless);
                    if (!result)
                        System.err.println("Duplicate ID found, skipping: " + line);
                    break;
                case "IP":
                    IPPhone ip = new IPPhone(arr[5], arr[1], arr[2], Double.parseDouble(arr[3]), formatter.parse(arr[4]));
                    result = phoneList.add(ip);
                    if (!result)
                        System.err.println("Duplicate ID found, skipping: " + line);
                    break;
                default:
                    System.err.println("Error: unrecognized item: " + line);
            }
        }

        TreeSet<Landline> setLandline = phoneList.getListLandline();

        System.out.printf("There are %d instances of type Landline%n", setLandline.size());

        phoneList.printFormatted();

        scanner.close();
    }
}