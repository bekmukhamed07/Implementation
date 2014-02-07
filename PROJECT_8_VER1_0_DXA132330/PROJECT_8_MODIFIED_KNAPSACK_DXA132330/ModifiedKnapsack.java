/**
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Dany
 *
 */
public class ModifiedKnapsack {

	/**
	 * @param args
	 */
	public static ArrayList<Integer> resultList=new ArrayList<Integer>();
	public static ArrayList<Integer> resultArray=new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] inArray={100,1000,200,100,-100,-1000, -200, -100};
		//int[] inArray={100,-100,100,-100,250,-250};
		//int[] inArray={100,100,250,-100,-100,-250};
		//int res=new ModifiedKnapsack().findDistinctWeights(inArray, inArray.length);
		
		new ModifiedKnapsack().readInAndfindDistinctWeights("/users/dany/downloads/suma.txt");

		for(int i:resultList )
		{
			System.out.println(" "+i);
		}

	}
	
	public void readInAndfindDistinctWeights(String inFile)
	{
		File infile=new File(inFile);
		int arraySize=0;
		int[] inArray=null;
		int value=0;
		int result=0;
		int j=0;
		try {
			Scanner scanner=new Scanner(infile);
			while((arraySize=scanner.nextInt())!=0)
			{
			j=0;
			inArray=new int[(arraySize*2)];
			for(int i=0;i<arraySize;i++)
			{
				value=scanner.nextInt();
				inArray[j++]=value;
			}
			
			for(int i=0;i<arraySize;i++)
			{
				inArray[j++]=(-inArray[i]);
			}
			
			result=new ModifiedKnapsack().findDistinctWeights(inArray, inArray.length);
			resultList.add(result);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int findDistinctWeights(int[] inArray, int nTotalWeights)
	{

		int resultTemp=0;
		resultArray=new ArrayList<Integer>();
		int listSize=0;
		
		resultArray.add(inArray[0]);
		
		for(int i=1;i<nTotalWeights;i++)
		{
			listSize=resultArray.size();
			for(int j=0;j<listSize;j++)
			{
				resultTemp=inArray[i]+resultArray.get(j);
				if(resultTemp>0&&!resultArray.contains(resultTemp))
				resultArray.add(resultTemp);
				
				if(!resultArray.contains(inArray[i])&&inArray[i]>0)
				resultArray.add(inArray[i]);
				
			}
			}
	
		return resultArray.size();
	}

}
