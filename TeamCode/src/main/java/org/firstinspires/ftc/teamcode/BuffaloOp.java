/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * chsrobotics Buffalo Wings
 * TeleOp Program 2017
 *
 *  ________________________________
 * |                                |
 * |           CHANGELOG            |
 * |________________________________|
 *
 *  Obtober 3rd 2017:
 *  - Added Four Wheel Drive variables
 *  - Updated MecanumX Movements
 *    a. Added Diagonal Movements
 *    b. Updated movement methods
 *  - Added various commenting.
 *
 *
 *  Use bumbers for speed reduction, 100% -> 50% -> 25% -> 5%
 *
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class BuffaloOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //
    // Standard motor variables
    //
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    //
    // Four Wheel Drive (FWD // Mecanum) variables
    //
    public DcMotor leftMotor1 = null;
    public DcMotor leftMotor2 = null;
    public DcMotor rightMotor1 = null;
    public DcMotor rightMotor2 = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        //leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        //rightDrive = hardwareMap.get(DcMotor.class, "right_drive");


        //leftDrive.setDirection(DcMotor.Direction.FORWARD);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait driver to press PLAY
        waitForStart();
        runtime.reset();

        // Run until driver presses STOP
        while (opModeIsActive()) {

            //
            // Standard Drive
            //



            double leftPower;
            double rightPower;

            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            //leftDrive.setPower(leftPower);
            //rightDrive.setPower(rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();



            leftMotor1 = hardwareMap.dcMotor.get("frontDriveLeft");
            leftMotor2 = hardwareMap.dcMotor.get("backDriveLeft");
            rightMotor1 = hardwareMap.dcMotor.get("frontDriveRight");
            rightMotor2 = hardwareMap.dcMotor.get("backDriveRight");

            double i = 100;
            if(gamepad1.right_bumper == true) {

                if(i == 0)
                {
                    i = 100;
                }
                else
                {
                    i = i - 25;
                }

                leftMotor1.setPower(i);
                leftMotor2.setPower(i);
                rightMotor1.setPower(i);
                rightMotor2.setPower(i);
                gamepad1.right_bumper = false;
            }

            // Strafe (Move Right) RIGHT
            if(gamepad1.right_trigger == 1)

            {

                leftMotor1.setDirection(DcMotor.Direction.FORWARD);
                leftMotor1.setPower(1);

                leftMotor2.setDirection(DcMotor.Direction.REVERSE);
                leftMotor2.setPower(1);

                rightMotor1.setDirection(DcMotor.Direction.REVERSE);
                rightMotor1.setPower(1);

                rightMotor2.setDirection(DcMotor.Direction.FORWARD);
                rightMotor2.setPower(1);

            }
            // Strafe (Move Left) LEFT
            else if (gamepad1.left_trigger == 1)

            {
                leftMotor1.setDirection(DcMotor.Direction.REVERSE);
                leftMotor1.setPower(1);

                leftMotor2.setDirection(DcMotor.Direction.FORWARD);
                leftMotor2.setPower(1);

                rightMotor1.setDirection(DcMotor.Direction.FORWARD);
                rightMotor1.setPower(1);

                rightMotor2.setDirection(DcMotor.Direction.REVERSE);
                rightMotor2.setPower(1);
            }

            else if(gamepad1.left_trigger == 0)

            {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }

            else if(gamepad1.right_trigger == 0)

            {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }

            else if(gamepad1.left_bumper == false)

            {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }

            else if(gamepad1.right_bumper == false)

            {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }


        }
    }
}
