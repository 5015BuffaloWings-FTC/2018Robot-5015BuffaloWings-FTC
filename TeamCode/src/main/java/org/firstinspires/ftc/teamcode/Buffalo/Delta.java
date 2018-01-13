package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created for team: 5015 Buffalo Wings by Noah Zulick on December 29, 2017 at 11:41 AM .
 */
@Autonomous (name = "Auto Test v1.1")
public class Delta extends LinearOpMode{

	private Definitions robot = new Definitions();
	private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

	@Override
	public void runOpMode() throws InterruptedException {

		telemetry.addData("Position on Field (with reference to the Relic matt", "back right");
		telemetry.addData("Status", "Initialized");
		telemetry.addData("Jewel", "Not Gotten");
		telemetry.update();


		boolean jewelGotten = false;
		boolean FORWARD = false;
		boolean BACKWARD = false;



		robot.init(hardwareMap);
		robot.servoInit();
		robot.encoderInit();
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


			robot.setJewelPosition(0.17);
			sleep(1000);
			robot.setRotLeft();
			robot.setRotPos(30);
			robot.runPos();
			robot.setPower(0.8);
			robot.waitForDriveStop();




		}

	}
}
