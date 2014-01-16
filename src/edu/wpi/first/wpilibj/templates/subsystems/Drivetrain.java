/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.BasicTankDrive;

/**
 *
 * @author Robotics Club
 */
public class Drivetrain extends Subsystem {
    
    //The drivetrain motors
    Victor leftMotor = new Victor(RobotMap.leftMotorPort);
    Victor rightMotor = new Victor(RobotMap.rightMotorPort);
    
    //Variables related to speed controlling
    double speedMin = 0.0;
    double speedMax = 0.75;
    double speedBoost = 0.25;
    
    //Used to prevent the robot from moving
    boolean parkingBrake = false;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BasicTankDrive());
    }
    
    /*
    Sets the speed of the left motor to desiredSpeed
    */
    public void setLeftSpeed(double desiredSpeed) {
        setLeftSpeed(desiredSpeed, false);
    }
    
    /*
    Sets the speed of the right motor to desiredSpeed
    */
    public void setRightSpeed(double desiredSpeed) {
        setRightSpeed(desiredSpeed, false);
    }
    
    /*
    Sets the speed of the left motor to desiredSpeed with optional boost
    */
    public void setLeftSpeed(double desiredSpeed, boolean boost) {
        if (!parkingBrake) {
            double drivingSpeed = scaleSpeed(desiredSpeed);
            if (boost) drivingSpeed = applyBoost(drivingSpeed);
            leftMotor.set(drivingSpeed);    
        }
        SmartDashboard.putNumber("leftMotorSpeed", leftMotor.get());
    }
    
    /*
    Sets the speed of the right motor to desiredSpeed with optional boost
    */
    public void setRightSpeed(double desiredSpeed, boolean boost) {
        if (!parkingBrake) {
            double drivingSpeed = scaleSpeed(desiredSpeed);
            if (boost) drivingSpeed = applyBoost(drivingSpeed);
            rightMotor.set(drivingSpeed);    
        }
        SmartDashboard.putNumber("rightMotorSpeed", rightMotor.get());
    }
    
    /*
    Toggles the parking brake, which will prevent the robot from moving
    */
    public boolean toggleParkingBrake() {
        parkingBrake = (!parkingBrake); //Inverts parking brake
        return parkingBrake;
    }
    
    /*
    Scales speed down to fit within our speed range.
    */
    private double scaleSpeed(double speedToScale) {
        return speedToScale * speedMax;
    }
    
    /**
     * Limit motor values to the -1.0 to +1.0 range.
     * @param num
     * @return 
     */
    public double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }
    
    private double applyBoost(double speedBeforeBoost) {
        if (speedBeforeBoost < -0.25) {
            return speedBeforeBoost - this.speedBoost;
        } else if (speedBeforeBoost > 0.25) {
            return speedBeforeBoost + this.speedBoost;
        } else {
            return speedBeforeBoost;
        }
    }

//    private double applyBoost(double speedBeforeBoost, double rawBoost) 
//        double normalizedBoost    
//            
//        if (speedBeforeBoost < -0.25) {
//            return speedBeforeBoost - this.speedBoost;
//        } else if (speedBeforeBoost > 0.25) {
//            return speedBeforeBoost + this.speedBoost;
//        } else {
//            return speedBeforeBoost;
//        }
//    }
}
