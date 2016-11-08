package com.goodlife.model;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
	 
    private Node<T> root;
     
    public Tree() {
        super();
    }
 
    public Node<T> getRoot() {
        return this.root;
    }
 
    public void setRoot(Node<T> rootElement) {
        this.root = rootElement;
    }
     
    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        walk(root, list);
        return list;
    }
     
    public String toString() {
        return toList().toString();
    }
     
    private void walk(Node<T> element, List<Node<T>> list) {
        list.add(element);
        for (Node<T> data : element.getChildren()) {
            walk(data, list);
        }
    }
}
