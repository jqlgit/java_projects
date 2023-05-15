package binaryheap_proj;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private ArrayList<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<Prioritized<V,P>>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (int i=0; i < initialEntries.length; i++){
            _heap.add(initialEntries[i]);
        }
        for (int i = ((_heap.size()-1)/2); i>=0; i--){
            bubbleUp(i);
        }
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient <V,P> mE = new Patient<>(value, priority);
        _heap.add(_heap.size(), mE);
        bubbleUp(_heap.size()-1);
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient mE = new Patient<>(value);
        _heap.add(_heap.size(), mE);
    }

    private void bubbleUp(int i) {
        if (i == 0) {}
        else {
            Prioritized child = _heap.get(i);
            Prioritized parent = _heap.get((i-1)/2);
            if (child.getPriority().compareTo(parent.getPriority()) > 0){
                _heap.set((i-1)/2, child);
                _heap.set(i, parent);
                bubbleUp((i-1)/2);
            }
        }
    }




    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.size()==0){
            return null;
        }
        else if (_heap.size()==1){
            return (V) _heap.remove(0).getValue();
        }
        else {
            Prioritized<V,P> fin = _heap.get(0);
            _heap.set(0, _heap.get(_heap.size()-1));
            _heap.remove(_heap.size()-1);
            bubbleDown(0);
            return fin.getValue();
        }
    }

    private void bubbleDown(int i){
        Prioritized<V,P> parent = _heap.get(i);
        if(!hasLeftChild(i) && !hasRightChild(i)){
            return;
        }
        else if (!hasRightChild(i)){
            Prioritized <V, P> leftChild = _heap.get(leftChildInd(i));
            if (leftChild.getPriority().compareTo(parent.getPriority())>0){
                _heap.set(i, leftChild);
                _heap.set(leftChildInd(i), parent);
                bubbleDown(leftChildInd(i));
            }
            else{ return;}
        }
        else{
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(i));
            Prioritized<V,P> rightChild = _heap.get(rightChildInd(i));
            if (((leftChild.getPriority().compareTo(parent.getPriority()) > 0) || (rightChild.getPriority().compareTo(parent.getPriority())> 0))){
                if(leftChild.getPriority().compareTo(rightChild.getPriority()) > 0){
                    _heap.set(i, leftChild);
                    _heap.set(leftChildInd(i), parent);
                    bubbleDown(leftChildInd(i));
                }
                else{
                    _heap.set(i, rightChild);
                    _heap.set(rightChildInd(i), parent);
                    bubbleDown(rightChildInd(i));
                }
            }
            else {return;}
        }
    }

    // TODO: getMax
    @Override
    public V getMax() {
        if(_heap.size() == 0){return null;}
        else if (_heap.size() == 1){return _heap.get(0).getValue();}
        else{
            int maxIndex = 0;
            P maxPriority = _heap.get(0).getPriority();
            for(int count = 0; count < _heap.size(); count++){
                if ((Integer)_heap.get(count).getPriority() > (Integer) maxPriority){
                    maxPriority = _heap.get(count).getPriority();
                    maxIndex = count;
                }
            }
            return _heap.get(maxIndex).getValue();
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    boolean hasLeftChild(int i){
        return(validIndex(leftChildInd(i)));
    }

    boolean hasRightChild(int i){
        return(validIndex(rightChildInd(i)));
    }

    static int leftChildInd(int i){
        return (2 * i + 1);
    }

    static int rightChildInd(int i){
        return (2 * i + 2);
    }

    static int parentInd(int i){
        return ((i-1)/2);
    }

    boolean validIndex(int i) {
        if ((i >= 0) && (i <= _heap.size() - 1)) {
            return true;
        } else {
            return false;
        }
    }
}