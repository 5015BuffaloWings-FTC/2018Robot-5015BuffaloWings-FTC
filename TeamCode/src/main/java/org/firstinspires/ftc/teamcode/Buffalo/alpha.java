package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Noah v.1.23", group="Iterative Opmode")
public class alpha extends OpMode{

    private Definitions robot = new Definitions();



    // Op Mode Essential Variables

    private ElapsedTime runtime = new ElapsedTime(); // Defines the Up Time of the program
    private DcMotor leftBack = null; // Define Back Left Motor
    private DcMotor leftFront = null; // Define Front Left Motor
    private DcMotor rightBack = null; // Define Back Right Motor
    private DcMotor rightFront = null; // Define Front Right Motor
    private DcMotor arm_1 = null;
    private DcMotor arm_2 = null;
    private DcMotor armReel = null;
    double slow = 1;
    double comp = 0;


    //Driver starts the program


    @Override
    public void init() { // Initialize all components

        leftBack  = hardwareMap.get(DcMotor.class, "leftBack"); // Initialize Left Drive
        leftFront = hardwareMap.get(DcMotor.class, "leftFront"); // Define Right Drive
        rightBack  = hardwareMap.get(DcMotor.class, "rightBack"); // Define Left Drive
        rightFront  = hardwareMap.get(DcMotor.class, "rightFront"); // Define Left Drive
        arm_1 = hardwareMap.get(DcMotor.class, "ArmRight"); // Define Right Arm
        arm_2 = hardwareMap.get(DcMotor.class, "ArmLeft"); // Define Left Arm
        armReel = hardwareMap.get(DcMotor.class, "armReel");

    }


    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        /*
         * Mecanum Wheel Algori thm
         */

        // Grab left stick Y value and left stick X value and return the square root
        double r = Math.hypot(gamepad1.left_stick_y, -gamepad1.right_stick_x);
        // Convert the coordinates into a polar format.
        //double robotAngle = Math.atan2(-gamepad1.right_stick_x, gamepad1.left_stick_y) - 0.785;
        // Grab the Right Stick X Value
        double leftX = gamepad1.left_stick_x;

        // The following variables use an algorithm in order to convert the variables into a
        // format to run the mecanum wheels.

        // There are two different algorithms for each wheel.
        //
        // The Cosine method uses the square root of Left Stick Y and Right Stick X
        // and multiplies it by the Cosine of the RobotAngle and then adds the
        // value of the Left Stick X. This converts the coordinates into a single value
        // that can be applied to each motor. This method converts the rotation of the
        // left stick into a
        //
        // The Sine method uses the square root of Left Stick Y and Right Stick X
        // and multiplies it by the Sine of the RobotAngle and then subtracts the
        // value of Left Stick X.
/*
        double V1 = r * Math.cos(robotAngle) + leftX;
        double V2 = r * Math.sin(robotAngle) - leftX;
        double V3 = r * Math.sin(robotAngle) + leftX;
        double V4 = r * Math.cos(robotAngle) - leftX;
*/

        /*double V1 = -gamepad1.right_stick_x / r;
        double V2 = gamepad1.left_stick_y / r;
        double V3 = gamepad1.left_stick_y / r;
        double V4 = -gamepad1.right_stick_x / r;*/

       /* double V1 = r * -gamepad1.right_stick_x + leftX;
        double V2 = r * gamepad1.left_stick_y - leftX;
        double V3 = r * gamepad1.left_stick_y + leftX;
        double V4 = r * -gamepad1.right_stick_x - leftX;*/



        if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            slow = 0.1;
        }
        else if (!gamepad1.right_bumper && gamepad1.left_bumper) {
            slow = 0.2;
        }
        else {
            slow = 1;
        }

		double DFR = Range.clip(slow*(-gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);//to 1 making sure they don't burn out.
        double DFL = Range.clip(slow*(gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1); //This will clip the outputs to the motors
        double DBR = Range.clip(slow*(-gamepad1.left_stick_y +gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);
        double DBL = Range.clip(slow*(gamepad1.left_stick_y +gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);

        rightFront.setPower(DFR);
		leftFront.setPower(DFL);
        rightBack.setPower(DBR);
        leftBack.setPower(DBL);




		if(gamepad2.a){
			comp = 0.1;
		} else {
			comp = 0;
		}

        double AR = comp+((0.1*gamepad2.right_stick_x)+(0.15*+gamepad2.right_stick_y)); //idk what im doing -Noah
        double AL = -comp+((0.1*-gamepad2.left_stick_x)+(0.15*-gamepad2.right_stick_y));

        arm_1.setPower(AR);
        arm_2.setPower(AL);



        /*
         * Reel Arm Controller
         */

        double V6 = -gamepad2.left_stick_y; // Reverse the value of the stick in order to provide a useable control. Pulling down makes the reel go up and vice versa.
        armReel.setPower(V6);

      /*  telemetry.addData("EMERGENCY STOP", "ACTIVATED");

        if(gamepad1.right_trigger == 1 && gamepad2.left_trigger == 1 || gamepad1.right_trigger == 1 && gamepad1.left_trigger == 1) {
            leftFront.close();
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setPower(0);

            leftBack.close();
            leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBack.setPower(0);

            rightFront.close();
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFront.setPower(0);

            rightBack.close();
            rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBack.setPower(0);
        }

        if(gamepad1.x && gamepad1.left_trigger == 1) {
            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
*/




    }

    @Override
    public void stop() {
    }
}
