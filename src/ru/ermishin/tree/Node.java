package ru.ermishin.tree;

public class Node {
    private Integer value;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node() {
        this.value = null;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setValue(int newValue) {
        if (value == null) this.value = newValue;
        else if (value < newValue) {
            if (rightChild == null) {
                rightChild = new Node();
                rightChild.setParent(this);
            }
            rightChild.setValue(newValue);
        }
        else {
            if (leftChild == null) {
                leftChild = new Node();
                leftChild.setParent(this);
            }
            leftChild.setValue(newValue);
        }
    }

    // Поиск минимального значения (для удаления узла с двумя потомками)
    private Node findMin() {
        if (leftChild != null) {
            return leftChild.findMin();
        }
        return this;
    }

    // Замена текущего узла на другой узел
    private void replaceWith(Node node) {
        if (parent != null) {
            if (this == this.parent.leftChild) {
                this.parent.leftChild = node;
            } else if (this == this.parent.rightChild) {
                this.parent.rightChild = node;
            }
        }
        if (node != null) node.parent = this.parent;
        this.parent = null;
        this.value = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void deleteValue(int delValue) {
        if (value == delValue) {
            if (rightChild != null && leftChild != null) {
                Integer valMinN = rightChild.findMin().getValue();
                this.value = valMinN; // Замением занчение удаляемого узла на min значение правого поддерева
                deleteValue(valMinN);
            }
            else if (leftChild != null) replaceWith(leftChild);
            else if (rightChild != null) replaceWith(rightChild);
            else replaceWith(null);
        }
        else if (value < delValue) {
            if (rightChild != null) {
                rightChild.deleteValue(delValue);
                return;
            }
        }
        else {
            if (leftChild != null) {
                leftChild.deleteValue(delValue);
                return;
            }
        }
    }

    public boolean containsValue(int searchValue) {
        if (value == null) return false;
        if (searchValue == value) return true;
        else if (searchValue < value) return leftChild != null && leftChild.containsValue(searchValue);
        else return rightChild != null && rightChild.containsValue(searchValue);
    }

    public String toString() {
        if (leftChild == null && rightChild == null) return "" + value;
        else if (leftChild == null) return value + " " + rightChild;
        else if (rightChild == null) return leftChild + " " + value;
        else return leftChild + " " + value +  " " + rightChild;
    }
}
