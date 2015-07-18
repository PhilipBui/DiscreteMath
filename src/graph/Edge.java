package graph;

public class Edge {
	final private Node from;
	final private int weight;
	final private Node to;
	public Edge (Node from, int weight, Node to) {
		this.from = from;
		this.weight = weight;
		this.to = to;
	}
	public Node getFrom() {
		return from;
	}
	public int getWeight() {
		return weight;
	}
	public Node getTo() {
		return to;
	}
	@Override
	public String toString() {
		return from + " => " + to;
	}
}
