package basisAlgorithmen;
import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}
	
	/**
	 * Startentfernung initialisieren
	 * @param from Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i=0; i < numv; i++) {
			dist[i] = 9999;		// Summen im Graph dürfen nie mehr ergeben
		}
	}
	
	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten
	 * Setzt dist[]- und pred[]-Arrays, kein Rückgabewert
	 * @param from Startknoten
	 */
	protected boolean calculatePath(int from, int to) {
		VertexHeap heap = new VertexHeap(graph.numVertices());
		dist[from] = 0;
		for (int i=0; i < graph.numVertices(); i++) {
			heap.insert(new Vertex(i, dist[i]));
		}
		
		while (!heap.isEmpty()) {
			Vertex v = heap.remove();
			if (v.vertex == to) {
				return true;
			}
			List<WeightedEdge> edges = graph.getEdges(v.vertex);
			for (WeightedEdge we: edges) {
				int newDistance = v.cost + we.weight;
			// 	int newDistance = dist[v.vertex] + we.weight;
				if (newDistance < dist[we.to_vertex]) {
					dist[we.to_vertex] = newDistance;
					pred[we.to_vertex] = v.vertex;
					heap.setCost(we.to_vertex, newDistance);
				}
			}
		}
		return (dist[to] != 9999);
	}
}
