


/**
 * 
 */

import java.util.HashMap;

/**
 * @author Dany
 *
 */
public class MaxBSTinBTUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeNode res=new TreeNode(55);
		res.insertBT(res, 75, 55,"right");
		res.insertBT(res, 27, 75,"left");
		res.insertBT(res, 89, 75,"right");
		res.insertBT(res, 95, 89,"right");
		res.insertBT(res, 26, 89,"left");
		res.insertBT(res, 105, 95,"right");
		res.insertBT(res, 23, 95,"left");
		res.insertBT(res, 110, 105,"right");
		res.insertBT(res, 20, 105,"left");
		
		//int[] output=new MaxBSTinBT().getCountAndMinMax(res);
		/*System.out.println("Total BST nodes : "+output[0]);
		System.out.println("Maximum : "+output[2]);
		System.out.println("Minimum : "+output[1]);*/
		new MaxBSTinBTUtil().printBST(res, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		
	}
	
	private static int nBSTCount=0;
	private static int nMaximum=Integer.MIN_VALUE;
	private static int nMinimum=Integer.MAX_VALUE;
	
	public int[] getCountAndMinMax(HashMap<Integer, WrapTree> hMap, int nodeValue)
	{
		int[] countMinMax=new int[3];
		new MaxBSTinBTUtil().countBstNodesHashMap(hMap, nodeValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
		countMinMax[0]=nBSTCount;
		countMinMax[1]=nMinimum;
		countMinMax[2]=nMaximum;
		nBSTCount=0;
		nMaximum=Integer.MIN_VALUE;
		nMinimum=Integer.MAX_VALUE;
		return countMinMax;
		
	}
	
	public boolean countBstNodesHashMap(HashMap<Integer, WrapTree> hMap, Integer key, int min, int max)
	{
		if(key==null)
		{
			return true;
		}
		if(key<min||key>max)
		{
			return false;
		}else
		{
			if(key>nMaximum)
				nMaximum=key;
			if(key<nMinimum)
				nMinimum=key;
			nBSTCount++;
		}
		 countBstNodesHashMap(hMap, hMap.get(key).left, min, key);
		 countBstNodesHashMap(hMap, hMap.get(key).right, key, max);
		return true;
	}

	
	public int[] getCountAndMinMax(TreeNode root)
	{
		int[] countMinMax=new int[3];
		new MaxBSTinBTUtil().processBSTinBT(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		countMinMax[0]=nBSTCount;
		countMinMax[1]=nMinimum;
		countMinMax[2]=nMaximum;
		nBSTCount=0;
		nMaximum=Integer.MIN_VALUE;
		nMinimum=Integer.MAX_VALUE;
		return countMinMax;
		
	}
	
	public boolean processBSTinBT(TreeNode root, int min, int max)
	{
		if(root==null)
		{
			return true;
		}
		if(root.data<min||root.data>max)
		{
			return false;
		}else
		{
			if(root.data>nMaximum)
				nMaximum=root.data;
			if(root.data<nMinimum)
				nMinimum=root.data;
			nBSTCount++;
		}
		processBSTinBT( root.left, min, root.data);
		processBSTinBT( root.right, root.data, max);
		return true;
	}
	
	public boolean printBST(TreeNode root, int min, int max)
	{
		if(root==null)
		{
			return true;
		}
		if(root.data<min||root.data>max)
		{
			return false;
		}else
		{
			;//System.out.println(root.data);
		}
		printBST( root.left, min, root.data);
		System.out.print(root.data+" ");
		printBST( root.right, root.data, max);
		return true;
		
	}

}
