/**
 * 
 */

/**
 * @author Dany
 *
 */
public class HashImplementationAndAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new HashImplementationAndAnalysis().runAndAnalyseHashImplementations("/users/dany/downloads/skewed.txt");
	}
	
	public void runAndAnalyseHashImplementations(String inFile)
	{
		long inTime=System.currentTimeMillis();
		Long result=new SeperateChainingOperations().operateOnHashingFunctions(inFile);
		System.out.println("Seperate Chaining with two choice hashing : "+result);
		result=0L;
		long pTime=System.currentTimeMillis();
		System.out.println("Time taken in Milli Secs "+(pTime-inTime));
		
		inTime = System.currentTimeMillis();
		result=DoubleHashOperations.executeDoubleHashing(inFile);
		System.out.println("Open addressing with double hashing : "+result);
		result=0L;
	    pTime = System.currentTimeMillis();
	    System.out.println("Time taken in Milli Secs " + (pTime - inTime));
	    
	    inTime = System.currentTimeMillis();
		result=CuckooHashingOperations.executeCuckooHashing(inFile);
		System.out.println("Cuckoo hashing : "+result);
		result=0L;
	    pTime = System.currentTimeMillis();
	    System.out.println("Time taken in Milli Secs " + (pTime - inTime));
	    
	    inTime = System.currentTimeMillis();
		result=new QuadraticProbingOperations().operateOnHashingFunctions(inFile);
		result=0L;
		System.out.println("Quadratic Probing Hash Table : "+result);
	    pTime = System.currentTimeMillis();
	    System.out.println("Time taken in Milli Secs " + (pTime - inTime));
		
	}

}
