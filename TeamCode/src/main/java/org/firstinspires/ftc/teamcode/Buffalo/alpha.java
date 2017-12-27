package org.firstinspires.ftc.teamcode.Buffalo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Noah v.1.25", group="Iterative Opmode")
public class alpha extends OpMode{

    private Definitions robot = new Definitions();



	// Op Mode Essential Variables

    private ElapsedTime runtime = new ElapsedTime(); // Defines the Up Time of the program
    private DcMotor leftBack = null; // Define Back Left Motor
    private DcMotor leftFront = null; // Define Front Left Motor
    private DcMotor rightBack = null; // Define Back Right Motor
    private DcMotor rightFront = null; // Define Front Right Motor
    private DcMotor arm_1 = null;
    private DcMotor arm_2 = null;
    private DcMotor armReel = null;
   	private double slow = 1;
    private double comp = 0;


    //Driver starts the program


    @Override
    public void init() { // Initialize all components

        leftBack  = hardwareMap.get(DcMotor.class, "leftBack"); // Initialize Left Drive
        leftFront = hardwareMap.get(DcMotor.class, "leftFront"); // Define Right Drive
        rightBack  = hardwareMap.get(DcMotor.class, "rightBack"); // Define Left Drive
        rightFront  = hardwareMap.get(DcMotor.class, "rightFront"); // Define Left Drive
        arm_1 = hardwareMap.get(DcMotor.class, "ArmRight"); // Define Right Arm
        arm_2 = hardwareMap.get(DcMotor.class, "ArmLeft"); // Define Left Arm
        armReel = hardwareMap.get(DcMotor.class, "armReel");

    }


    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {



		if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            slow = 0.2;
        }
        else if (!gamepad1.right_bumper && gamepad1.left_bumper) {
            slow = (2/5);
        }
		else if (gamepad1.right_bumper && gamepad1.left_bumper){
        	slow = 2;
		}
        else {
            slow = 1;
        }

		double DFR = Range.clip(slow*0.5*(-gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);//to 1 making sure they don't burn out.
        double DFL = Range.clip(slow*0.5*(gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1); //This will clip the outputs to the motors
        double DBR = Range.clip(slow*0.5*(-gamepad1.left_stick_y +gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);
        double DBL = Range.clip(slow*0.5*(gamepad1.left_stick_y +gamepad1.left_stick_x - gamepad1.right_stick_x),-1,1);

        rightFront.setPower(DFR);
		leftFront.setPower(DFL);
        rightBack.setPower(DBR);
        leftBack.setPower(DBL);



		if(gamepad2.a){
			comp = 0.1;
		} else {
			comp = 0;
		}

        double AR = comp+((0.1*-gamepad2.right_stick_x)+(0.15*-gamepad2.right_stick_y)); //idk what im doing -Noah
        double AL = -comp+((0.1*-gamepad2.left_stick_x)+(0.15*gamepad2.right_stick_y));

        arm_1.setPower(AR);
        arm_2.setPower(AL);



        /*
         * Reel Arm Controller
         */

        double V6 = -gamepad2.left_stick_y; // Reverse the value of the stick in order to provide a useable control. Pulling down makes the reel go up and vice versa.
        armReel.setPower(V6);





    }


    @Override
    public void stop() {
    }
}
