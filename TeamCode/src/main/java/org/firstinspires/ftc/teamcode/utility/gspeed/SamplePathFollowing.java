package org.firstinspires.ftc.teamcode.gspeed;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class SamplePathFollowing extends LinearOpMode {

    // A* Graph Class
    Graph graph = new Graph();
    Point start = new Point(0, 0);
    Point end = new Point(5, 5);

    PathBuilder pathbuilder = new PathBuilder(graph, start, end);
    Path path = pathbuilder.AStar();

    @Override
    public void runOpMode() throws InterruptedException {
        //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        waitForStart();

        if (isStopRequested()) return;
        telemetry.addData("Path:", graph.printPathFinder(path));
        telemetry.update();
        while(!isStopRequested() && opModeIsActive());
    }
}
