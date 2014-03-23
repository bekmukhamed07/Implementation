
import java.io.File;
import java.util.Scanner;

/**
 * Class for executing Cuckoo Hashing operations
 */
public class CuckooHashingOperations {
    public static void main(String[] args) {
        long inTime = System.currentTimeMillis();
        System.out.println("Output: " + executeCuckooHashing("/users/dany/downloads/skewed.txt"));
        long pTime = System.currentTimeMillis();
        System.out.println("Time in Milli Secs " + (pTime - inTime));
    }

    public static Long executeCuckooHashing(String inFile) {
        File infile = new File(inFile);
        Long findFailureCount = 0L;
        String sOperation = "";
        CuckooHashing cuckooHashing = new CuckooHashing(new StringHashMethods(3));

        try {
            Scanner fileScanner = new Scanner(infile);
            while (fileScanner.hasNext()) {
                if (!(Character.isDigit((sOperation = fileScanner.next()).charAt(0)))) {
                    if (sOperation.compareTo("Insert") == 0) {
                        cuckooHashing.insert(fileScanner.next());
                    } else if (sOperation.compareTo("Find") == 0) {
                        if (!cuckooHashing.find(String.valueOf(fileScanner.nextLong()))) {
                            findFailureCount++;
                        }
                    } else if (sOperation.compareTo("Remove") == 0) {
                        cuckooHashing.remove(String.valueOf(fileScanner.nextLong()));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return findFailureCount;
    }
}
