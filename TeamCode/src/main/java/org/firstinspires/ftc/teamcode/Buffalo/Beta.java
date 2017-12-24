package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CHSRobotics on 12/23/2017.
 */

@Autonomous (name = "Autonomous beta v.1.2.3")
public class Beta extends LinearOpMode{

	private Definitions robot = new Definitions();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.init(hardwareMap);

		waitForStart();


		robot.resetEncoders();
		robot.setPos(5000);
		robot.run();
		robot.forward(0.5);
		robot.waitForDriveStop();

		sleep(100);

		robot.resetEncoders();
		robot.setPos(5000);
		robot.run();
		robot.backward(0.5);
		robot.waitForDriveStop();

	}


}
