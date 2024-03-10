import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Process baby names for all 27 years
        for (int year = 1990; year <= 2017; year++) {
            String filename = "names" + year + ".csv";
            Baby[] boys = createAndSortBabyArray(filename, "M");
            Baby[] girls = createAndSortBabyArray(filename, "F");

            // Store baby names in text files
            storeBabyNamesInTextFile("Boy" + year + ".txt", boys);
            storeBabyNamesInTextFile("Girl" + year + ".txt", girls);

            System.out.println("Baby Names for " + year + " stored in files.");
        }
    }

    public static Baby[] createAndSortBabyArray(String filename, String gender) {
        List<Baby> babyNames = FileHandler.readBabyNames(filename, gender);
        Baby[] babies = new Baby[1000]; // Assuming there are at most 1000 unique names
        babyNames.subList(0, Math.min(1000, babyNames.size())).toArray(babies);
        Arrays.sort(babies);
        return babies;
    }

    public static void storeBabyNamesInTextFile(String filename, Baby[] babyArray) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Baby baby : babyArray) {
                if (baby != null) {
                    writer.write(baby.toString() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
