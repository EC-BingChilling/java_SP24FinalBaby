import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        for (int year = 1990; year <= 2017; year++) {
            String filename = "names" + year + ".csv";
            Baby[] boys = createAndSortBabyArray(filename, "M");
            Baby[] girls = createAndSortBabyArray(filename, "F");

            writeToFile(boys, "Boy" + year + ".txt");
            writeToFile(girls, "Girl" + year + ".txt");
        }

        // Process and write total boys and girls
        Map<String, Integer> totalBoysMap = new HashMap<>();
        Map<String, Integer> totalGirlsMap = new HashMap<>();
        for (int year = 1990; year <= 2017; year++) {
            String filename = "names" + year + ".csv";
            List<Baby> boys = FileHandler.readBabyNames(filename, "M");
            List<Baby> girls = FileHandler.readBabyNames(filename, "F");
            updateTotalNames(totalBoysMap, boys);
            updateTotalNames(totalGirlsMap, girls);
        }
        writeTotalNamesToFile(totalBoysMap, "TotalBoys.txt");
        writeTotalNamesToFile(totalGirlsMap, "TotalGirls.txt");
    }

    public static Baby[] createAndSortBabyArray(String filename, String gender) {
        List<Baby> babyNames = FileHandler.readBabyNames(filename, gender);
        Collections.sort(babyNames);
        Baby[] babies = new Baby[1000]; // Assuming there are at most 1000 unique names
        babyNames.subList(0, Math.min(1000, babyNames.size())).toArray(babies);
        return babies;
    }

    public static void writeToFile(Baby[] babies, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Baby baby : babies) {
                if (baby != null) {
                    writer.write(baby.toString() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void updateTotalNames(Map<String, Integer> totalNamesMap, List<Baby> babies) {
        for (Baby baby : babies) {
            String name = baby.getName();
            int count = baby.getCount();
            totalNamesMap.put(name, totalNamesMap.getOrDefault(name, 0) + count);
        }
    }

    public static void writeTotalNamesToFile(Map<String, Integer> totalNamesMap, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            List<Map.Entry<String, Integer>> sortedEntries = totalNamesMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toList());

            for (Map.Entry<String, Integer> entry : sortedEntries) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
