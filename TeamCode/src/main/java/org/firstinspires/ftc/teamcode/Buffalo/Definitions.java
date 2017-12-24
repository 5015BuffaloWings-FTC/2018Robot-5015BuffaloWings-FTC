package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CHSRobotics on 12/23/2017.
 */

public class Definitions  {

	ElapsedTime runtime = new ElapsedTime(); // Defines the Up Time of the program
	DcMotor leftBack = null; // Define Back Left Motor
	DcMotor leftFront = null; // Define Front Left Motor
	DcMotor rightBack = null; // Define Back Right Motor
	DcMotor rightFront = null; // Define Front Right Motor
	DcMotor arm_1 = null;
	DcMotor arm_2 = null;
	DcMotor armReel = null;
	
	
	public void init(HardwareMap Map) {

		leftBack = Map.dcMotor.get("leftBack"); // Initialize Left Drive
		leftFront = Map.dcMotor.get( "leftFront"); // Define Right Drive
		rightBack = Map.dcMotor.get( "rightBack"); // Define Left Drive
		rightFront = Map.dcMotor.get( "rightFront"); // Define Left Drive
		arm_1 = Map.dcMotor.get( "ArmRight"); // Define Right Arm
		arm_2 = Map.dcMotor.get( "ArmLeft"); // Define Left Arm
		armReel = Map.dcMotor.get( "armReel"); // Define Glyph Reel

	}




}
