package basisAlgorithmen;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Graph g = new ListGraph(8, false);
		g.addEdge(0, 4,  3);
		g.addEdge(0, 5,  4);
		g.addEdge(1, 3,  1);
		g.addEdge(1, 4,  6);
		g.addEdge(1, 6,  2);
		g.addEdge(2, 3,  3);
		g.addEdge(2, 4,  4);
		g.addEdge(2, 7,  4);
		g.addEdge(3, 6,  2);
		g.addEdge(3, 7,  1);
		g.addEdge(5, 6,  3);

//		System.out.println(g.hasEdge(3, 1));

		/* Wegsuche */
//		DepthFirstSearch dfs = new DepthFirstSearch(g);
//		BreadthFirstSearch dfs = new BreadthFirstSearch(g);
//		DijkstraDGShortestPath dfs = new DijkstraDGShortestPath(g);
		DijkstraPQShortestPath dfs = new DijkstraPQShortestPath(g);
		List<Integer> way = dfs.findWay(0, 7);
		printWay(way);

		/* Minimaler Spannbaum */
//		PrimMinimumSpanningTree pmst = new PrimMinimumSpanningTree();
//		int[] mst = pmst.findMST(g);
//		for (int i=0; i < mst.length; i++) {
//			if (mst[i] != -1)
//				System.out.println(i + " -> " + mst[i]);
//		}
	}
	
	public static void printWay(List<Integer> way) {
		if (way == null) {
			System.out.println("Kein Weg gefunden.");
			return;
		}
		for (int i=0; i < way.size(); i++) {
			if (i != 0)
				System.out.print(" -> ");
			System.out.print(way.get(i));
		}
	}
}
