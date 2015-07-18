package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Graph with Nodes and connecting Edges.
 * Initialized without Nodes and Edges, or with a List of both of them.
 * Paths automatically computed for efficiency.
 * Graph assumes edges are one way, to add two way Edges use addDoubleEdges and make sure your List of Edges are two ways. 
 * Graph uses Lists for efficiency, do not add clone Nodes or Edges.
 * @author Philip Bui
 *
 */
public class Graph {
	public List<Node> nodes; // List of all nodes in Graph
	public List<Edge> edges; // List of all edges in Graph
	public Map<Node, List<Edge>> paths = new HashMap<Node, List<Edge>>(); // List of all possible paths for each "key" node.
	public Graph() {
		
	}
	public Graph(List<Node> nodes, List<Edge> edges) {
		this.nodes.addAll(nodes);
		this.edges.addAll(edges);
		computePaths();
	}
	/**
	 * Adds a Node in Graph.
	 * @param name Name of node
	 */
	public void addNode(String name) { 
		Node addMe = new Node(name);
		nodes.add(addMe);
	}
	/**
	 * Adds an one direction Edge connecting two Nodes in Graph.
	 * @param from Predecessor Node 
	 * @param weight Weight of Edge
	 * @param to Destination Node
	 */
	public void addEdge(Node from, int weight, Node to) { // Add edge
		Edge addMe = new Edge(from, weight, to);
		edges.add(addMe);
		addPath(addMe); // Automatically adding possible paths
	}
	/**
	 * Adds a two direction Edge connecting two Nodes in Graph.
	 * @param from First Node
	 * @param weight Weight of Edge
	 * @param to Second Node
	 */
	public void addDoubleEdge(Node from, int weight, Node to) {
		Edge addMe = new Edge(from, weight, to);
		edges.add(addMe);
		addPath(addMe);
		Edge addMe2 = new Edge(to, weight, from);
		edges.add(addMe2);
		addPath(addMe2);
	}
	/**
	 * Adds a Path connecting two Nodes in Graph.
	 * @param path Edge resembling Path 
	 */
	public void addPath(Edge path) { // Add path to existing connected paths for that node
		Node from = path.getFrom();
		List<Edge> existingPaths = paths.get(from);
		existingPaths.add(path);
		paths.put(from, existingPaths);
	}
	/**
	 * Returns all Nodes in Graph.
	 * @return List of all Nodes
	 */
	public List<Node> getNodes() { 
		return nodes;
	}
	/**
	 * Returns all Edges in Graph. 
	 * @return List of all Edges
	 */
	public List<Edge> getEdges() { // Return edges
		return edges;
	}
	/**
	 * Returns all Paths in Graph.
	 * @return Map ("Key Node", List of Edges from Key Node)
	 */
	public Map<Node, List<Edge>> getPaths() { 
		return paths;
	}
	/**
	 * Reads through all Edges, and computes all Paths, sorting them into ("Key Node", List of Edges from Key Node)
	 */
	public void computePaths() {
		for (Edge edge : edges) {
			Node from = edge.getFrom();
			List<Edge> fromEdges;
			if (paths.containsKey(from))
				fromEdges = paths.get(from);
			else
				fromEdges = new ArrayList<Edge>();
			fromEdges.add(edge);
			paths.put(from, fromEdges);
		}
	}
	/**
	 * Returns List of connecting Paths from a Node.
	 * @param from Starting Node
	 * @return List of Edges from that Node.
	 */
	public List<Edge> getAdjacent(Node from) { // Get adjacent paths connecting to this node
		return paths.get(from);
	}
	
}
