
import java.io.File;
import java.util.Scanner;

/**
 * Class for executing Double Hashing operations
 */
public class DoubleHashOperations {
    public static void main(String[] args) {
        long inTime = System.currentTimeMillis();
        System.out.println("Output: " + executeDoubleHashing("/users/dany/downloads/skewed.txt"));
        long pTime = System.currentTimeMillis();
        System.out.println("Time in Milli Secs " + (pTime - inTime));
    }


    public static Long executeDoubleHashing(String inFile) {
        File infile = new File(inFile);
        Long findFailureCount = 0L;
        String sOperation = "";
        OpenAddressDoubleHash doubleHash = new OpenAddressDoubleHash();

        try {
            Scanner fileScanner = new Scanner(infile);
            while (fileScanner.hasNext()) {
                if (!(Character.isDigit((sOperation = fileScanner.next()).charAt(0)))) {
                    if (sOperation.compareTo("Insert") == 0) {
                        doubleHash.insert(fileScanner.nextLong(), fileScanner.nextLong());
                    } else if (sOperation.compareTo("Find") == 0) {
                        if (null ==doubleHash.find(fileScanner.nextLong())){
                            findFailureCount++;
                        }
                    } else if (sOperation.compareTo("Remove") == 0) {
                        doubleHash.remove(fileScanner.nextLong());

                    }
                } else if (sOperation.compareTo("Size") == 0) {
                    {
                        doubleHash.getSize();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return findFailureCount;
    }
}