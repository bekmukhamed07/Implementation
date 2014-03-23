import java.io.File;
import java.util.Scanner;

/**
 * Main class for executing Skip List operations
 *
 */
public class SkipListOperations {

	public static void main(String[] args) {

		long inTime=System.currentTimeMillis();
		Long result=new SkipListOperations().operateOnBalancedTrees("d:\\input.txt");
		long pTime=System.currentTimeMillis();
        System.out.println("Total Result : "+result);
        System.out.println("Time in Milli Secs "+(pTime-inTime));
		
	}
	
	public Long operateOnBalancedTrees(String inFile)
	{
		File infile=new File(inFile);
		Long nTotalOperationResult=(long) 0;
		String sOperation="";
		SkipList<Long, Long> sList=new SkipList<Long, Long>();

		try{
			Scanner fileScanner=new Scanner(infile);
			while(fileScanner.hasNext())
			{
				if(!(Character.isDigit((sOperation=fileScanner.next()).charAt(0))))
				{
					if(sOperation.compareTo("Insert")==0)
					sList.add(fileScanner.nextLong(), fileScanner.nextLong());
					else if(sOperation.compareTo("Find")==0)
					sList.get(fileScanner.nextLong());
					else if(sOperation.compareTo("FindMin")==0)
					nTotalOperationResult+=sList.findMin();
					else if(sOperation.compareTo("FindMax")==0)
					nTotalOperationResult+=sList.findMax();
					else if(sOperation.compareTo("Remove")==0)
					sList.remove(fileScanner.nextLong());
					else if(sOperation.compareTo("RemoveValue")==0)
					nTotalOperationResult+=sList.removeValue(fileScanner.nextLong());
					else if(sOperation.compareTo("Size")==0)
					nTotalOperationResult+=sList.size();
					else if(sOperation.compareTo("IsEmpty")==0)
					sList.isEmpty();
				}
			}
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		return nTotalOperationResult;
	}

}
