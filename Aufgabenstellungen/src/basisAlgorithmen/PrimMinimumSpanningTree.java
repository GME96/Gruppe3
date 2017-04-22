package basisAlgorithmen;
import java.util.List;

public class PrimMinimumSpanningTree {
	
	public int[] findMST(Graph g) {
		int numv = g.numVertices();
		int[] pred = new int[numv];
		int[] weight = new int[numv];
		for (int i=0; i < numv; i++) {
			weight[i] = 9999;		// Gewichte im Graph dürfen nie größer sein
			pred[i] = -1;
		}
		
		weight[0] = 0;		// Start bei beliebigem Knoten
		
		// TODO
		
		return null;
	}
}
