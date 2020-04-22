/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.gpes.graph;

import java.util.Objects;

/**
 *
 * @author juan
 */
class Edge {

    private Node source;
    private Node target;
    private double weight;

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    public Node getFirst() {
        return source;
    }

    public void setFirst(Node source) {
        this.source = source;
    }

    public Node getSecond() {
        return target;
    }

    public void setSecond(Node target) {
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.source);
        hash = 11 * hash + Objects.hashCode(this.target);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Edge{" + "source=" + source + ", target=" + target + ", weight=" + weight + '}';
    }

}
