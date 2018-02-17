package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by 5015 Buffalo Wings on December 28, 2017 at 2:51 PM .
 */


@TeleOp (name = "Buffalo V v2.10.2018")
public class BuffaloV extends LinearOpMode {

	private Definitions robot = new Definitions(); // Call the main functions file for use later.
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);


    @Override
	public void runOpMode() throws InterruptedException { // Main function combines all functions into one.
		robot.init(hardwareMap); // Initialize all the hardware except servos.
		robot.servoInit(); // Enable servos on their own to allow for more accurate starting positions.

        double AL;
        double AR;

        runtime.reset();
        while(true) {

            if (runtime.seconds() < 3) {
                robot.armLeft.setPower(0.07);
                robot.armRight.setPower(-0.07);
            }
            else {
                robot.armRight.setPower(0);
                robot.armLeft.setPower(0);
                break;
            }

            if (runtime.seconds() > 3) {
                robot.armRight.setPower(0);
                robot.armLeft.setPower(0);
                break;
            }
        }
        sleep(500);
        robot.armEncoderReset();
		robot.armEncoderInit();

        robot.reel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.reel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

		waitForStart(); // Wait for driver to press start.
		robot.runtime.reset(); // Set the uptime to zero.

		while(opModeIsActive()){ // Once the driver presses start.

			//Actions with Game Pad 1
			double slow; // Number that allows for throttle control.

			// Adjust the slow variable based on certain buttons being pressed.

			if (gamepad1.right_bumper && !gamepad1.left_bumper) {
				slow = 0.2;
			} else if (!gamepad1.right_bumper && gamepad1.left_bumper) {
				slow = 2/5;
			} else if (gamepad1.right_bumper && gamepad1.left_bumper){
				slow = 2;
			} else {
				slow = 1;
			}

			//Math functions to run Mecanum Wheels

			// The Mecanum Algorithm combines the analog input from all the joysticks and
			// clips it in order to make sure that the motors do not burn out (i.e. Makes
			// sure the value doesn't climb above 1 or below -1.

			double DFR = Range.clip((-gamepad1.left_stick_y -(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x)*slow,-1,1);//to 1 making sure they don't burn out.
			double DFL = Range.clip((gamepad1.left_stick_y -(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x)*slow,-1,1); //This will clip the outputs to the motors
			double DBR = Range.clip((-gamepad1.left_stick_y +(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x)*slow,-1,1);
			double DBL = Range.clip((gamepad1.left_stick_y +(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x)*slow,-1,1);

			// Apply the values to the motors.

			robot.rightFront.setPower(DFR);
			robot.leftFront.setPower(DFL);
			robot.rightBack.setPower(DBR);
			robot.leftBack.setPower(DBL);

            // Pully motor controls

			if(gamepad1.dpad_up){ // Drive the motor to move the string up once DPAD_UP is pressed.
				robot.pulley.setPower(0.5);
			} else if(gamepad1.dpad_down){ // Drive the motor to move the string down when DPAD_DOWN is pressed.
				robot.pulley.setPower(-0.5);
			} else { // If nothing is pressed, set the power to zero.
				robot.pulley.setPower(0);
			}

			/**
			 *  Actions with Game Pad 2
			 **/
			if(gamepad2.a){ // Compress both arms if GAMEPAD2_A is pressed.
				robot.comp = 0.1;
			} else {
				robot.comp = 0;
			}

			// Algorithm to determine the power to set the left and right arms to.
            double Al = robot.comp + ((0.1 * gamepad2.left_stick_x) + (0.15 * -gamepad2.right_stick_y));
            double Ar = robot.comp+((0.1*-gamepad2.right_stick_x)+(0.15*-gamepad2.right_stick_y));

			if (robot.armLeft.getCurrentPosition() >= -70 && Al < 0) {
			    AL = 0;
            } else if (robot.armLeft.getCurrentPosition() <= -315 && Al > 0) {
			    AL = 0;
            } else {
                if (robot.armLeft.getCurrentPosition() < -175 && Al > 0) {
                    AL = 0.5*(-robot.comp + ((0.1 * -gamepad2.left_stick_x) + (0.15 * gamepad2.right_stick_y)));
                } else {
                    AL = -robot.comp + ((0.1 * -gamepad2.left_stick_x) + (0.15 * gamepad2.right_stick_y));
                }
            }

            if (robot.armRight.getCurrentPosition() <= 70 && Ar < 0) {
			    AR = 0;
            } else if (robot.armRight.getCurrentPosition() >= 300 && Ar > 0) {
			    AR = 0;
            } else {
			    if (robot.armRight.getCurrentPosition() > 175 && Ar > 0) {
                    AR = 0.5*(robot.comp+((0.1*-gamepad2.right_stick_x)+(0.15*-gamepad2.right_stick_y)));
                } else {
                    AR = robot.comp + ((0.1 * -gamepad2.right_stick_x) + (0.15 * -gamepad2.right_stick_y));
                }
            }
			// Apply the power to the motors.
			robot.armRight.setPower(AR);
			robot.armLeft.setPower(AL);


        /*
         * Reel Arm Controller
         */
            telemetry.addData("reel",robot.reel.getCurrentPosition());
            if (robot.reel.getCurrentPosition() >= 0 && gamepad2.left_stick_y > 0) {
                robot.reel.setPower(0);
            } else if (robot.reel.getCurrentPosition() <= -4200 && gamepad2.left_stick_y < 0) {
                robot.reel.setPower(0);

            } else {
                robot.reel.setPower(gamepad2.left_stick_y);
            }

			telemetry.addData("Run Time", robot.runtime.toString()); // Add the runtime information to the telemetry.
			telemetry.addData("Jewel Position", robot.jewel.getPosition()); // Add the jewel position to the telemetry.
			//	telemetry.addData("Relic Lift Position", robot.relicLift.getPosition());
			//	telemetry.addData("Relic Grip Position", robot.relicLift.getPosition());
			telemetry.update(); // Update the telemetry.


		}
	}
}
