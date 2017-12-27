package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Noah Zulick (Buffalo Wings) on 12/23/2017.
 */

@Autonomous (name = "Autonomous beta v.1.2.10x")
public class Beta extends LinearOpMode{

	private Definitions robot = new Definitions();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.init(hardwareMap);
		robot.encoderInit();

		waitForStart();

		if ()



	}


}
