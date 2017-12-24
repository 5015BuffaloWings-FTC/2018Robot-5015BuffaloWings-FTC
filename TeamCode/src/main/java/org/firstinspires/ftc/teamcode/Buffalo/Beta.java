package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CHSRobotics on 12/23/2017.
 */

@Autonomous (name = "Autonomous beta v.1.0")
public class Beta extends LinearOpMode{

	private Definitions robot = new Definitions();

	@Override
	public void runOpMode() throws InterruptedException {

		waitForStart();


		
		robot.resetEncoders();
		robot.setPos(2);
		robot.run();
		robot.forward(0.5);
		robot.waitForDriveStop();
		robot.wait();


	}


}
