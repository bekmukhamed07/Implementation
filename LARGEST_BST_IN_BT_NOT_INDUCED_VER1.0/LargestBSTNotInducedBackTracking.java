




//import java.util.HashMap;


/**
 * @author Dany
 *
 */
public class LargestBSTNotInducedBackTracking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Test input 1:
		TreeNode res=new TreeNode(25);
		res.insertBT(res, 20, 25,"left");
		res.insertBT(res, 12, 20,"left");
		res.insertBT(res, 32, 20,"right");
		res.insertBT(res, 47, 25,"right");
		res.insertBT(res, 28, 47,"left");
		res.insertBT(res, 54, 47,"right");
		res.insertBT(res, 46, 54,"left");
		res.insertBT(res, 40, 54,"right");
		res.insertBT(res, 62, 40,"right");
		
		
		//Test input 2
		TreeNode res1=new TreeNode(7);
		res1.insertBT(res1, 4, 7,"left");
		res1.insertBT(res1, 2, 4,"left");
		res1.insertBT(res1, 9, 4,"right");
		res1.insertBT(res1, 1, 2,"left");
		res1.insertBT(res1, 6, 1,"right");
		res1.insertBT(res1, 5, 9,"left");
		res1.insertBT(res1, 8, 7,"right");
		res1.insertBT(res1, 15, 8,"right");
		res1.insertBT(res1, 14, 15,"left");
		
		res1.insertBT(res1, 12, 14,"left");
		res1.insertBT(res1, 3, 12,"left");
		res1.insertBT(res1, 10, 3,"right");
		res1.insertBT(res1, 11, 10,"right");
		res1.insertBT(res1, 13, 12,"right");
		res1.insertBT(res1, 18, 15,"right");
		res1.insertBT(res1, 20, 18,"right");
		res1.insertBT(res1, 19, 20,"left");
		res1.insertBT(res1, 16, 18,"left");
		res1.insertBT(res1, 17, 16,"right");

		
		long inTime=System.currentTimeMillis();
		
		TreeNodeHelper[] result=getLargestBST(res);
		result[0]=result[1]==null?result[0]:(result[0].nodes>result[1].nodes?result[0]:result[1]);
		System.out.println("\nRoot Node : "+result[0].node.data+ " 	Minimum: "+result[0].minValue + "	 Maximum:  "+result[0].maxValue);
		System.out.println("INORDER TRAVERSAL OF BST");
		new MaxBSTinBTUtil().printBST(result[0].node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		result=getLargestBST(res1);
		result[0]=result[1]==null?result[0]:(result[0].nodes>result[1].nodes?result[0]:result[1]);
		System.out.println("\n\nRoot Node : "+result[0].node.data+ " 	Minimum: "+result[0].minValue + "	 Maximum:  "+result[0].maxValue);
		System.out.println("INORDER TRAVERSAL OF BST");
		new MaxBSTinBTUtil().printBST(result[0].node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		
		long pTime=System.currentTimeMillis();
		System.out.println("\nTime in Milli Secs "+(pTime-inTime));

	}
	

	 
	    public static TreeNodeHelper[] getLargestBST(TreeNode root) {
	        
	    	
	    	if (root == null) {
	        	TreeNodeHelper[] beanArray=new TreeNodeHelper[2];
	        	beanArray[0]=new TreeNodeHelper(null, 0, null, null, false);
	            return beanArray;
	        }
	       
	    	if (root.left == null && root.right == null) {
	        	TreeNodeHelper[] beanArray=new TreeNodeHelper[2];
	        	beanArray[0] = new TreeNodeHelper(root, 1, root.data, root.data, true);
	            return beanArray;
	        } else {
	           
	        	//Post order traversal
	        	TreeNodeHelper[] leftBst = getLargestBST(root.left);
	            TreeNodeHelper[] rightBst = getLargestBST(root.right);            
	            
	            //Right tree validation with root
	            if (!(rightBst[0].isBST && rightBst[0].minValue > root.data)) {
	            	if(rightBst[0].node!=null)
	            	{
	            		TreeNode leftTemp=root.left;
	            		root.left=null;
		            int[] countMinMax=new MaxBSTinBTUtil().getCountAndMinMax(root);
					if(countMinMax[0]>1)
					{
						rightBst[0].isBST=true;
						rightBst[0].nodes=countMinMax[0];
						rightBst[0].maxValue=countMinMax[2];
					}else
						rightBst[0].isBST=false;
					root.left=leftTemp;
	            	}
					else
						rightBst[0].isBST=false;
	            }
	 
	            //Left tree validation with root
	            if (!(leftBst[0].isBST && leftBst[0].maxValue < root.data)) {
	            	if(leftBst[0].node!=null)
	            	{
	            		TreeNode rightTemp=root.right;
	            		root.right=null;
	            	int[] countMinMax=new MaxBSTinBTUtil().getCountAndMinMax( root );
	            	if(countMinMax[0]>1)
	            	{
	            		leftBst[0].isBST=true;
	            		leftBst[0].nodes=countMinMax[0];
	            		leftBst[0].minValue=countMinMax[1];

	            	}else
	            		leftBst[0].isBST=false;
            		root.right=rightTemp;

	            	}else
	            		leftBst[0].isBST=false;
	            }
	            
	            
	            //Right and left BST satisfies the BST condition with root
	            if (leftBst[0].isBST && rightBst[0].isBST) { 
	            	
	            	leftBst[0]=new TreeNodeHelper(root, rightBst[0].nodes + leftBst[0].nodes + 1, rightBst[0].maxValue, leftBst[0].minValue, true);
	            	if((leftBst[1]!=null)&&(rightBst[1]!=null)){
	            		leftBst[1]=leftBst[1].nodes>rightBst[1].nodes?leftBst[1]:rightBst[1];
	            	}else if((leftBst[1]!=null)||(rightBst[1]!=null))
	            	{
	            		leftBst[1]=leftBst[1]!=null?leftBst[1]:rightBst[1];
	            	}
	                return leftBst;
	                
	                
	            } else if (root.left == null && rightBst[0].isBST) {
	            	
	                rightBst[0] = new TreeNodeHelper(root, ++rightBst[0].nodes, rightBst[0].maxValue, root.data, true);
	                return rightBst;
	            
	            }else if (root.left == null && (!(rightBst[0].isBST)) ){
	            	
	                TreeNodeHelper bstHelper = new TreeNodeHelper(root, 1, root.data, root.data, true);
	                rightBst[1]=rightBst[1]==null?rightBst[0]:(rightBst[0].nodes>rightBst[1].nodes?rightBst[0]:rightBst[1]);
	                rightBst[0]=bstHelper;
	                return rightBst;
	                
	            }else if (root.right == null && leftBst[0].isBST) {
	            	
	                leftBst[0] = new TreeNodeHelper(root, ++leftBst[0].nodes, root.data, leftBst[0].minValue, true);
	                return leftBst;
	                
	            }else if (root.right == null && (!(leftBst[0].isBST))  ){
	            	
	                TreeNodeHelper bstHelper = new TreeNodeHelper(root, 1, root.data, root.data, true);
	                leftBst[1]=leftBst[1]==null?leftBst[0]:(leftBst[0].nodes>leftBst[1].nodes?leftBst[0]:leftBst[1]);
	                leftBst[0]=bstHelper;
	                return leftBst;
	                
	            }else if ( (leftBst[0].isBST) && (!(rightBst[0].isBST)) ){ 
	            	
	            	if(leftBst[0].nodes > rightBst[0].nodes)
	            	{
	                    leftBst[0] = new TreeNodeHelper(root, ++leftBst[0].nodes, root.data, leftBst[0].minValue, true);
	                    if(leftBst[1]!=null&&rightBst[1]!=null)
	                    {
	                    leftBst[1]=leftBst[1].nodes>rightBst[1].nodes?leftBst[1]:rightBst[1];
	                    }else
	                    {
	                    	leftBst[1]=leftBst[1]!=null?leftBst[1]:rightBst[1];
	                    }
	                    return leftBst;

	            	}else if (leftBst[0].nodes < rightBst[0].nodes)
	            	{
	            		leftBst[0] = new TreeNodeHelper(root, ++leftBst[0].nodes, root.data, leftBst[0].minValue, true);
	            		
	            		if(rightBst[1]==null)
	            		{
	            			
	            			leftBst[1]=leftBst[1]==null?rightBst[0]:(leftBst[1].nodes>rightBst[0].nodes?leftBst[1]:rightBst[0]);
	            			
	            		}
	            		else
	            		{
	                    TreeNodeHelper bstHelper=rightBst[0].nodes>rightBst[1].nodes?rightBst[0]:rightBst[1];
	                    leftBst[1]=leftBst[1]==null?bstHelper:(leftBst[1].nodes>bstHelper.nodes?leftBst[1]:bstHelper);
	            		}

	                    return leftBst;
	            		
	            	}else //if (leftBst[0].nodes == rightBst[0].nodes)
	            	{
	            		leftBst[0] = new TreeNodeHelper(root, ++leftBst[0].nodes, root.data, leftBst[0].minValue, true);
	            		
	            		if((leftBst[1]!=null)&&(rightBst[1]!=null)){
		            		leftBst[1]=leftBst[1].nodes>rightBst[1].nodes?leftBst[1]:rightBst[1];
		            	}else if((leftBst[1]!=null)||(rightBst[1]!=null))
		            	{
		            		leftBst[1]=leftBst[1]!=null?leftBst[1]:rightBst[1];
		            	}
	            		
	                    return leftBst;

	            	}
	            	
	            } else if ( (!(leftBst[0].isBST)) && ((rightBst[0].isBST)) ){ 
	            	
	            	if(leftBst[0].nodes > rightBst[0].nodes)
	            	{
	            		
	            		rightBst[0]=new TreeNodeHelper(root, ++rightBst[0].nodes, rightBst[0].maxValue, root.data, true);
	            		
	            		if(leftBst[1]==null)
	            		{
	            			rightBst[1]=rightBst[1]==null?leftBst[0]:(leftBst[0].nodes>rightBst[1].nodes?leftBst[0]:rightBst[1]);
	            		}else
	            		{
		                    TreeNodeHelper bstHelper=leftBst[0].nodes>leftBst[1].nodes?leftBst[0]:leftBst[1];
		            		rightBst[1]=rightBst[1]==null?bstHelper:(rightBst[1].nodes>bstHelper.nodes?rightBst[1]:bstHelper);

	            			
	            		}
	            		return rightBst;
	            		
	            	}else if (leftBst[0].nodes < rightBst[0].nodes)
	            	{
	            		rightBst[0]=new TreeNodeHelper(root, ++rightBst[0].nodes, rightBst[0].maxValue, root.data, true);
	            		
	            		if(leftBst[1]!=null&&rightBst[1]!=null)
	                    {
	                    rightBst[1]=leftBst[1].nodes>rightBst[1].nodes?leftBst[1]:rightBst[1];
	                    }else
	                    {
	                    	rightBst[1]=rightBst[1]!=null?rightBst[1]:leftBst[1];
	                    }
	            		
	            		return rightBst;

	            		
	            	}else //if (leftBst[0].nodes == rightBst[0].nodes)
	            	{
	            		rightBst[0]=new TreeNodeHelper(root, ++rightBst[0].nodes, rightBst[0].maxValue, root.data, true);
	            		
	            		if((leftBst[1]!=null)&&(rightBst[1]!=null)){
		            		rightBst[1]=leftBst[1].nodes>rightBst[1].nodes?leftBst[1]:rightBst[1];
		            	}else if((leftBst[1]!=null)||(rightBst[1]!=null))
		            	{
		            		rightBst[1]=rightBst[1]!=null?rightBst[1]:leftBst[1];
		            	}
	            		
	            		return rightBst;
	            	}
	            	
	            } else {
	            	
	            	//Start the count from 1
	            	TreeNodeHelper falseHelper= new TreeNodeHelper(root, 1, root.data, root.data, true);
	                TreeNodeHelper bstHelper=rightBst[0].nodes>leftBst[0].nodes?rightBst[0]:leftBst[0];

	                TreeNodeHelper bstHelper1=null;
	                if((leftBst[1]!=null)&&(rightBst[1]!=null)){
	            		 bstHelper1=leftBst[1].nodes>rightBst[1].nodes?leftBst[1]:rightBst[1];
	            	}else if((leftBst[1]!=null)||(rightBst[1]!=null))
	            	{
	            		 bstHelper1=leftBst[1]!=null?leftBst[1]:rightBst[1];
	            	}
            		
	                leftBst[1]=bstHelper1==null?bstHelper:(bstHelper.nodes>bstHelper1.nodes?bstHelper:bstHelper1);
	                leftBst[0]=falseHelper;
	                
            		return leftBst;

	            }
	        }
	    }
	
}
