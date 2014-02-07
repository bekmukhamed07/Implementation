/**
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Dany
 *
 */
public class PrulMaxProfit {

	/**
	 * @param args
	 */
	public static ArrayList<Integer>[] inList=null;
	public static ArrayList<Integer>[] boxCountList=null;
	public int[] maxProfitArray=null;

	public static ArrayList<Integer> resultSet=new ArrayList<Integer>();


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new PrulMaxProfit().readInput("/users/dany/downloads/suma.txt");
	
	}
	
	public void readInput(String inFile)
	{
		File infile=new File(inFile);
		int noOfPiles,noOfBoxes=0,prevProfit=0,currentProfit=0;
		int maxProfit=Integer.MIN_VALUE;
		int totalMaxProfit=0;
		ArrayList<Integer> boxList=null;
		try {
			Scanner scanner=new Scanner(infile);
			while((noOfPiles=scanner.nextInt())!=0)
			{
				totalMaxProfit=0;
				resultSet=new ArrayList<Integer>();
				inList=(ArrayList<Integer>[])new ArrayList[noOfPiles];
				maxProfitArray=new int[noOfPiles];
				for(int i=0;i<noOfPiles;i++)
				{
					noOfBoxes=scanner.nextInt();
					boxList=new ArrayList<Integer>();
					for(int j=0;j<noOfBoxes;j++)
					{
						currentProfit=10-scanner.nextInt();
						currentProfit+=prevProfit;
						
						if(currentProfit>maxProfit)
							maxProfit=currentProfit;
						
						boxList.add(currentProfit);
						prevProfit=currentProfit;
						
					}
					inList[i]=boxList;
					totalMaxProfit+=maxProfit;
					maxProfitArray[i]=maxProfit;
					maxProfit=Integer.MIN_VALUE;
					currentProfit=0;
					prevProfit=0;

				}
				
				//To create a new array of list of boxes with maximum profit 
				boxCountList=(ArrayList<Integer>[])new ArrayList[noOfPiles];
				for(int k=0;k<noOfPiles;k++)
				{
					ArrayList<Integer> boxCount = inList[k];
					int listmaxtProfit=maxProfitArray[k];
					ArrayList<Integer> tempList=new ArrayList<Integer>();
					for(int l=0;l<boxCount.size();l++)
					{
						if(boxCount.get(l)==listmaxtProfit)
							tempList.add(l+1);
					}
					boxCountList[k]=tempList;
				}
				
				//To fine the total no of pruls to buy
				//addNumbersFromEachList(0, noOfPiles-1);
				addNumbersFromList(noOfPiles);
				System.out.println("\n\nProblem : "+noOfPiles);
				System.out.println("Maximum Profit is : "+totalMaxProfit);
				System.out.print("Number of pruls to buy: ");

				Collections.sort(resultSet);
				for(int i : resultSet)
				{
					System.out.print(" "+i);
				}
				
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//This method used to add the number from different list using n * n iterations
	public void addNumbersFromEachList(int value, int pos)
	{
		if(pos<0)
		{
			if(!resultSet.contains(value))
			resultSet.add(value);
			return;
		}
		ArrayList<Integer> intermediateList=boxCountList[pos--];
		while(intermediateList.isEmpty())
			intermediateList=boxCountList[pos--];
				for(int i : intermediateList)
				{
					int res=value+i;
					addNumbersFromEachList(res, pos);
				}
	}

	public void addNumbersFromList(int noOfPiles)
	{
		ArrayList<Integer> tempBuffer=new ArrayList<Integer>();
		ArrayList<Integer> newTempBuffer = new ArrayList<Integer>();
		int addedValue=0;
		tempBuffer.add(0);
		
		for(int i=0;i<noOfPiles;i++)
		{
			ArrayList<Integer> inList = boxCountList[i];
			for(int n : inList)
			{
				for(int m : tempBuffer)
				{
					addedValue=n+m;
					if(!newTempBuffer.contains(addedValue))
					newTempBuffer.add(addedValue);
				}
			}
			
			
			tempBuffer=new ArrayList<Integer>();
			tempBuffer.addAll(newTempBuffer);
			newTempBuffer=new ArrayList<Integer>();
		}
		
		resultSet=tempBuffer;
	}
}
