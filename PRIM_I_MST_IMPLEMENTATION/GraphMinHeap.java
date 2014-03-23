/**
 * 
 */
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * @author Dany
 *
 */
public class GraphMinHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PriorityQueue<Edge> queue=new GraphMinHeap().implementMinHeapPriorityQueue();
		
		 queue.add(new Edge(5, 10, 20));
		 queue.add(new Edge(10, 15, 57));
		 // .add() and .offer() are the same.
		 queue.offer(new Edge(5, 13, 70));
		 queue.offer(new Edge(13, 10, 50));
		 
		 System.out.println("Countries in database: " + queue.size());
		 
		 while (!queue.isEmpty())
		 { 
			 Edge node=queue.poll();
			 System.out.println("u : "+node.u+" v : "+node.v+" w : "+node.w);
			 
		 } 
		 
		 System.out.println("Countries in database: " + queue.size());
		 
		
	}
	
	public PriorityQueue<Edge> implementMinHeapPriorityQueue()
	{
		 PriorityQueue<Edge> queue = new PriorityQueue<Edge>(11, new Comparator<Edge>()
		 {
			 public int compare(Edge o1, Edge o2)
			 {
				 int w1=o1.w;
				 int w2=o2.w;
				 return (w1 - w2); 
				 }
			 }
		 );
		 return queue;
		 
	
	
	}

}
