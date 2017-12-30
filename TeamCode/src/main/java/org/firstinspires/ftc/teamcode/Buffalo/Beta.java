package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created for team: 5015 Buffalo Wings by Noah Zulick on December 28, 2017 at 2:50 PM .
 */

@Autonomous (name = "Autonomous beta v.2.1.6")
public class Beta extends LinearOpMode{

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
		robot.jewelSensor.enableLed(true);



		waitForStart();
		runtime.reset();



		while(opModeIsActive()) {

		telemetry.addData("Status", "Running");
		telemetry.addLine()
				.addData("Blue", robot.jewelSensor.blue())
				.addData("Red", robot.jewelSensor.red())
				.addData("Green", robot.jewelSensor.green());
			telemetry.update();



		if (runtime.seconds() <= 5) {
			robot.resetEncoders();
			robot.setJewelPosition(0.6);

			if (robot.jewelSensor.blue() >= 3 && robot.jewelSensor.red() <= 2 && robot.jewelSensor.green() >= 0) {
				robot.setRotLeft();
				robot.rotLeft(0.3);
				robot.setJewelPosition(1);
				robot.setRotRight();
				robot.rotRight(0.6);
				jewelGotten = true;
				FORWARD = true;
				BACKWARD = false;
				telemetry.addData("Jewel", "Gotten!");
				telemetry.update();
				robot.waitForDriveStop();

			} else if (robot.jewelSensor.blue() >= 3 && robot.jewelSensor.red() <= 2 && robot.jewelSensor.green() >= 0) {
				robot.setRotRight();
				robot.rotRight(0.3);
				robot.setJewelPosition(1);
				jewelGotten = true;
				FORWARD = false;
				BACKWARD = true;
				telemetry.addData("Jewel", "Gotten!");
				telemetry.update();
				robot.waitForDriveStop();
			} else if (runtime.seconds() >= 5) {
				robot.setJewelPosition(1);
				jewelGotten = false;
				FORWARD = false;
				BACKWARD = false;
				telemetry.addData("Jewel", "Not Gotten");
				telemetry.update();
			} else {
				stop();
			}
		}


		if (runtime.seconds() <= 10) {
			if ((jewelGotten = true) && (FORWARD = true) && (BACKWARD = false)) {
				robot.resetEncoders();
				robot.setRotRight();
				robot.rotRight(0.3);
				robot.setDriveBackward();
				robot.backward(0.5);
				robot.setPos(500);
				robot.runPos();
				robot.waitForDriveStop();
				intape = true;
			} else if ((jewelGotten = true) && (FORWARD = false) && (BACKWARD = true)) {
				robot.resetEncoders();
				robot.setDriveBackward();
				robot.backward(0.5);
				robot.setPos(500);
				robot.runPos();
				robot.waitForDriveStop();
				intape = true;
			} else if ((jewelGotten = false) && (FORWARD = false) && (BACKWARD = false)) {
				robot.resetEncoders();
				robot.setDriveBackward();
				robot.backward(0.5);
				robot.setPos(500);
				robot.runPos();
				robot.waitForDriveStop();
				intape = true;
			} else {
				stop();
			}

		}



		}

	}

}
