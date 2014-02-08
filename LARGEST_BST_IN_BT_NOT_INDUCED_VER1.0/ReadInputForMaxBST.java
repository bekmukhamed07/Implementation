/**
 * 
 */


import java.io.File;
import java.util.Scanner;



/**
 * @author Dany
 *
 */
public class ReadInputForMaxBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReadInputForMaxBST().readInputFromFile(“input.txt");
	}

	public void readInputFromFile(String inFile)
	{
		File infile=new File(inFile);
		int arrLen=0;
		int[] postOrder = null;
		int[] inOrder = null;
		String sName="";
		try{
			Scanner scanner=new Scanner(infile);
			while(scanner.hasNext())
			{
				arrLen=scanner.nextInt();
				if(!(Character.isDigit((sName=scanner.next()).charAt(0))))
				{
					if(sName.compareTo("Postorder")==0)
					{
					postOrder=new int[arrLen];
					for(int i=0;i<arrLen;i++)
					{
						postOrder[i]=scanner.nextInt();
					}
					}
				}
				
				if(!(Character.isDigit((sName=scanner.next()).charAt(0))))
				{
					if(sName.compareTo("Inorder")==0)
					{
						inOrder=new int[arrLen];
						for(int j=0;j<arrLen;j++)
						{
							inOrder[j]=scanner.nextInt();
						}
					}
				}
				
				TreeNode root=new CreateTreeGivenPostAndInOrder().createBinaryTree(postOrder, 0, postOrder.length-1, inOrder, 0, inOrder.length-1);
				TreeNodeHelper[] result=LargestBSTNotInducedBackTracking.getLargestBST(root);
				result[0]=result[1]==null?result[0]:(result[0].nodes>result[1].nodes?result[0]:result[1]);
				System.out.println("\n\nRoot Node : "+result[0].node.data+ " 	Minimum: "+result[0].minValue + "	 Maximum:  "+result[0].maxValue);
				System.out.println("INORDER TRAVERSAL OF BST");
				new MaxBSTinBTUtil().printBST(result[0].node, Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
