import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Process baby names for all 27 years
        for (int year = 1990; year <= 2017; year++) {
            String filename = "names" + year + ".csv";
            List<Baby> boys = readAndSortBabyNames(filename, "M");
            List<Baby> girls = readAndSortBabyNames(filename, "F");

            System.out.println("Baby Names for " + year + " (Boys):");
            printBabyNames(boys);
            System.out.println("\nBaby Names for " + year + " (Girls):");
            printBabyNames(girls);
        }
    }

    public static List<Baby> readAndSortBabyNames(String filename, String gender) {
        List<Baby> babyNames = FileHandler.readBabyNames(filename, gender);
        Collections.sort(babyNames);
        return babyNames;
    }

    public static void printBabyNames(List<Baby> babyNames) {
        for (Baby baby : babyNames) {
            System.out.println(baby);
        }
    }
}
