/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.SimpleRobot;	/* This is based on the SIMPLE ROBOT template */
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**************************************************************************************
* TODO:  the joystick buttons do not work well enough to use as toggles. 
* We need to have the button pressed to enable a mode (e.g. TWIST),
* and then use the trigger or something to reset everything back to normal mode.
*
* TODO: Autonomous mode only works for the first time. It just continues for the second one.
* I can't figure out why. The timer may not be controlled by the program,
* but by the "Enable" button on th edashboard.
*
* TODO: There is one error message (I have to write down) about not enough data being sent.
***************************************************************************************/

public class Simple4 extends SimpleRobot {
    

    //CONSTANTS
    //robot mode constants. Start with B for BEAL, to avoid potential conflicts
    //they can be any multple of 2. That way they can be added. ** NO. This would get too complicated.
    static final int BDRIVE	= 1;	//opposite of BSTOP and BFAST
    static final int BSTOP	= 2;		
    static final int BARCADE	= 4;
    static final int BTANK	= 8;
    static final int BTWIST	= 16;	// for twisting the joystick (z-axis). Automatically in arcade mode
    static final int BFLIP	= 32;	// for driving backwards. Automatically in arcade mode.
    static final int BFAST	= 64;   //tank drive is always in fast mode.

    //autonomous mode constants (time1 and speed1, then time2 and speed2)
    static final double AUTOTIME1 = 3.0;	
    static final double AUTOTIME2 = 1.0;
    static final double AUTOSPEED1 = 0.7;
    static final double AUTOSPEED2 = 0.4;

    static final double SLOWSPEED = 0.7;  //multiplier for non-speedboost
    static final double SLOWTWIST = 0.5;  //multiplier for non-speedtwist

    int driveMode = BDRIVE;  //BDRIVE or BSTOP or BTWIST or BFLIP
    int stickMode = BARCADE; //BARCADE or BTANK
    int speedMode = BDRIVE;  //BDRIVE or BFAST

    //object definions
    Joystick leftStick, rightStick;
    Victor vicLeft, vicRight;
    RobotDrive chassis;
    JoystickButton  leftStickBtn1,leftStickBtn2,leftStickBtn3,
                    leftStickBtn4,leftStickBtn5,leftStickBtn6,
                    leftStickBtn7,leftStickBtn8,leftStickBtn9,
                    leftStickBtn10,leftStickBtn11,leftStickBtn12;

    
    /* initialization */
    protected void robotInit() {
        leftStick = new Joystick(1);
        rightStick = new Joystick(2);
        vicLeft = new Victor(1);
        vicRight = new Victor(2);
        chassis = new RobotDrive(vicLeft, vicRight);
        leftStickBtn1 = new JoystickButton(leftStick,1);
        leftStickBtn2 = new JoystickButton(leftStick,2);
        leftStickBtn3 = new JoystickButton(leftStick,3);
        leftStickBtn4 = new JoystickButton(leftStick,4);
        leftStickBtn5 = new JoystickButton(leftStick,5);
        leftStickBtn6 = new JoystickButton(leftStick,6);
        leftStickBtn7 = new JoystickButton(leftStick,7);
        leftStickBtn8 = new JoystickButton(leftStick,8);
        leftStickBtn9 = new JoystickButton(leftStick,9);
        leftStickBtn10 = new JoystickButton(leftStick,10);
        leftStickBtn11 = new JoystickButton(leftStick,11);
        leftStickBtn12 = new JoystickButton(leftStick,12);
    }
    
    

    /* This function is called once each time the robot enters operator control. */
    public void operatorControl() {
        chassis.setSafetyEnabled(true);
        double joyX,joyY,joyZ;
           
        while (isOperatorControl() && isEnabled()) {
           
            joyX = leftStick.getX();//x, y and z values for joystick 
            joyY = leftStick.getY();
            joyZ = leftStick.getZ();
	
            /****************************************************
            *  set all of the robot modes based on the buttons  *
            ****************************************************/
	    //button 2: drive fast when held down
	    speedMode = (leftStickBtn2.get())? BFAST:BDRIVE;
           
            //buttons 3 and 4 turn driving off and on.
            if(leftStickBtn3.get()) driveMode = BSTOP;
            if(leftStickBtn4.get()) driveMode = BDRIVE;

	    //button 11 toggles TANK/ARCADE mode
            if(leftStickBtn11.get()) stickMode = (stickMode==BDRIVE)? BTANK : BDRIVE;

	    //if(leftStickBtn7.get()) driveMode = BTWIST; //what turns TWIST OFF?
	    //if(leftStickBtn9.get()) driveMode = BFLIP;  //what turns FLIP OFF?
	    //make these toggle between normal mode and special mode
	    if(leftStickBtn7.get()) driveMode = (driveMode==BDRIVE)? BTWIST : BDRIVE;
	    if(leftStickBtn9.get()) driveMode = (driveMode==BDRIVE)? BFLIP : BDRIVE; 

	    /****************************************************
            *  do the various driving types based on the modes  *
            ****************************************************/
	   switch (speedMode) {
		case BDRIVE:
                    joyX = leftStick.getX()*SLOWSPEED;
	            joyY = leftStick.getY()*SLOWSPEED;
	            joyZ = leftStick.getZ()*SLOWSPEED;
		    break;
		case BFAST: break;
           }  
           switch (driveMode) {             
	       case BSTOP: continue; 
                    //break;
	       case BDRIVE: 
		    if (stickMode == BTANK) 
                        chassis.tankDrive(leftStick, rightStick);  //this is not controled by speed boost
		    else 
                        chassis.arcadeDrive(joyY,joyX);	//the parameters must be in this order.
		    break;
	       case BTWIST:        
		    chassis.arcadeDrive(0.0, joyZ * SLOWTWIST);  
		    break;
               case BFLIP: chassis.arcadeDrive(-joyY,-joyX); 
		    break;

           }

           //System.out.println(""+driveMode + " "+stickMode + " " +speedMode);

           Timer.delay(0.001);
        }
    }
    
    /* This function is called once each time the robot enters autonomous mode. */
    public void autonomous() {
       chassis.setSafetyEnabled(false);
       Timer t1 = new Timer();
       t1.start();
       while(t1.get() < AUTOTIME1) {
           chassis.tankDrive(AUTOSPEED1, AUTOSPEED1);
           //System.out.println("time="+t1.get());
       }
       //t1.reset();
       //t1.start();
       while(t1.get() < AUTOTIME1 + AUTOTIME2) {
           chassis.tankDrive(AUTOSPEED2, AUTOSPEED2);           
       }
       t1.stop();
       chassis.setSafetyEnabled(false);
    }
    
    /*This function is called once each time the robot enters test mode.   */
    public void test() {}
}
