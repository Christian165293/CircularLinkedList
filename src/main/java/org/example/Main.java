package org.example;

// Node class represents an element in a circular linked list
class Node {
    int data; // Value stored in the node
    Node next; // Pointer to the next node

    // Constructor to initialize a node with data
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// CircularLinkedList class represents a circular linked list
class CircularLinkedList {
    Node last; // Pointer to the last node in the list

    // Constructor initializes the list with a given last node
    public CircularLinkedList(Node last) {
        this.last = last;
        if (this.last != null) {
            this.last.next = last;
        }
    }

    // Method to add a node with given data at the end of the list
    public void addToEnd(int data) {
        Node n = new Node(data);
        if (last == null) { // If the list is empty
            n.next = n;
        } else {
            n.next = last.next;
            last.next = n;
        }
        last = n; // Update last pointer
    }

    // Method to add a node with given data at the beginning of the list
    public void addToStart(int data) {
        Node n = new Node(data);
        if (last == null) { // If the list is empty
            n.next = n;
            last = n;
        } else {
            n.next = last.next;
            last.next = n;
        }
    }

    // Method to add a node with given data after a specific node
    public void addAfter(int insertAfter, int data) {
        if (last != null) {
            Node current = last;
            do {
                if (current.data == insertAfter) {
                    Node n = new Node(data);
                    n.next = current.next;
                    current.next = n;
                    break;
                }
                current = current.next;
            } while (current != last);
        }
    }

    // Method to delete the last node in the list
    public void deleteLast() {
        if (last == null || last.next == last) { // If the list is empty or has only one node
            last = null;
            return;
        }
        Node prev = last.next;
        while (prev.next != last) { // Traverse to find the second last node
            prev = prev.next;
        }
        prev.next = last.next; // Update last pointer
        last = prev;
    }

    // Method to delete the first node in the list
    public void deleteStart() {
        if (last == null || last.next == last) { // If the list is empty or has only one node
            last = null;
            return;
        }
        last.next = last.next.next; // Update the head node
    }

    // Method to delete the node after a given node
    public void deleteAfter(int data) {
        if (last == null) return;
        Node toDelete;
        Node current = last;
        do {
            if (current.data == data) {
                toDelete = current.next;
                if (current.next == current) { // If only one node exists
                    last = null;
                } else {
                    current.next = toDelete.next;
                    if (toDelete == last) last = current;
                }
                break;
            }
            current = current.next;
        } while (current != last);
    }
}


public class Main {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList(null);

        // Adding nodes to the list
        list.addToEnd(10);
        list.addToEnd(20);
        list.addToEnd(30);
        System.out.println("List after adding 10, 20, 30:");
        printList(list);

        // Adding a node at the start
        list.addToStart(5);
        System.out.println("List after adding 5 to start:");
        printList(list);

        // Adding a node after a specific value
        list.addAfter(20, 25);
        System.out.println("List after adding 25 after 20:");
        printList(list);

        // Deleting the last node
        list.deleteLast();
        System.out.println("List after deleting last node:");
        printList(list);

        // Deleting the first node
        list.deleteStart();
        System.out.println("List after deleting start node:");
        printList(list);

        // Deleting a node after a specific value
        list.deleteAfter(10);
        System.out.println("List after deleting node after 10:");
        printList(list);
    }

    // Method to print the circular linked list
    public static void printList(CircularLinkedList list) {
        if (list.last == null) {
            System.out.println("List is empty");
            return;
        }
        Node current = list.last.next;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != list.last.next);
        System.out.println("(back to start)");
    }
}