package org.firstinspires.ftc.teamcode.gspeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PathFollower {

    private Path path = new Path();

    public PathFollower(Path p) {
        path = p;
    }

    private boolean compareSlopes(Point prev, Point curr, Point next) {
        int prevCurrSlope = curr.getRow() - prev.getRow() / curr.getColumn() - prev.getRow();
        int currNextSlope = next.getRow() - curr.getRow() / next.getColumn() - curr.getRow();
        return prevCurrSlope == currNextSlope;
    }

    private ArrayList<Point> pathGrouper() {
        ArrayList<Point> out = new ArrayList<Point>();
        Point current = new Point(0, 0);
        Point forward1 = new Point(0, 0);
        Point back1 = new Point(0, 0);
        for (int i=0; i<path.getPath().size(); i++) {
            if (i==1) {
                out.add(new Point(path.getPath().get(i).getRow(), path.getPath().get(i).getColumn()));
            } else {
                back1 = current;
                current = new Point(path.getPath().get(i).getRow(), path.getPath().get(i).getColumn());
                forward1 = new Point(path.getPath().get(i+1).getRow(), path.getPath().get(i+1).getColumn());

                // Conditions
                if (current.getColumn() == back1.getColumn() && current.getColumn() == forward1.getColumn()) {
                    // Don't Add
                } else {
                    if (!compareSlopes(back1, current, forward1)) {
                        out.add(path.getPath().get(i));
                    }
                }

            }
        }
        return out;
    }


    public Vector2d unitConvert(Point p) {
        return new Vector2d(24*p.getColumn()-60, -24*p.getRow()-60);
    }

    public TrajectoryBuilder followPath(SampleMecanumDrive drive, Pose2d endPose) {
        TrajectoryBuilder traj = drive.trajectoryBuilder(endPose);
        for (int i=0; i<pathGrouper().size(); i++) {
            Point point = new Point(pathGrouper().get(i).getRow(), pathGrouper().get(i).getColumn());
            Vector2d out = unitConvert(point);
            traj.lineTo(new Vector2d());
        }
        return traj;
    }

}
