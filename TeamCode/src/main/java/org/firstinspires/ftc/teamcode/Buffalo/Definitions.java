package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by CHSRobotics on 12/23/2017.
 */

public class Definitions  {

	ElapsedTime runtime = new ElapsedTime(); // Defines the Up Time of the program


	DcMotor leftBack = null; // Define Back Left Motor
	DcMotor leftFront = null; // Define Front Left Motor
	DcMotor rightBack = null; // Define Back Right Motor
	DcMotor rightFront = null; // Define Front Right Motor
	DcMotor armRight = null;
	DcMotor armLeft = null;
	DcMotor reel = null;
	DcMotor pulley = null;
	Servo jewel = null;
	Servo relicLift = null;
	Servo relicGrip = null;
	ColorSensor jewelSensor = null;
	private HardwareMap testMap;
	double comp = 0;
	double slow = 1;
	double grab = 0;

	public Definitions() {
		leftBack = null;
	}


	public void init(HardwareMap Map) {

		/** Find Motors/Servos in the configuration and pull into code **/

		leftBack = Map.dcMotor.get("leftBack");
		leftFront = Map.dcMotor.get("leftFront");
		rightBack = Map.dcMotor.get("rightBack");
		rightFront = Map.dcMotor.get("rightFront");
		armRight = Map.dcMotor.get("armRight");
		armLeft = Map.dcMotor.get("armLeft");
		reel = Map.dcMotor.get("reel");
		pulley = Map.dcMotor.get("pulley");
		jewel = Map.servo.get("armJewel");
		relicGrip = Map.servo.get("relicGrip");
		relicLift = Map.servo.get("relicLift");
		jewelSensor= Map.colorSensor.get("jewelSensor");
	}

	public void testmapinit(HardwareMap TestMap) {

		jewelSensor = TestMap.colorSensor.get("jewelSensor");

	}


		/** defines all actions for Autonomous **/




	void servoInit() {
		jewel.setPosition(1);
		relicLift.setPosition(0.5);
		relicGrip.setPosition(0);
	}

	void setJewelPosition(double Position) {
		jewel.setPosition(Position);
	}


	void setDriveForward() {
		leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
		leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
		rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
		rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
	}

	void setDriveBackward() {
		leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
		leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
		rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
		rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
	}

	void setRotLeft() {
		leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
		leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
		rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
		rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
	}

	void setRotRight() {
		leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
		leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
		rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
		rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
	}

	void setStrafeLeft() {
		leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
		leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
		rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
		rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
	}

	void setStrafeRight() {
		leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
		leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
		rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
		rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
	}



	void forward(double power){
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}

	void backward(double power){
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}


	void rotLeft(double power) {
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}

	void rotRight(double power) {
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}

	void strafeLeft(double power){
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}

	void strafeRight(double power){
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}


	void encoderInit(){
		leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}

	void setPos(int pos) {
		leftBack.setTargetPosition(pos);
		leftFront.setTargetPosition(pos);
		rightBack.setTargetPosition(pos);
		rightFront.setTargetPosition(pos);
	}

	void runPos() {
		leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	}

	void resetEncoders() {
		leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	}




	//Waits for the drive motor(s) to stop rotating
	void waitForDriveStop() {
		while (true) {
			if (!(leftBack.isBusy())) break;
		}
	}


}
