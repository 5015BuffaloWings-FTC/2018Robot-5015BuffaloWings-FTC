package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CHSRobotics on 12/23/2017.
 */

@Autonomous (name = "Autonomous beta v.1.2.8")
public class Beta extends LinearOpMode{

	private Definitions robot = new Definitions();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.init(hardwareMap);

		waitForStart();

		if(time < 10) {
			robot.resetEncoders();
			robot.setPos(5000);
			robot.forward(0.5);
			robot.run();
			robot.waitForDriveStop();
		} else {
			sleep(1);
		}

		sleep(500);

		robot.resetEncoders();
		robot.setPos(5000);
		robot.run();
		robot.backward(0.5);
		robot.waitForDriveStop();

	}


}
