
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.BasicArcadeDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public final Joystick leftStick = new Joystick(RobotMap.leftJoystickPort);
    public final Joystick rightStick = new Joystick(RobotMap.rightJoystickPort);
    
    Button rightStickButton1 = new JoystickButton(rightStick, 1),
                        rightStickButton2 = new JoystickButton(rightStick, 2),
                        rightStickButton3 = new JoystickButton(rightStick, 3),
                        rightStickButton4 = new JoystickButton(rightStick, 4),
                        rightStickButton5 = new JoystickButton(rightStick, 5),
                        rightStickButton6 = new JoystickButton(rightStick, 6),
                        rightStickButton7 = new JoystickButton(rightStick, 7),
                        rightStickButton8 = new JoystickButton(rightStick, 8),
                        rightStickButton9 = new JoystickButton(rightStick, 9),
                        rightStickButton10 = new JoystickButton(rightStick, 10),
                        rightStickButton11 = new JoystickButton(rightStick, 11),
                        rightStickButton12 = new JoystickButton(rightStick, 12);
    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    public OI() {
        //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
//        rightStickButton3.toggleWhenPressed(new BasicArcadeDrive());
        //rightStickButton3.whileHeld(new BasicArcadeDrive());
        rightStickButton3.toggleWhenPressed(new BasicArcadeDrive());
    }
    
}

