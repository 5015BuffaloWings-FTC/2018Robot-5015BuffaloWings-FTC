package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Buffalo.Definitions;


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
			telemetry.addLine()
					.addData("Blue", robot.jewelSensor.blue())
					.addData("Red", robot.jewelSensor.red())
					.addData("Green", robot.jewelSensor.green());
			telemetry.update();



			if (runtime.seconds() <= 5 && !jewelGotten) {
				robot.setJewelPosition(0.17);
				sleep(1000);
				robot.jewelSensor.enableLed(true);
				robot.resetEncoders();

				if (robot.jewelSensor.blue() == 0 && robot.jewelSensor.red() == 0 && !jewelGotten){
					robot.setDriveForward();
					robot.setBigPos(10);
					robot.runPos();
					robot.setPower(0.2);
					robot.waitForDriveStop();
					robot.setPower(0);
				} else if (robot.jewelSensor.blue() > robot.jewelSensor.red()) {
					robot.setDriveForward();
					robot.setBigPos(500);
					robot.runPos();
					robot.setPower(0.8);
					robot.waitForDriveStop();
					robot.setPower(0);
					sleep(1000);
					robot.setJewelPosition(1);
					jewelGotten = true;
					FORWARD = true;
					BACKWARD = false;
					telemetry.addData("Jewel", "Gotten!");
					telemetry.update();


				} else if (robot.jewelSensor.blue() < robot.jewelSensor.red()) {
					robot.setDriveBackward();
					robot.setBigPos(50);
					robot.runPos();
					robot.setPower(0.8);
					robot.waitForDriveStop();
					robot.setPower(0);
					sleep(1000);
					robot.setJewelPosition(1);
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

		/*	if (runtime.seconds() >= 5 && runtime.seconds() <= 10 && jewelGotten) {
				if ((jewelGotten = true) && (FORWARD = true) && (BACKWARD = false)) {
					robot.resetEncoders();
					robot.setDriveForward();
					robot.setPower(0.5);
					robot.setBigPos(500);
					robot.runPos();
					robot.waitForDriveStop();
					intape = true;
				} else if ((jewelGotten = true) && (FORWARD = false) && (BACKWARD = true)) {
					robot.resetEncoders();
					robot.setDriveForward();
					robot.setPower(0.5);
					robot.setBigPos(500);
					robot.runPos();
					robot.waitForDriveStop();
					intape = true;
				} else if (!jewelGotten && !FORWARD && !BACKWARD) {
					robot.resetEncoders();
					robot.setDriveForward();
					robot.setPower(0.5);
					robot.setBigPos(500);
					robot.runPos();
					robot.waitForDriveStop();
					intape = true;
				} else {
					stop();
				}

			}
*/		}

	}

}
