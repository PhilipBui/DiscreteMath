package graph;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Extension of Graph by Philip Bui.
 * Contains methods to compute Dijkstra's Algorithm, along with getting steps to complete Dijkstra, and minimum routes and distances to each Node in Graph.
 * @author Philip Bui
 *
 */
public class Dijkstra extends Graph{
	public Dijkstra (Graph Graph) {
		super.edges = Graph.getEdges();
		super.nodes = Graph.getNodes();
		super.paths = Graph.getPaths();
	}
	public Dijkstra(List<Edge> edges, List<Node> Nodes) {
		super.edges = edges;
		super.nodes = Nodes;
		super.computePaths();
	}
	public Map<Node, Map<Node, List<Edge>>> minRoutes; // Variable to hold routes to each Node starting from Node "key" 
	public Map<Node, Map<Node, Integer>> minDistances; // Variable to hold distances to each Node starting from Node "key"
	public Map<Node, List<Edge>> steps; // Variable to hold steps to fulfilling Dijkstra's algorithm from Node "key"
	public void init() {
		this.steps = new HashMap<Node, List<Edge>>();
		this.minRoutes = new HashMap<Node, Map<Node,List<Edge>>>();
		this.minDistances = new HashMap<Node, Map<Node, Integer>>();
	}
	/**
	 * Computes Minimum Routes and Minimum Distances to each Node from the Starting Node using Dijkstra's Algorithm.
	 * Stores steps to complete Dijkstra's Algorithm. 
	 * @param start Starting Node
	 */
	public void computeDijkstra(Node start) {
		Map <Node, List<Edge>> minRoute = new HashMap<Node, List<Edge>>();
		minRoute.put(start, null); // Variable to hold Dijkstra's routes to each Node starting from Node "start".
		Map <Node, Integer> minDistance = new HashMap<Node, Integer>();
		minDistance.put(start, 0); // Variable to hold Dijkstra's distances to each Node starting from Node "start".
		List<Edge> step = new ArrayList<Edge>(); // Variable to hold steps to fulfilling Dijkstra.
		List<Node> visited = new ArrayList<Node>();
		visited.add(start); // Variable to hold Nodes we have already visited, to prevent us visiting them again.
		boolean finished = false;
		while (!finished) {
			Edge nextPath = new Edge (null, 0, null);
			int minWeight = 99999;
			for (Node visitedNode: visited) { // Check all visited Nodes for Dijkstra's next possible destination
				int nodeWeight = minDistance.get(visitedNode); // Get minimum distance to that node
				List<Edge> possiblePaths = super.getAdjacent(visitedNode); // Get Paths from that node
				for (Edge path: possiblePaths) { // Every Path from that node
					Node possibleDestination = path.getTo(); // Check next possible destination
					int nodeAndPathWeight = nodeWeight + path.getWeight(); // Distance to next destination is existing weight + Path weight
					if (!visited.contains(possibleDestination) && nodeAndPathWeight < minWeight) { // If not visited and less than minimum weight
						minWeight = nodeAndPathWeight; // Change minimum weight found
						nextPath = path; // Change path accordingly
					}
				}
			}
			Node predecessorDestination = nextPath.getFrom();
			Node nextDestination = nextPath.getTo();
			if (nextDestination == null)
				throw new IllegalArgumentException("Make sure each Node is connected to at least one other Node.");
			step.add(nextPath); // Add in next step to finished Dijkstra's Algorithm
			List<Edge> routeToNextDestination = minRoute.get(predecessorDestination); // Get List of all Edges leading to predecessor of next Node
			routeToNextDestination.add(nextPath); // Add an Edge leading from predecessor to next Node
			minRoute.put(nextDestination, routeToNextDestination); // Add next Node as key, and the minimum route (List of Edges) to reach next Node
			minDistance.put(nextDestination, minWeight); // Add next Node as key, and the minimum distance to reach that node
			visited.add(nextDestination); // Add next Node to visited
			if (visited.size() == super.nodes.size()) // If visited Nodes equals to amount of all Nodes in Graph
				finished = true;
		}
		this.minRoutes.put(start, minRoute);
		this.minDistances.put(start, minDistance);
		this.steps.put(start, step);
	}
}
	