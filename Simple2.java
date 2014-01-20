/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.*;
/* 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

/*****************************************************************************
Name: 		SIMPLE2 (Simple Robot)		 	Version 2.0
Purpose: 	set up a basic ARCADE drive for 1 joystick
ToDo:		set up buttons. 
		Make one button start Arcade mode, make another start Tank mode

*****************************************************************************/

//QUESTIONS:
//1. What does the above mean about "updating manifest"?
//2. How to we make the joystick work properly? The axes are reversed.



public class Simple2 extends SimpleRobot {

    RobotDrive myDrive;
    Joystick joy1;
    Victor v1, v2;
   
    public void robotInit() {
        v1 = new Victor(1);
        v2 = new Victor(2);
        joy1 = new Joystick(1);
        myDrive = new RobotDrive(v1,v2);
    }

     //This function is called once each time the robot enters operator control.
    public void operatorControl() {
           while (isAutonomous() && isEnabled()) {
               myDrive.arcadeDrive(joy1);
               Timer.delay(0.1);
           }           
    }

    //This function is called once each time the robot enters autonomous mode.
    public void autonomous() {       
    }
    
    //This function is called once each time the robot enters test mode.
    public void test() {    
    }
} //end of Simple2 class
