package com.ai.project;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<Node> openList = new LinkedList<>();
        Queue<Node> closedList = new LinkedList<>();

        // Create root
        Node root = new Node(null, new ChessBoard(
                new int[] {1, 8, 2,
                           0, 4, 3,
                           7, 6, 5}
        ));

        //Node root = new Node(null, new ChessBoard());
        openList.add(root);
        boolean goalFound = false;
        int count = 0;
        Node solution = null;

        System.out.println("Searching for solution...");

        while(!openList.isEmpty() && !goalFound) {
            Node currentNode =  openList.remove();
            closedList.add(currentNode);

            currentNode.moveToAllDirections();

            for (Node child : currentNode.getChildren()) {
                if (child.getChessBoard().isGoal()) {
                    System.out.println("Goal found.");
                    solution = child;

                    goalFound = true;
                }

                if(!openList.contains(child) &&
                        !closedList.contains(child)) {
                    openList.add(child);
                }
            }
            count++;
        }

        System.out.println("solution is:\n" + solution.getChessBoard().toString());

        System.out.println("printing solution path");
        printSolutionPath(solution);
    }

    private static void printSolutionPath(Node solution) {
        List<String> paths = new ArrayList<>();
        do {
            paths.add(solution.getChessBoard().toString());

            solution = solution.getParent();
        } while (solution != null);

        Collections.reverse(paths);

        String output = String.join("\n---\n", paths);

        System.out.println(output);
    }

}
