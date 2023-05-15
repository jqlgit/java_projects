package avltree_proj;
import java.util.Queue;
import java.util.LinkedList;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _size, _height;
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }
    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        if(this._right.isEmpty()) { return this;}
        AVLTree<T> RR = this._right;
        AVLTree<T> RL = RR._left;
        RR._left = this;
        this._right = RL;
        this._size = this._left.size() + this._right.size() +1;
        this._height = Math.max(this._left.height(), this._right.height()) + 1;
        RR._size = RR._left.size() + RR._right.size() + 1;
        RR._height = Math.max(RR._left.height(), RR._right.height()) + 1;
        return RR;
    }


    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateRight() {
        if(this._left.isEmpty()) { return this;}
        AVLTree<T> LL = this._left;
        AVLTree<T> LR = LL._left;
        LL._left = this;
        this._right = LR;
        this._size = this._left.size() +this._right.size() +1;
        this._height = Math.max(this._left.height(), this._right.height()) + 1;
        LL._size = LL._left.size() +LL._right.size() + 1;
        LL._height = Math.max(LL._left.height(), LL._right.height()) + 1;
        return LL;
    }


    @Override
    public boolean isEmpty() {return size() == 0;}
    @Override
    public int height() {return _height;}
    @Override
    public int size() {return _size;}
    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (element == null) {return this;}
        if (this.isEmpty()) {
            this._value = element;
            this._size = 1;
            this._height = 0;
            this._left = new AVLTree<>();
            this._right = new AVLTree<>();
            return this;
        } else if (this._value.compareTo(element) > 0) {
            this._left = (AVLTree<T>) this._left.insert(element);
        } else if(this._value.compareTo(element) < 0) {
            this._right = (AVLTree<T>) this._right.insert(element);
        } else{
            return this;
        }
        this._size = this._left.size() + this._right.size() + 1;
        this._height = Math.max(this._left.height(), this._right.height()) + 1;
        return this.balance();
    }




    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (this.isEmpty()) {return this;}
        if (element.compareTo(_value) < 0) {
            if(!this._left._left.isEmpty()) {
                this._left = (AVLTree<T>) this._left.remove(element);
                this._size = this._left.size() + this._right.size() + 1;
                this._height = Math.max(this._left.height(), this._right.height()) + 1;
                return this.balance();
            }
        }
        else if (element.compareTo(_value) > 0) {
            if (!this._left._left.isEmpty()) {
                this._right = (AVLTree<T>) this._right.remove(element);
                this._size = this._left.size() + this._right.size() + 1;
                this._height = Math.max(this._left.height(), this._right.height()) + 1;
                return this.balance();
            }
        }
        else {
            if (this._left.isEmpty() && this._right.isEmpty()) {return new AVLTree<T>();}
            else if ((!this._left.isEmpty()) && (!this._right.isEmpty())) {
                AVLTree<T> temp = this._right;
                while(!temp._left.isEmpty()) {
                    temp = temp._left;
                }
                this._value = temp.getValue();
                if (!this._right._right.isEmpty()) {
                    this._right = (AVLTree<T>)this._right.remove(temp.getValue()); }
                this._size = this._left.size() +this._right.size() + 1;
                this._height = Math.max(this._left.height(),this._right.height()) + 1;
                return this.balance();
            }
            else if (this._left.isEmpty() && !this._right.isEmpty()) {return this._right;}
            else if (this._right.isEmpty() && !this._left.isEmpty()) { return this._left;}
        }
        return this;
    }

    private AVLTree<T> balance() {
        int left_height = this._left.height();
        int right_height =this._right.height();
        if ((left_height-right_height) > 1){
            if(this._left._right.height() > this._left._left.height()){
                this._left =this._left.rotateLeft();
            }
            return this.rotateRight();
        }
        else if ((right_height-left_height) > 1){
            if(this._right._left.height() > this._right._right.height()){
                this._right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }
        return this;
    }


    @Override
    public T findMin() {
        if(this.isEmpty()) {return null;}
        AVLTree<T> newTree = this;
        T min =this.getValue();
        while(!newTree._left.isEmpty()) {
            newTree = newTree._left;
            min =newTree.getValue();
        }
        return min;
    }


    @Override
    public T findMax() {
        if(this.isEmpty()) {return null;}
        AVLTree<T> newTree = this;
        T max = this.getValue();
        while(!newTree._right.isEmpty()) {
            newTree = newTree._right;
            max = newTree.getValue();
        }
        return max;
    }


    @Override
    public boolean contains(T element) {
        AVLTree<T> newTree = this;
        if (element ==null || this.isEmpty()) {return false;}
        while(!newTree.isEmpty()){
            int comp = newTree.getValue().compareTo(element);
            if (comp < 0) {
                newTree =(AVLTree<T>)newTree.getRight();
            }
            else if (comp > 0){
                newTree =(AVLTree<T>)newTree.getLeft();
            }
            else {return true;}
        } return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }


    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }
    // Your code for public methods here.
    public void printBreadth() {
        Queue<AVLTree<T>> queue = new LinkedList<>();
        queue.add(this);
        while (!(queue.size() == 0)) {
            AVLTree<T> curr = queue.poll();
            System.out.print(curr.getValue() + " ");
            if(curr._left != null) queue.add(curr._left);
            if(curr._right != null) queue.add(curr._right);
        }
    }

}
