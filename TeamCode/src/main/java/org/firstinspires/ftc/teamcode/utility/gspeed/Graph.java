package org.firstinspires.ftc.teamcode.gspeed;

import java.util.ArrayList;

public class Graph {
    // TODO: COLUMN AND ROWS ARE SWITCHED

    // Constructor
    public Graph() { }

    private int[][] grid = {
      new int[]{1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1},
      new int[]{1, 1, 1, 1, 1, 1}
    };


    // Accessor for the grid
    public int[][] getGrid() {
        return grid;
    }
    // Accessor for the value of a point
    public int getValue(Point p) {
        return grid[p.getRow()][p.getRow()];
    }
    // Modifier for setting the value of a point
    public void setValue(Point p, int v) {
        grid[p.getRow()][p.getColumn()] = v;
    }

    // Printer
    public String printString() {
        String output = "";

        for (int r=0; r<grid.length; r++) {
            String line = "";
            for (int c=0; c<grid[0].length; c++) {
                line += String.valueOf(grid[r][c]);
            }
            output += line + "\n";
        }
        return output;
    }

    // Printer for a visual grid
    public String printPathFinder(Path path) {
        String[][] output = new String[6][6];
        for (int i=0; i< grid.length; i++) {
            for (int z=0; z<grid[i].length; z++) {
                output[i][z] = String.valueOf(grid[i][z]);
                //output += " ";
            }
            //output += "\n";
        }

        for (int l=0; l<path.getPath().size(); l++) {
            int column = path.getPath().get(l).getColumn();
            int row = path.getPath().get(l).getRow();
            output[row][column] = "*";
        }

        String out = "\n";
        for (int b=0; b<output.length; b++) {
            for (int d=0; d<output[b].length; d++) {
                out += output[b][d] + "\t";
            }
            out += "\n";
        }
        return out;
    }

    // Retrieves the non-diagonal
    public ArrayList<Point> getNeighbors(Point p) {
            Point up = new Point(p.getColumn()-1, p.getRow());
            Point down = new Point(p.getColumn()+1, p.getRow());
            Point left = new Point(p.getColumn(), p.getRow()-1);
            Point right = new Point(p.getColumn(), p.getRow()+1);
            Point upleft = new Point(p.getRow()-1, p.getColumn()-1);
            Point downleft = new Point(p.getRow()+1, p.getColumn()-1);
            Point upright = new Point(p.getRow()-1, p.getColumn()+1);
            Point downright = new Point(p.getRow()+1, p.getColumn()+1);

            ArrayList<Point> out = new ArrayList<Point>();
            Point [] n = {up, down, left, right, upleft, downleft, upright, downright};
            for (int i=0; i < 8; i++) {
                if (isValid(n[i])) {
                    out.add(n[i]);
                }
            }
            return out;
    }

    // Checks to see if a specified point is on the grid
    public boolean isValid(Point p) {
        boolean left = p.getColumn() > -1;
        boolean right = p.getColumn() < 6;
        boolean up = p.getRow() > -1;
        boolean down = p.getRow() < 6;
        boolean upleft = p.getRow() > -1 && p.getColumn() > -1;
        boolean downleft = p.getRow() < 6 && p.getColumn() > -1;
        boolean upright = p.getRow() > -1 && p.getColumn() < 6;
        boolean downright = p.getRow() < 6 && p.getColumn() < 6;
        return up && down && left && right && upleft && downleft && upright && downright;
    }

    // Get the euclidean distance from one point to another
    public double getDistance(Point p1, Point p2) {
        double d = 0;
        double x1 = p1.getColumn();
        double x2 = p2.getColumn();
        double y1 = p1.getRow();
        double y2 = p2.getRow();

        double x = Math.pow((x1-x2), 2);
        double y = Math.pow((y1-y2), 2);
        d = Math.sqrt((x+y));

        return d;
    }
    /*
    private void makeDefault() {
        for (int c=0; c<grid.length; c++) {
            for (int r=0; r<grid[c].length; r++) {
                grid[c][r] = 1;
            }
        }
    }
    */
}
