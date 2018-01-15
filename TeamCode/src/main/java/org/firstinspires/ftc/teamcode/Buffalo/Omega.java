package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created for team: 5015 Buffalo Wings by Noah Zulick on December 28, 2017 at 2:51 PM .
 */


@TeleOp (name = "Sexy TeleOp v1.0.0")
public class Omega extends LinearOpMode {

	private Definitions robot = new Definitions();


	@Override
	public void runOpMode() throws InterruptedException {

		robot.init(hardwareMap);
		robot.servoInit();

		waitForStart();
		robot.runtime.reset();

		while(opModeIsActive()){



			/**
			 * Actions with Game Pad 1
			 **/
			double slow;

			if (gamepad1.right_bumper && !gamepad1.left_bumper) {
				slow = 0.2;
			}
			else if (!gamepad1.right_bumper && gamepad1.left_bumper) {
				slow = (2/5);
			}
			else if (gamepad1.right_bumper && gamepad1.left_bumper){
				slow = 2;
			}
			else {
				slow = 1;
			}

			double DFR = Range.clip(slow*0.5*(-gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);//to 1 making sure they don't burn out.
			double DFL = Range.clip(slow*0.5*(gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1); //This will clip the outputs to the motors
			double DBR = Range.clip(slow*0.5*(-gamepad1.left_stick_y +gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);
			double DBL = Range.clip(slow*0.5*(gamepad1.left_stick_y +gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);

			robot.rightFront.setPower(DFR);
			robot.leftFront.setPower(DFL);
			robot.rightBack.setPower(DBR);
			robot.leftBack.setPower(DBL);

/*
			double PU = Range.clip((gamepad1.right_stick_y), -1, 1);

			robot.pulley.setPower(PU);
*/

			if(!gamepad1.dpad_down && gamepad1.dpad_up){
				robot.pulley.setPower(0.5);
			} else if (gamepad1.dpad_down && !gamepad1.dpad_up){
				robot.pulley.setPower(-0.5);
			} else {
				robot.pulley.setPower(0);
			}



			/**
			 *  Actions with Game Pad 2
			 **/

			if(gamepad2.a){
				robot.comp = 0.1;
			} else {
				robot.comp = 0;
			}

			double AR = robot.comp+((0.1*-gamepad2.right_stick_x)+(0.15*-gamepad2.right_stick_y));
			double AL = -robot.comp+((0.1*-gamepad2.left_stick_x)+(0.15*gamepad2.right_stick_y));

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




			telemetry.addData("Run Time", robot.runtime.toString());
			telemetry.addData("Jewel Position", robot.jewel.getPosition());
			//	telemetry.addData("Relic Lift Position", robot.relicLift.getPosition());
			//	telemetry.addData("Relic Grip Position", robot.relicLift.getPosition());
			telemetry.update();


		}
	}
}
