package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by 5015 Buffalo Wings on December 28, 2017 at 2:51 PM .
 */


@TeleOp (name = "Buffalo V v2.10.2018")
public class BuffaloV extends LinearOpMode {

	private Definitions robot = new Definitions(); // Call the main functions file for use later.


	@Override
	public void runOpMode() throws InterruptedException { // Main function combines all functions into one.

		robot.init(hardwareMap); // Initialize all the hardware except servos.
		robot.servoInit(); // Enable servos on their own to allow for more accurate starting positions.

		waitForStart(); // Wait for driver to press start.
		robot.runtime.reset(); // Set the uptime to zero.

		while(opModeIsActive()){ // Once the driver presses start.

			//Actions with Game Pad 1
			double slow; // Number that allows for throttle control.

			// Adjust the slow variable based on certain buttons being pressed.

			if (gamepad1.right_bumper && !gamepad1.left_bumper) {
				slow = 0.2;
			}
			else if (!gamepad1.right_bumper && gamepad1.left_bumper) {
				slow = 2/5;
			}
			else if (gamepad1.right_bumper && gamepad1.left_bumper){
				slow = 2;
			}
			else {
				slow = 1;
			}

			//Math functions to run Mecanum Wheels

			// The Mecanum Algorithm combines the analog input from all the joysticks and
			// clips it in order to make sure that the motors do not burn out (i.e. Makes
			// sure the value doesn't climb above 1 or below -1.

			double DFR = Range.clip((-gamepad1.left_stick_y -(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x),-1,1);//to 1 making sure they don't burn out.
			double DFL = Range.clip((gamepad1.left_stick_y -(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x),-1,1); //This will clip the outputs to the motors
			double DBR = Range.clip((-gamepad1.left_stick_y +(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x),-1,1);
			double DBL = Range.clip((gamepad1.left_stick_y +(0.87*gamepad1.left_stick_x) - gamepad1.right_stick_x),-1,1);

			// Apply the values to the motors.

			robot.rightFront.setPower(DFR);
			robot.leftFront.setPower(DFL);
			robot.rightBack.setPower(DBR);
			robot.leftBack.setPower(DBL);

/*
			double PU = Range.clip((gamepad1.right_stick_y), -1, 1);

			robot.pulley.setPower(PU);
*/

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
			double AL = -robot.comp+((0.1*-gamepad2.left_stick_x)+(0.15*gamepad2.right_stick_y));
			double AR = robot.comp+((0.1*-gamepad2.right_stick_x)+(0.15*-gamepad2.right_stick_y));

			// Apply the power to the motors.
			robot.armRight.setPower(AR);
			robot.armLeft.setPower(AL);


        /*
         * Reel Arm Controller
         */

			double V6 = -gamepad2.left_stick_y; // Reverse the value of the stick in order to provide a useable control. Pulling down makes the reel go up and vice versa.
			robot.reel.setPower(V6);




			/********************************/
			/**Relic Grabber/Gripper Code**/
			/********************************/

		/*	if(gamepad2.dpad_up && !gamepad2.dpad_down) {
				robot.relicLift.setDirection(Servo.Direction.FORWARD);
			}
			else if(!gamepad2.dpad_up && gamepad2.dpad_down) {
				robot.relicLift.setDirection(Servo.Direction.REVERSE);
			} else {
				robot.relicLift.setPosition(0.5);
			}


			if(gamepad2.x){
				robot.relicGrip.setPosition(0.1);
			} else {
				robot.relicGrip.setPosition(0);
			} */



			telemetry.addData("Run Time", robot.runtime.toString()); // Add the runtime information to the telemetry.
			telemetry.addData("Jewel Position", robot.jewel.getPosition()); // Add the jewel position to the telemetry.
			//	telemetry.addData("Relic Lift Position", robot.relicLift.getPosition());
			//	telemetry.addData("Relic Grip Position", robot.relicLift.getPosition());
			telemetry.update(); // Update the telemetry.


		}
	}
}
