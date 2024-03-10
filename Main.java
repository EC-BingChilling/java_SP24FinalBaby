import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Process baby names for all 27 years
        for (int year = 1990; year <= 2017; year++) {
            String filename = "names" + year + ".csv";
            Baby[] boys = createAndSortBabyArray(filename, "M");
            Baby[] girls = createAndSortBabyArray(filename, "F");

            writeBabyArrayToFile(boys, "Boy" + year + ".txt");
            writeBabyArrayToFile(girls, "Girl" + year + ".txt");

            // Update total names map
            updateTotalNamesMap(boys, totalBoysMap);
            updateTotalNamesMap(girls, totalGirlsMap);
        }

        // Write total boys and girls to file
        writeTotalNamesToFile(totalBoysMap, "TotalBoys.txt");
        writeTotalNamesToFile(totalGirlsMap, "TotalGirls.txt");
    }

    private static Map<String, Integer> totalBoysMap = new HashMap<>();
    private static Map<String, Integer> totalGirlsMap = new HashMap<>();

    public static Baby[] createAndSortBabyArray(String filename, String gender) {
        List<Baby> babyNames = FileHandler.readBabyNames(filename, gender);
        Collections.sort(babyNames);
        return babyNames.toArray(new Baby[0]);
    }

    public static void writeBabyArrayToFile(Baby[] babyArray, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Baby baby : babyArray) {
                writer.write(baby.toString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void updateTotalNamesMap(Baby[] babies, Map<String, Integer> totalNamesMap) {
        for (Baby baby : babies) {
            String name = baby.getName();
            int count = baby.getCount();
            totalNamesMap.put(name, totalNamesMap.getOrDefault(name, 0) + count);
        }
    }

    public static void writeTotalNamesToFile(Map<String, Integer> totalNamesMap, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Map.Entry<String, Integer> entry : totalNamesMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
