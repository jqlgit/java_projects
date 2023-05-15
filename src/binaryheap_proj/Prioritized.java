package binaryheap_proj;

public interface Prioritized<V,P extends Comparable<P>> {
    V getValue();
    P getPriority();
    int compareTo(Prioritized<V, P> other);
}
