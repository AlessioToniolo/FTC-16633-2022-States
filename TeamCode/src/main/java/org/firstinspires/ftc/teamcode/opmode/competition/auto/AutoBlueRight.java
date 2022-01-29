package org.firstinspires.ftc.teamcode.opmode.competition.auto;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.opmode.competition.auto.camerapipelines.Pipeline_Target_Detect;
import org.firstinspires.ftc.teamcode.utility.BaseRobot;
import org.firstinspires.ftc.teamcode.utility.Fields;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
public class AutoBlueRight extends LinearOpMode{
    //Fields
    BaseRobot robot = new BaseRobot();
    private final ElapsedTime runtime = new ElapsedTime();
    //Camera Fields
    WebcamName webcamName;
    OpenCvCamera camera;
    Pipeline_Target_Detect myPipeline;
    double zone = 3;
    double xPos = -1;


    @Override
    public void runOpMode()  {
        telemetry.addLine("Wait for Start!");
        // Initialize Hardware
        robot.init(hardwareMap, true);
        // Signal that robot is ready to run
        telemetry.addLine("Ready to start!");
        // Wait for User to Start
        telemetry.update();
        waitForStart();
        //Camera vision
        detectTarget();
        // Autonomous Movements
        movement();




    }
    public void detectTarget()
    {
        // Init Camera
        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
        myPipeline = new Pipeline_Target_Detect(5, 35);//create pipeline
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(myPipeline);
            }

            @Override
            public void onError(int errorCode) {
            }
        });
        delay(2);
        xPos = myPipeline.getXPos();//Get xPos from PIpeline
        telemetry.addLine("XPos: " + xPos);
        // Read Detection
        if (xPos < 100) {
            zone = 1;
        } else if (xPos > 200) {
            zone = 3;
        } else {
            zone = 2;
        }
        // Close Camera
        camera.stopStreaming();
        camera.closeCameraDevice();
        // Print Detection to Drivers
        telemetry.addLine("XPos: " + xPos);
        telemetry.addLine("Zone: " + zone);
        telemetry.update();
    }
    public void movement(){

    }
    public void turn() {}
    public void drive(){}
    public void delay(double t) { // Imitates the Arduino delay function
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < t)) {
        }
    }
}
