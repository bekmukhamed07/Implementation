/**
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;


/**
 * @author Dany
 *
 */
public class Prim1Implementation {

	public static Set<Integer> boolCheck=new HashSet<Integer>();
	public static ArrayList<Edge> heapCheck=new ArrayList<Edge>();
	public static int noOfVertices=0;
	public static int noOfEdges=0;
	PriorityQueue<Edge> minHeap= new GraphMinHeap().implementMinHeapPriorityQueue();

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph graph=new Prim1Implementation().constructGraph("/users/dany/downloads/suma.txt");
		//graph.printGraph();
		//int mst=new Prim1Implementation().implementMST(graph, 1, 0, null);
		int mst=new Prim1Implementation().implementPrim(graph, 1);
		//mst.printGraph();
		System.out.println("Total : "+mst);
	}
	
	public Graph constructGraph(String inFile)
	{
		File infile=new File(inFile);
		int u, v, w;
		Graph graph=null;
		int arrLen=0;
		try {
			Scanner scanner=new Scanner(infile);
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();
			graph=new Graph(noOfVertices);
			
			for(int i=0;i<noOfEdges;i++)
			{
				u=scanner.nextInt();
				v=scanner.nextInt();
				w=scanner.nextInt();

			graph.addEdge(u,v,w);
			graph.addEdge(v,u,w);
			}
			//graph.printGraph();
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graph;
		

	}
	
	public boolean isEdgeInQueue(PriorityQueue<Edge> minHeap,Edge e)
	{
		Iterator<Edge> itr=minHeap.iterator();
		while(itr.hasNext())
		{
			Edge qe=itr.next();
			if((qe.u==e.u&&qe.v==e.v&&qe.w==e.w)||(qe.u==e.v&&qe.v==e.u&&qe.w==e.w))
				return true;
		}
		return false;
	}
	
	public int implementMST(Graph graph, int startNode, int mstGraph, PriorityQueue<Edge> minHeap)
	{
		if(minHeap==null)
			minHeap=new GraphMinHeap().implementMinHeapPriorityQueue();
		//Change it later testing
		/*if(mstGraph==null)
			mstGraph=new Graph(noOfVertices);*/
		boolean qCheck;
		for(int i=1;i<=noOfVertices;i++)
		{
		ArrayList<Edge> adjList=graph.getOutEdges(startNode);

		if(boolCheck.size()==noOfVertices)
		{
			return mstGraph;
		}
		boolCheck.add(startNode);
		
		if(adjList!=null)
		{
		for(Edge o : adjList)
		{
			//qCheck=isEdgeInQueue(minHeap, o);
			//if(!qCheck)
			//{
			minHeap.add(o);
			//}

		}
		}
		
		Edge e=minHeap.poll();
		while(e!=null&&boolCheck.contains(e.u)&&boolCheck.contains(e.v))
		e=minHeap.poll();
		
		if(e!=null&&boolCheck.contains(e.u)&&!boolCheck.contains(e.v))
		{
			startNode=e.v;
			boolCheck.add(e.v);
			mstGraph+=e.w;
		}else if(e!=null&&!boolCheck.contains(e.u)&&boolCheck.contains(e.v))
		{
			startNode=e.u;
			boolCheck.add(e.u);
			mstGraph+=e.w;
		}
		}

		return mstGraph;
	}

	public int implementPrim(Graph graph, int startNode)
	{
		boolCheck.add(startNode);
		int mstWeight=0;
		ArrayList<Edge> adjList=graph.getOutEdges(startNode);
		for(Edge e: adjList)
		{
			minHeap.add(e);
		}
		while(!minHeap.isEmpty())
		{
			Edge oe=minHeap.poll();
			if(boolCheck.contains(oe.u)&&(boolCheck.contains(oe.v)))
				continue;
			mstWeight+=oe.w;
			if(boolCheck.contains(oe.u))
			{
				boolCheck.add(oe.v);
				adjList=graph.getOutEdges(oe.v);

				for(Edge e: adjList)
				{
					minHeap.add(e);
				}
				
			}else
			{
				boolCheck.add(oe.u);
				adjList=graph.getOutEdges(oe.u);
				for(Edge e: adjList)
				{
					minHeap.add(e);
				}

			}
		
		}
		return mstWeight;
	}
	
	/*public int implementMST(Graph graph, int startNode, int mstGraph, PriorityQueue<Edge> minHeap)
	{
		if(minHeap==null)
			minHeap=new GraphMinHeap().implementMinHeapPriorityQueue();
		//Change it later testing
		if(mstGraph==null)
			mstGraph=new Graph(noOfVertices);
		ArrayList<Edge> adjList=graph.getOutEdges(startNode);

		if(boolCheck.size()==noOfVertices)
		{
			return mstGraph;
		}
		boolCheck.add(startNode);
		
		if(adjList!=null)
		{
		for(Edge o : adjList)
		{
			minHeap.add(o);
			System.out.println("u value "+o.u);
			System.out.println("Min Heap size : "+minHeap.size());
			System.out.println("u  :"+o.u+" v  :"+o.v+" w  :"+o.w);

		}
		}
		
		Edge e=minHeap.poll();
		while(e!=null&&boolCheck.contains(e.u)&&boolCheck.contains(e.v))
		e=minHeap.poll();
		
		if(e!=null&&boolCheck.contains(e.u)&&!boolCheck.contains(e.v))
		{
			startNode=e.v;
			boolCheck.add(e.v);
			mstGraph+=e.w;
			System.out.println("v to add  u  :"+e.u+" v  :"+e.v+" w  :"+e.w);

			//mstGraph.addEdge(e.u, e.v, e.w);
		}else if(e!=null&&!boolCheck.contains(e.u)&&boolCheck.contains(e.v))
		{
			startNode=e.u;
			boolCheck.add(e.u);
			//mstGraph.addEdge(e.u, e.v, e.w);
			mstGraph+=e.w;
			System.out.println(" u to add u  :"+e.u+" v  :"+e.v+" w  :"+e.w);


		}

		return implementMST(graph, startNode, mstGraph, minHeap);
	}
*/
}
