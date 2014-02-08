/**
 * 
 */



/**
 * @author Dany
 *
 */
public class CreateTreeGivenPostAndInOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] postOrder={6,1,2,5,9,4,11,10,3,13,12,14,17,16,19,20,18,15,8,7};
		int[] inOrder={1,6,2,4,5,9,7,8,3,10,11,12,13,14,15,16,17,18,19,20};
		TreeNode root=new CreateTreeGivenPostAndInOrder().createBinaryTree(postOrder, 0, postOrder.length-1, inOrder, 0, inOrder.length-1);
		root.inOrderTraversal(root);
	}
	
	public TreeNode createBinaryTree(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd)
	{
	
		if(inEnd<inStart)
			return null;
		int rootIndex=0;

		TreeNode root=new TreeNode(postOrder[postEnd]);
		for(int i=inStart;i<=inEnd;i++)
		{
			if(inOrder[i]==postOrder[postEnd])
			{
				rootIndex=i;
				 break;
			}
			
		}
		int len = rootIndex - inStart;

		root.left=createBinaryTree(postOrder, postStart, postStart+len-1, inOrder, inStart, rootIndex-1);
		root.right=createBinaryTree(postOrder, postStart+len, postEnd-1, inOrder, rootIndex+1, inEnd);
		return root;

	}

}
