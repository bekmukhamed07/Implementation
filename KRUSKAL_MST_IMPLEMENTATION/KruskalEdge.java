
public class KruskalEdge implements Comparable<KruskalEdge> {
    public int u;
    public int v;
    public int weight;

    KruskalEdge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(KruskalEdge o) {
        KruskalEdge e1 = o;
        if (e1.weight == this.weight)
            return 0;
        return e1.weight < this.weight ? 1 : -1;
    }

}
