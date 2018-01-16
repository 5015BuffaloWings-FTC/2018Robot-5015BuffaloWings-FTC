package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created for team: 5015 Buffalo Wings by Noah Zulick on December 28, 2017 at 2:50 PM .
 */

@Autonomous (name = "WE ARE RED TEAM")
public class Phi extends LinearOpMode{

	private Definitions robot = new Definitions();
	private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

	@Override
	public void runOpMode() throws InterruptedException {

		telemetry.addData("Position on Field (with reference to the Relic matt", "back right");
		telemetry.addData("Status", "Initialized");
		telemetry.addData("Jewel", "Not Gotten");
		telemetry.addData("In Tape", "No");
		telemetry.update();


		boolean jewelGotten = false;
		boolean FORWARD = false;
		boolean BACKWARD = false;
		boolean intape = false;



		robot.init(hardwareMap);
		robot.encoderInit();
		robot.servoInit();
		robot.jewelSensor.enableLed(false);



		waitForStart();
		runtime.reset();



		while(opModeIsActive()) {

			telemetry.addLine()
					.addData("Status", "Running")
					.addData("Run Time", robot.runtime.toString());

			telemetry.update();



			if (runtime.seconds() <= 5 && !jewelGotten) {
				robot.setJewelPosition(0.17);
				sleep(1000);
				robot.jewelSensor.enableLed(true);
				robot.resetEncoders();
				telemetry.addLine()
						.addData("Blue", robot.jewelSensor.blue())
						.addData("Red", robot.jewelSensor.red())
						.addData("Green", robot.jewelSensor.green());
				telemetry.update();

				if (robot.jewelSensor.blue() < 0 || robot.jewelSensor.red() < 0){
					robot.setRotLeft();
					robot.setPower(0.2);
				} else if (robot.jewelSensor.blue() > robot.jewelSensor.red()) {
					robot.setRotLeft();
					robot.setRotPos(50);
					robot.runPos();
					robot.setPower(0.8);
					robot.waitForDriveStop();
					robot.setJewelPosition(1);
					sleep(1000);
					robot.setRotRight();
					robot.resetEncoders();
					robot.setRotPos(50);
					robot.runPos();
					robot.setPower(0.8);
					jewelGotten = true;
					FORWARD = true;
					BACKWARD = false;
					telemetry.addData("Jewel", "Gotten!");
					telemetry.update();


				} else if (robot.jewelSensor.blue() < robot.jewelSensor.red()) {
					robot.setRotRight();
					robot.resetEncoders();
					robot.setRotPos(50);
					robot.runPos();
					robot.setPower(0.8);
					robot.waitForDriveStop();
					robot.setJewelPosition(1);
					sleep(1000);
					jewelGotten = true;
					FORWARD = false;
					BACKWARD = true;
					telemetry.addData("Jewel", "Gotten!");
					telemetry.update();

				} else if (runtime.seconds() >= 5 && !jewelGotten) {
					jewelGotten = false;
					FORWARD = false;
					BACKWARD = false;
					telemetry.addData("Jewel", "Not Gotten");
					telemetry.update();
				}
			}

/*
		if (runtime.seconds() <= 10) {
			if ((jewelGotten = true) && (FORWARD = true) && (BACKWARD = false)) {
				robot.resetEncoders();
				robot.setRotRight();
				robot.setPower(0.3);
				robot.setDriveBackward();
				robot.setPower(0.5);
				robot.setBigPos(500);
				robot.runPos();
				robot.waitForDriveStop();
				intape = true;
			} else if ((jewelGotten = true) && (FORWARD = false) && (BACKWARD = true)) {
				robot.resetEncoders();
				robot.setDriveBackward();
				robot.setPower(0.5);
				robot.setBigPos(500);
				robot.runPos();
				robot.waitForDriveStop();
				intape = true;
			} else if ((jewelGotten = false) && (FORWARD = false) && (BACKWARD = false)) {
				robot.resetEncoders();
				robot.setDriveBackward();
				robot.setPower(0.5);
				robot.setBigPos(500);
				robot.runPos();
				robot.waitForDriveStop();
				intape = true;
			} else {
				stop();
			}

		}
*/


		}

	}

}
