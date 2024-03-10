import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example usage for one year (1990)
        List<Baby> boys1990 = readAndSortBabyNames("names1990.csv");
        List<Baby> girls1990 = readAndSortBabyNames("names1990.csv");

        // Print baby names for one year
        System.out.println("Baby Names for 1990 (Boys):");
        printBabyNames(boys1990);
        System.out.println("\nBaby Names for 1990 (Girls):");
        printBabyNames(girls1990);

        // Similar process for all 27 years...
    }

    public static List<Baby> readAndSortBabyNames(String filename) {
        List<Baby> babyNames = FileHandler.readBabyNames(filename);
        Collections.sort(babyNames);
        return babyNames;
    }

    public static void printBabyNames(List<Baby> babyNames) {
        for (Baby baby : babyNames) {
            System.out.println(baby);
        }
    }
}
