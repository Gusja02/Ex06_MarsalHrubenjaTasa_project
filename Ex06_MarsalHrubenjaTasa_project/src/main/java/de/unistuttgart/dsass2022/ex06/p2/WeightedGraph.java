package de.unistuttgart.dsass2022.ex06.p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WeightedGraph<N,E> implements IWeightedGraph<N,E> {

	private int numNodes;
	private int numEdges;
	private HashMap<Integer, ArrayList<IEdge<E>>> adjacencyList;
	
	/**
	 * Initializes an empty graph without nodes and edges.
	 */
	public WeightedGraph() {
		this.numNodes = 0;
		this.numEdges = 0;
		this.adjacencyList = new HashMap<>();
	}
	
	
	public void createFromEdgeList(ArrayList<Integer> list) throws UnsupportedOperationException{
		// TODO:
	}
	

	// exampleList: {3,3, 1,2,1, 1,3,2, 2,3,2, 3,1,3}
	public ArrayList<Integer> toEdgeList(){
		ArrayList<Integer> edgeList = new ArrayList<>();
		
		edgeList.add(numberOfNodes());	
		edgeList.add(numberOfEdges());

		for (Map.Entry<Integer, ArrayList<IEdge<E>>> index : adjacencyList.entrySet()) {
			int source = index.getKey();
			boolean flag = true;

			for (IEdge<E> value : index.getValue()) {
				if (flag) {				// only every other iteration
					edgeList.add(source);			
				}
				edgeList.add(value.getSource());	// (source1, destination1, weight1, source2, destination2, ...)
				flag = !flag;
			}
		}
		return edgeList;
	}
	
	public void createFromNodeList(ArrayList<Integer> list) throws UnsupportedOperationException{
		// TODO:
	}
	
	public ArrayList<Integer> toNodeList(){
		// TODO:
		return null;
	}
	
	@Override
	public int numberOfNodes() {
		return this.numNodes;
	}

	@Override
	public int numberOfEdges() {
		return this.numEdges;
	}
	
	/**
	 * Adds a new node with the specified meta data to this graph.
	 * @param nodeMetaData	meta data of the added node
	 * @return the new node's index
	 */
	public int addNode(int nodeID ,N nodeMetaData){
		this.adjacencyList.put(nodeID, new ArrayList<>(1));
		return this.numNodes++;
	}
	
	/**
	 * Adds a new edge from node index <tt>src</tt> to node index <tt>dst</tt>.
	 * @param src index of the source node
	 * @param dst index of the destination node
	 * @param weight weight of the edge
	 * @return the created edge object.
	 */
	public Edge<E> addEdge(int src, int dst, double weight){
		Edge<E> toAdd = new Edge<>(src, dst, weight);
		this.addEdge(toAdd);
		return toAdd;
	}

	/**
	 * Adds the specified edge to this graph.
	 * @param edge	the edge that should be added
	 */
	public void addEdge(IEdge<E> edge) {
		int source = edge.getSource();
		this.adjacencyList.get(source).add(edge);
		this.numEdges++;
	}
	
	@Override
	public Iterator<IEdge<E>> edgeIterator() {
		ArrayList<IEdge<E>> edgeList = new ArrayList<IEdge<E>>(numEdges);
		for (int i = 0; i < this.numNodes; i++) {
			edgeList.addAll(this.adjacencyList.get(i));
		}
		return edgeList.iterator();
	}

	@Override
	public Iterator<IEdge<E>> outgoingEdges(int src) {
		return this.adjacencyList.get(src).iterator();
	}

}
