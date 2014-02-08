/**
 * 
 */
package PROJECT_2_LONG_VER_1_2;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Dany
 *
 */
public class LinkedListPolynomial {

	/**
	 * @param args
	 */
	static String _sNegative_Error="Negative number not supported";
	static HashMap<Integer, String> hDynamicMap=new HashMap<Integer, String>();
	static final int nBase=1000000000;
	static String m="4";
	static int n=1000000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		long inTime=System.currentTimeMillis();
		String addedNde=addNumber("87675673456", "5432432321", 1000000000);
		System.out.println("ADD OUTPUT : "+addedNde);
		
		String resultSub=subractNumber("87675673456", "5432432321", 1000000000);
		System.out.println("SUBRACT OUTPUT : "+resultSub);
		
		String resultMul=multiplicationHandler("87675673456", "5432432321", 1000000000);
		System.out.println("MULTIPLY OUTPUT : "+resultMul);
		
		String resultExp=ExponentialHandler("4", n);
		System.out.println("EXPONENTIAL "+resultExp);
		
		long pTime=System.currentTimeMillis();
		System.out.println("Time in Secs "+(pTime-inTime));
		
	}
	
	public static LinkedList<Node> frameList(String sNumber, int nBase)
	{
		int nExp=0;
		int noOfDigit=1;
		int noOfNodes;
		
		noOfDigit=(int) Math.log10(nBase);
		
		//To identify the no of nodes for the linked list . may have an extra node for less than 4 digits
		noOfNodes=sNumber.length()/noOfDigit;
		
		LinkedList<Node> resList=new LinkedList<Node>();
		for(int i=0;i<noOfNodes;i++)
		{
			Node newNode=new Node();
			newNode.sCoEff=Long.parseLong(sNumber.substring((sNumber.length())-noOfDigit, sNumber.length()));
			newNode.sBase=nBase;
			newNode.exp=nExp;
			resList.addLast(newNode);
			sNumber=sNumber.substring(0, sNumber.length()-noOfDigit);
			nExp++;
		}
		if(sNumber.length()>0)
		{
			Node newNode=new Node();
			newNode.sCoEff=Long.parseLong(sNumber);
			newNode.sBase=nBase;
			newNode.exp=nExp;
			resList.addLast(newNode);
			
		}
		
		return resList;
		
	}

	public static String addNumber(String sNum1, String sNum2, int nBase)
	{
		LinkedList<Node> lList1=frameList(sNum1, nBase);
		LinkedList<Node> lList2=frameList(sNum2, nBase);
		LinkedList<Node> resList=new LinkedList<Node>();
		//String sResult="";
		//String sData="";
		StringBuilder sBuilder=new StringBuilder("");

		
		
		long nNum1=0;
		long nNum2=0;
		long nCarry=0;
		int nExp1=0;
		int nExp2=0;
		int base=nBase;
		long res=0;
		//int nCoEff=0;
		//int noOfDigit=(int) Math.log10(nBase);


		if(lList1==null||lList2==null)
			return null;

		while((lList1.peek()!=null) || (lList2.peek()!=null))
		{

			if((lList1.peek()!=null) && (lList2.peek()!=null))
			{
			Node newNode1=lList1.pop();
			Node newNode2=lList2.pop();
			Node addedNode=new Node();
			nNum1=newNode1.sCoEff;
			nNum2=newNode2.sCoEff;
			//Inserting base and exp of any of the number because we are accessing from right most bit
			base=newNode1.sBase;
			nExp1=newNode1.exp;
			
			//To add the result set
			res=nCarry+nNum1+nNum2;
			//To make the carry as zero.if carry is not zero new node must be inserted
			nCarry=0;
			nCarry=res/base;
			//System.out.println("carry after addition : "+nCarry);
			res=res%base;
			//System.out.println("result : "+res);

			addedNode.sCoEff=res;
			addedNode.exp=nExp1+nExp2;
			addedNode.sBase=base;
			
			resList.addLast(addedNode);
			}else{
				LinkedList<Node> contList;
				Node newNode=new Node();
				contList=(lList1.peek()!=null)?lList1:lList2;

				while(contList.peek()!=null)
				{
					newNode=contList.pop();
					res=newNode.sCoEff+nCarry;
					newNode.sCoEff=res;
					nExp1=newNode.exp;
					nCarry=0;
					
					nCarry=res/base;
					//System.out.println("carry after addition : "+nCarry);
					res=res%base;
					resList.addLast(newNode);
				}
			}
		}
		//To insert the last carry of the addition
		if(nCarry!=0)
		{
			Node newNode=new Node();
			newNode.sCoEff=nCarry;
			newNode.exp=nExp1+1;
			newNode.sBase=nBase;
			
			resList.addLast(newNode);
			
		}
		while(resList.peek()!=null)
		{
			Node newNode12=new Node();
			newNode12=resList.pop();
			
			sBuilder.insert(0, addTrailingZeros(newNode12.sCoEff, nBase));
			
			/*String formatted="";
			sData=Integer.toString(newNode12.sCoEff);
			if(sData.length()<noOfDigit)
			{
				formatted="%0"+noOfDigit+"d";
			    sData=String.format(formatted, newNode12.sCoEff);
			}
			sResult=sData+sResult;
			*/
		}
		return sBuilder.toString().replaceFirst("^0+(?!$)", "");

		//return sResult;
	}
	
	public static String subractNumber(String sNum1, String sNum2, int nBase)
	{
		int sLen1=sNum1.length();
		int sLen2=sNum2.length();
		if(sLen1<sLen2)
		{
			return _sNegative_Error;
		}
		else if(sLen1==sLen2)
		{
			if((sNum1.compareTo(sNum2))<0)
			{
				return _sNegative_Error;
			}
		}
		
		LinkedList<Node> res=frameList(sNum1, nBase);
		LinkedList<Node> res1=frameList(sNum2, nBase);

		String sRes=subractHandler(res, res1, nBase);
		return sRes;
	}

	public static String subractHandler(LinkedList<Node> oList1, LinkedList<Node> oList2, int nBase)
	{
		LinkedList<Node> oResList=new LinkedList<Node>();

		long nNum1=0;
		long nNum2=0;
		long nCarry=0;
		long resNum=0;
		int nExp=0;
		String resStr="";
		//String sData="";
		StringBuilder sBuilder=new StringBuilder("");
		
		if(oList1==null||oList2==null)
			return null;
		
		while((oList1.peek()!=null) || (oList2.peek()!=null))
		{

			if((oList1.peek()!=null) && (oList2.peek()!=null))
			{
			Node oNode1=oList1.pop();
			Node oNode2=oList2.pop();
			Node oResNode=new Node();
			nNum1=oNode1.sCoEff;
			nNum2=oNode2.sCoEff;
			nExp=oNode1.exp;

			
			nNum1=nNum1+nCarry;
			nCarry=0;
			if(nNum1<nNum2)
			{
				nNum1=nNum1+nBase;
				nCarry=-1;
				
			}
			resNum=nNum1-nNum2;
			resStr=addTrailingZeros(resNum, nBase);
			oResNode.sCoEff=Integer.parseInt(resStr);
			oResNode.exp=nExp;
			
			oResList.addLast(oResNode);

		}else
		{

			LinkedList<Node> contList;
			Node newNode=new Node();
			contList=oList1;

			while(contList.peek()!=null)
			{
				newNode=contList.pop();
				resNum=newNode.sCoEff+nCarry;
				newNode.sCoEff=resNum;
				newNode.exp=newNode.exp;
				nCarry=0;

				oResList.addLast(newNode);
			}
		
			
		}
		}
		//It will be useful while we handle the negative numbers
		/*if(nCarry==-1)
		{
			Node negNode=new Node();
			negNode.sCoEff=-1;
			negNode.exp=nExp;	
			oResList.addLast(negNode);

		}*/
		while(oResList.peek()!=null)
		{
			Node res=oResList.pop();
			
			sBuilder.insert(0, addTrailingZeros(res.sCoEff, nBase));
			//String resData=addTrailingZeros(res.sCoEff, nBase);
			//sData=resData+sData;
		}
		//return sData;
		return sBuilder.toString().replaceFirst("^0+(?!$)", "");
		
	}
	
	public static String multiplicationHandler(String sNum1, String sNum2, int nBase)
	{		
		LinkedList<Node> oList1=frameList(sNum1, nBase);
		LinkedList<Node> oList2=frameList(sNum2, nBase);
		String resl=multiplyNumber(oList1, oList2, nBase);
		return resl.replaceFirst("^0+(?!$)", "");
		
	}
	
	public static String multiplyNumber(LinkedList<Node> lList1, LinkedList<Node> lList2, int nBase)
	{
		int inFirstLeng=lList1.size();
		int inSecondLen=lList2.size();
		long[] nBuffer=new long[(inFirstLeng+inSecondLen)];
		long[] nCarryBuffer=new long[inFirstLeng+inSecondLen];
		
		long nBuffCoEff=0;
		long nBuffCarry=0;
		
		long additionCarry=0;
		

		long resCoEff=0;
		int resExp=0;
		
		//String sData="";
		StringBuilder sBuilder=new StringBuilder("");
		long nResNum=0;
		
		if(lList1==null||lList2==null)
			return null;
			for(Node oNode1 : lList1)
			{
			for(Node oNode2 : lList2)	
			{
				resCoEff=oNode1.sCoEff*oNode2.sCoEff;
				resExp=oNode1.exp+oNode2.exp;
				if(resExp==0)
				{
					nBuffCoEff=resCoEff%nBase;
					nBuffCarry=resCoEff/nBase;
					
					nBuffer[resExp]=nBuffCoEff;
					nCarryBuffer[resExp]=nBuffCarry;
				}else
				{
					
					resCoEff=resCoEff+nBuffer[resExp];
					
					nBuffCoEff=resCoEff%nBase;
					nBuffCarry=resCoEff/nBase;
					
					nBuffer[resExp]=nBuffCoEff;
					nCarryBuffer[resExp]+=nBuffCarry;
				}
				
			}
		}
			
			for(int i=0;i<nBuffer.length;i++)
			{
				if(i==0)
				{
					sBuilder.insert(0, addTrailingZeros(nBuffer[i], nBase));
					//sData=addTrailingZeros(nBuffer[i], nBase)+sData;
				}else
				{
				nResNum=nBuffer[i]+nCarryBuffer[i-1];
				additionCarry=nResNum/nBase;
				nResNum=nResNum%nBase;
				nCarryBuffer[i]+=additionCarry;
				//sData=addTrailingZeros(nResNum, nBase)+sData;

				sBuilder.insert(0, addTrailingZeros(nResNum, nBase));

				
				}
			}
			return sBuilder.toString();
		//return sData;
	}
	
	public static String addTrailingZeros(long nNum, int nBase)
	{
			int noOfDigit=(int) Math.log10(nBase);
			String formatted="%0"+noOfDigit+"d";
		    String resData=String.format(formatted, nNum);
		    
		    return resData;
		
	}

	public static String ExponentialHandler(String m, int n)
	{
		hDynamicMap.put(1, m);
		String res=mPowerN(m, n);
		return res;
	}
	
	public static String mPowerN(String m, int n)
	{
		String mapEntry;
		
		if(     ( mapEntry = hDynamicMap.get(n)   )!=null )
		{
			return mapEntry;
		}
		if(n==0)
			return m;
		
		if((n&1)==0)
		{
			hDynamicMap.put(n, multiplicationHandler(mPowerN(m, n/2), mPowerN(m, n/2), nBase));
		}else
		{
			hDynamicMap.put(n, multiplicationHandler(m, multiplicationHandler(mPowerN(m, n/2), mPowerN(m, n/2), nBase), nBase));

		}
		return hDynamicMap.get(n);

	}
}
