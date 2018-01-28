package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * Created for team: 5015 Buffalo Wings by Noah Zulick on December 28, 2017 at 3:44 PM .
 */
@TeleOp (name = "Jewel Test")
public class Gamma extends LinearOpMode {

	private Definitions robot = new Definitions();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.testmapinit(hardwareMap);

		waitForStart();
		robot.runtime.reset();

		while(opModeIsActive()){

			telemetry.addData("Status", "Running");
			telemetry.addLine()
					.addData("Blue", robot.jewelSensor.blue())
					.addData("Red", robot.jewelSensor.red())
					.addData("Green", robot.jewelSensor.green());
			telemetry.update();

		}
	}
}
