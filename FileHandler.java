import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static List<Baby> readBabyNames(String filename, String gender) {
        List<Baby> babyNames = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[gender.equals("M") ? 1 : 3].trim();
                    int rank = Integer.parseInt(parts[0].trim());
                    int count = Integer.parseInt(parts[gender.equals("M") ? 2 : 4].trim());
                    babyNames.add(new Baby(name, rank, count));
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return babyNames;
    }
}
