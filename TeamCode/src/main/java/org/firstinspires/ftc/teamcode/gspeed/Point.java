package org.firstinspires.ftc.teamcode.gspeed;

public class Point {
    // TODO: comment point
    private int r = 0;
    private int c = 0;

    public Point(int y, int x) {
        r = y;
        c = x;
    }

    public String toString() {
        String output = "";
        output = String.valueOf(r) + "," + String.valueOf(c);
        return output;
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    public boolean equals(Point p) {
        return p.getRow() == r && p.getColumn() == c;
    }

}
