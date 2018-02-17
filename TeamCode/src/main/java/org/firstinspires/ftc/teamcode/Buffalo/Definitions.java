package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created for team: 5015 Buffalo Wings by Noah Zulick on December 23, 2017 at 4:36 PM .
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
	double comp = 0;
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
		//relicGrip = Map.servo.get("relicGrip");
		//relicLift = Map.servo.get("relicLift");
		jewelSensor= Map.colorSensor.get("jewelSensor");
	}

	public void testmapinit(HardwareMap TestMap) {

		jewelSensor = TestMap.colorSensor.get("jewelSensor");
		jewel = TestMap.servo.get("armJewel");

	}


		/** defines all actions for Autonomous **/




	void servoInit() {
		jewel.setPosition(1);
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

	void setPower(double power){
		leftBack.setPower(power);
		leftFront.setPower(power);
		rightBack.setPower(power);
		rightFront.setPower(power);
	}

	void armEncoderInit() {
		armLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		armRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	}
	void armEncoderReset() {
		armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	}

	void encoderInit(){
		leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	}

	void setBigPos(int pos) {
		leftBack.setTargetPosition(pos*92);
		leftFront.setTargetPosition(pos*92);
		rightBack.setTargetPosition(pos*92);
		rightFront.setTargetPosition(pos*92);
	}
	
	void setRotPos(int pos){
		leftBack.setTargetPosition((pos*1120)/360);
		leftFront.setTargetPosition((pos*1120)/360);
		rightBack.setTargetPosition((pos*1120)/360);
		rightFront.setTargetPosition((pos*1120)/360);
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

	private int motorTicks = 1120;
	private int wheelDiameter = 4;

	public void posDegree(double degreesToRotate){
		int T = motorTicks;
		double Rd = 17.5;
		double Wd = wheelDiameter;

		int poss = (int) (((Rd/Wd)*(T/360))+0.5);

		leftBack.setTargetPosition(poss + leftBack.getCurrentPosition());
		leftFront.setTargetPosition(poss + leftFront.getCurrentPosition());
		rightBack.setTargetPosition(poss + rightBack.getCurrentPosition());
		rightFront.setTargetPosition(poss + rightFront.getCurrentPosition());
	}


	public void posINCH(double pos){
		double toInch = motorTicks/(wheelDiameter*Math.PI);

		int poss = (int) (pos*toInch);

		leftBack.setTargetPosition(poss + leftBack.getCurrentPosition());
		leftFront.setTargetPosition(poss + leftFront.getCurrentPosition());
		rightBack.setTargetPosition(poss + rightBack.getCurrentPosition());
		rightFront.setTargetPosition(poss + rightFront.getCurrentPosition());
	}

	//public void driveForwardINCH (double Inches, double powerStart);


	//Waits for the drive motor(s) to stop rotating
	void waitForDriveStop() {
		while (true) {
			if (!(leftBack.isBusy())) break;
		}
	}
}
