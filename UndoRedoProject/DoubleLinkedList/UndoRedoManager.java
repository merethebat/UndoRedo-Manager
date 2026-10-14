package DoubleLinkedList;

// Author: Merethe Batino
// Undo and redo practice using a double linked list.

public class UndoRedoManager<T> {

    // Each node stores one state and links to the states beside it.
    private class Node {
        private T state;
        private Node next;
        private Node previous;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Move back to the previous state.
    public T Undo() {
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }

        if (currentState.previous == null) {
            System.out.println("Reached the initial start state");
            return currentState.state;
        }

        currentState = currentState.previous;
        return currentState.state;
    }

    // Save a new state as the current state.
    public void addState(T newState) {
        Node newNode = new Node(newState);

        // If we add something after undoing, the old redo path is removed.
        if (currentState != null) {
            currentState.next = null;
            newNode.previous = currentState;
            currentState.next = newNode;
        }

        currentState = newNode;
    }

    // Move forward to the next state, if one is available.
    public T Redo() {
        if (currentState == null) {
            System.out.println("No state to redo");
            return null;
        }

        if (currentState.next == null) {
            System.out.println("No newer state to redo");
            return currentState.state;
        }

        currentState = currentState.next;
        return currentState.state;
    }

    // Show the current state without changing it.
    public T getCurrentState() {
        if (currentState == null) {
            return null;
        }
        return currentState.state;
    }

    public static void main(String[] args) {
        UndoRedoManager<String> manager = new UndoRedoManager<>();

        manager.addState("Wake up from bed");
        manager.addState("Brush Teeth");
        manager.addState("Take a Shower");
        manager.addState("Go to Class");

        System.out.println("Current State: " + manager.getCurrentState());

        System.out.println("Undo: " + manager.Undo());
        System.out.println("Undo: " + manager.Undo());

        System.out.println("Redo: " + manager.Redo());
        System.out.println("Redo: " + manager.Redo());

        // This tries to redo when already at the newest state.
        System.out.println("Redo: " + manager.Redo());
    }
}
