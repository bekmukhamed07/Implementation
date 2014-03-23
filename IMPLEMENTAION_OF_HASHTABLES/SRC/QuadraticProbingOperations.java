/**
 * 
 */

import java.io.File;
import java.util.Scanner;

/**
 * @author Dany
 *
 */
public class QuadraticProbingOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long inTime=System.currentTimeMillis();
		long result=new QuadraticProbingOperations().operateOnHashingFunctions("/users/dany/downloads/skewed.txt");
		System.out.println("Total Operations : "+result);
		long pTime=System.currentTimeMillis();
		System.out.println("Time in Milli Secs "+(pTime-inTime));

	}
	
	public long operateOnHashingFunctions(String inFile)
	{

		File infile=new File(inFile);
		Long nTotalOperationResult= 0L;
		String sOperation="";
		Long nFind=0L;
        //SplayTree sTree = new SplayTree();

        QuadraticProbingHashTable sHashing = new QuadraticProbingHashTable();
        
		try{
			Scanner fileScanner=new Scanner(infile);
			while(fileScanner.hasNext())
			{
				if(!(Character.isDigit((sOperation=fileScanner.next()).charAt(0))))
				{
				//System.out.println(sOperation);
				if(sOperation.compareTo("Insert")==0)
				sHashing.insert(fileScanner.nextLong(), fileScanner.nextLong());
				else if(sOperation.compareTo("Find")==0)
				{
				nFind = sHashing.find(fileScanner.nextLong());
				if(nFind==null)
				{
				nTotalOperationResult++;
				}
				}
				else if(sOperation.compareTo("Remove")==0)
				sHashing.remove(fileScanner.nextLong());
				}
			}
			
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		

		return nTotalOperationResult;
	
	}

}
