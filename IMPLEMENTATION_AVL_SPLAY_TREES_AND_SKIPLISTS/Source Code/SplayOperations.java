import java.io.File;
import java.util.Scanner;

/**
 * Main class for executing Splay Tree operations
 */
public class SplayOperations {

    public static void main(String[] args) {
        long inTime = System.currentTimeMillis();
        Long result = new SplayOperations().operateOnBalancedTrees("d:\\input.txt");
        long pTime = System.currentTimeMillis();
        System.out.println("Total Result : " + result);
        System.out.println("Time in Milli Secs " + (pTime - inTime));

    }

    public Long operateOnBalancedTrees(String inFile) {
        File infile = new File(inFile);
        Long nTotalOperationResult = 0L;
        String sOperation = "";
        SplayTree sTree = new SplayTree();

        try {
            Scanner fileScanner = new Scanner(infile);
            while (fileScanner.hasNext()) {
                if (!(Character.isDigit((sOperation = fileScanner.next()).charAt(0)))) {
                    if (sOperation.compareTo("Insert") == 0)
                        sTree.insert(fileScanner.nextLong(), fileScanner.nextLong());
                    else if (sOperation.compareTo("Find") == 0)
                        sTree.find(fileScanner.nextLong());
                    else if (sOperation.compareTo("FindMin") == 0)
                        nTotalOperationResult += sTree.findMin();
                    else if (sOperation.compareTo("FindMax") == 0)
                        nTotalOperationResult += sTree.findMax();
                    else if (sOperation.compareTo("Remove") == 0)
                        sTree.remove(fileScanner.nextLong());
                    else if (sOperation.compareTo("RemoveValue") == 0)
                        nTotalOperationResult += sTree.removeValue(fileScanner.nextLong());
                    else if (sOperation.compareTo("Size") == 0)
                        nTotalOperationResult += sTree.size();
                    else if (sOperation.compareTo("IsEmpty") == 0)
                        sTree.isEmpty();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return nTotalOperationResult;
    }

}
