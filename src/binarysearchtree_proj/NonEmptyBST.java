package binarysearchtree_proj;

import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
    private T _element;
    private BST<T> _left;
    private BST<T> _right;

    public NonEmptyBST(T element) {

        _left = new EmptyBST<T>();
        _right = new EmptyBST<T>();
        _element = element;
    }

    // TODO: insert
    @Override
    public BST<T> insert(T element) {
        if (this.getElement().compareTo(element) > 0) {
            this._left = this._left.insert(element);
        } else if (this.getElement().compareTo(element) < 0) {
            this._right = this._right.insert(element);
        }
        return this;
    }



    // TODO: remove
    //@Override
    public BST<T> remove(T element) {
        if (this.getElement().compareTo(element) > 0) {
            this._left = this._left.remove(element);
        }
        else if (this.getElement().compareTo(element) < 0) {
            this._right = this._right.remove(element);
        }
        else {
            if (this._right.isEmpty() && this._left.isEmpty()) {
                return new EmptyBST<>();
            }
            else if (this._right.isEmpty()){
                return this._left;
            }
            else if (this._left.isEmpty()){
                return this._right;
            }
            else{
                this._element = this._right.min();
                _right = this._right.remove(_element);
                return this;
            }
        }
        return this;
    }

    public T min() {
        if (this._left.isEmpty()){
            return this.getElement();
        }
        else {
            return this._left.min();
        }
    }

    // TODO: printPreOrderTraversal
    //@Override
    public void printPreOrderTraversal() {
        System.out.print(this._element + " ");
        this._left.printPreOrderTraversal();
        this._right.printPreOrderTraversal();
    }

    // TODO: printPostOrderTraversal
    @Override
    public void printPostOrderTraversal() {
        this._left.printPostOrderTraversal();
        this._right.printPostOrderTraversal();
        System.out.print(this._element + " ");
    }

    // TODO: printBreadthFirstTraversal
    @Override
    public void printBreadthFirstTraversal() {
        Queue<BST> q = new LinkedList<>();
        q.add(this);
        while (!(q.isEmpty())){
            BST node = q.poll();
            System.out.print(node.getElement() + " ");
            if (!(node.getLeft().isEmpty())) {
                q.add(node.getLeft());
            }
            if (!(node.getRight().isEmpty())) {
                q.add(node.getRight());
            }
        }
    }
    // GetHeight of A Tree

    @Override
    public int getHeight() {
        return Math.max(_left.getHeight(), _right.getHeight())+1;
    }


    @Override
    public BST<T> getLeft() {
        return _left;
    }

    @Override
    public BST<T> getRight() {
        return _right;
    }

    @Override
    public T getElement() {
        return _element;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}