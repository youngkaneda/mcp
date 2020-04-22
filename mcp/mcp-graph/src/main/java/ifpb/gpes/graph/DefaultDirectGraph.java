package ifpb.gpes.graph;

import ifpb.gpes.Call;

import java.util.*;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author juan
 */
public class DefaultDirectGraph implements Graph<Node,Double> {

    private final DefaultDirectedWeightedGraph<Node,DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    private final Stack<Node> nodes = new Stack<>();

    @Override
    public Matrix toMatrix() {
        return new AdapterMatrix(graph)
            .get();
    }

    public void buildNode(Call call) {
        Node firstnode = new Node();
        firstnode.setClassName(call.getClassType());
        firstnode.setMethodName(call.getMethodName());
        firstnode.setReturnType(call.getReturnType());
        firstnode.setInvokedBy(call.getInvokedBy());
        addNodeAsVertix(firstnode);

        if (isNotNullCallMethod(call)) {
            Node get = nodes.pop();
            addNodeAsVertix(get);
            updateNodesToGraph(firstnode,get);

            if (isInvokedByMethod(call)) {
                nodes.push(firstnode);
            } else {
                Node second = nodes.pop();
                addNodeAsVertix(second);
                updateNodesToGraph(second,firstnode);
            }
        } else {
            Node secondnode = new Node();
            secondnode.setClassName(call.getCalledInClass());
            secondnode.setMethodName(call.getCalledInMethod());
            secondnode.setReturnType(call.getCalledInMethodReturnType());
            nodes.push(secondnode);
            nodes.push(firstnode);
        }
    }

    @Override
    public List<Call> getCandidates() {
        List<Node> sources = graph.vertexSet().stream().filter((n) -> graph.incomingEdgesOf(n).isEmpty()).collect(Collectors.toList());
        List<Node> leafs = graph.vertexSet().stream().filter((n) -> graph.outgoingEdgesOf(n).isEmpty()).collect(Collectors.toList());
        List<Call> mountedCalls = new ArrayList<>();
        //
        sources.parallelStream().forEach(s -> {
            leafs.parallelStream().forEach(l -> {
                DijkstraShortestPath dijk = new DijkstraShortestPath(graph);
                GraphPath<Node,DefaultWeightedEdge> shortestPath = dijk.getPath(s,l);
                if (shortestPath != null) {
                    Call mountedCall = mountCall(shortestPath.getStartVertex(),shortestPath.getEndVertex());
                    if(!isInvokedByThis(mountedCall.getInvokedBy()))
                        mountedCalls.add(mountedCall);
                }
            });
        });
        return mountedCalls;
    }

    private Call mountCall(Node start,Node end) {
        return Call.of(end.getClassName(),end.getMethodName(),end.getReturnType(),
                       start.getClassName(),start.getMethodName(),start.getReturnType(),null,end.getInvokedBy());
    }

    @Override
    public Set<Node> vertex() {
        return graph.vertexSet();
    }

    @Override
    public Double edge(Node source,Node target) {
        if (isConnected(source,target)) {
            DefaultWeightedEdge edge = graph.getEdge(source,target);
            return graph.getEdgeWeight(edge);
        }
        return 0d;
    }

    private boolean isInvokedByThis(String invokedBy) {
        if (!invokedBy.contains("."))
            return true;
        String[] strs = invokedBy.split("\\.");
        if (strs.length >= 2 && strs[0].equals("this"))
            return true;
        return false;
    }

    private boolean isInvokedByMethod(Call call) {
        String invokedBy = call.getInvokedBy();
        if (invokedBy == null || invokedBy.equals("this")) {
            return false;
        }
        return invokedBy.endsWith(")") && !invokedBy.contains("new");
    }

    private void addNodeAsVertix(Node node) {
        if (!graph.containsVertex(node)) {
            graph.addVertex(node);
        }
    }

    private void updateNodesToGraph(Node first,Node second) {
        if (!graph.containsEdge(first,second)) {
            DefaultWeightedEdge addEdge = graph.addEdge(first,second);
            graph.setEdgeWeight(addEdge,1);
        } else {
            DefaultWeightedEdge edge = graph.getEdge(first,second);
            graph.setEdgeWeight(edge,graph.getEdgeWeight(edge) + 1);
        }
    }

    private boolean isNotNullCallMethod(Call call) {
        return call.getCallMethod() != null && !"null".equals(call.getCallMethod().trim());
    }

    @Override
    public boolean isConnected(Node source,Node target) {
        return graph.getEdge(source,target) != null;
    }
}
