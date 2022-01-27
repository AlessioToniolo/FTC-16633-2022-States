package org.firstinspires.ftc.teamcode.gspeed;

import java.util.ArrayList;

public class PathBuilder {
    // TODO: comment path builder
    private Graph grid;
    private Point start;
    private Point end;

    // Visited and Frontier
    private ArrayList<Point> visited = new ArrayList<Point>();
    private ArrayList<Path> frontier = new ArrayList<Path>();

    public PathBuilder(Graph g, Point s, Point e) {
        grid = g;
        start = s;
        end = e;
    }

    // A*
    public Path AStar() {
        Path p = new Path();

        if (start.equals(end)) {
            return p;
        }
        p.addPoint(start);
        frontier.add(p);
        while(true) {
            if (frontier.size() < 1) {
                return new Path();
            }
            // Scan Frontier, find index of smallest cost + heuristic; pop it out
            Path current = getSmallest();
            Point last = current.getEnd();
            visited.add(last);
            if (last.equals(end)) {
                return current;
            }
            // Search connected points
            ArrayList<Point> points = grid.getNeighbors(last);
            for (int i=0; i<points.size(); i++) {
                Point node = points.get(i);
                int cost = grid.getValue(node);
                double heuristic = grid.getDistance(node, end);
                // New ArrayList<Point>, new Path
                ArrayList<Point> newP = new ArrayList<Point>();
                for (int j=0; j<current.getPath().size(); j++) {
                    newP.add(current.getPath().get(j));
                }
                newP.add(node);
                Path newPath = new Path(newP, cost, heuristic);

                if (!inFrontier(node) && !inVisited(node)) {
                    frontier.add(newPath);
                }
            }
        }
    }

    private boolean inVisited(Point p) {
        boolean output = false;
        for (int i=0; i< visited.size(); i++) {
            if (visited.get(i).equals(p)) {
                output = true;
            }
        }
        return output;
    }

    private boolean inFrontier(Point p) {
        boolean output = false;
        for (int i=0; i< frontier.size(); i++) {
            if (frontier.get(i).containsPoint(p)) {
                output = true;
            }
        }
        return output;
    }

    private Path getSmallest() {
        double min = Double.POSITIVE_INFINITY;
        int index = 0;
        for (int i=0; i<frontier.size(); i++) {
            Path p = frontier.get(i);
            double cost = p.getCost() + p.getHeuristic();
            if (cost < min) {
                index = i;
                min = cost;
            }
        }
        Path p = frontier.get(index);
        frontier.remove(index);
        return p;
    }

}
