package com.team2383.robot;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;


import com.team2383.ninjaLib.DPadButton;
import com.team2383.ninjaLib.DPadButton.Direction;
import com.team2383.ninjaLib.Gamepad;
import com.team2383.ninjaLib.LambdaButton;
import com.team2383.ninjaLib.OnChangeButton;
import com.team2383.ninjaLib.SetState;
import com.team2383.ninjaLib.Values;
import com.team2383.ninjaLib.WPILambdas;
import com.team2383.robot.subsystems.Fangs;
import com.team2383.robot.subsystems.Feeder;
import com.team2383.robot.commands.AutoDriveStraight;
import com.team2383.robot.commands.MoveFangs;

import com.team2383.robot.commands.MoveFangs;
import com.team2383.robot.commands.PrecisionDrive;

import com.team2383.robot.commands.TeleopDriveStraight;

import com.team2383.robot.subsystems.Climber;
import com.team2383.ninjaLib.SetState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;


import static com.team2383.robot.HAL.fangs;
import static com.team2383.robot.HAL.feeder;
import static com.team2383.robot.HAL.climber;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

/*
 * OI Controls:
 * 
 *  Driver->
 * 		Arcade drive, left stick throttle, right stick turn
 * 		Shooting ->
 * 			Vision: LT aim, RT shoot
 * 		DriveStraight -> right shoulder
 * 		Precision Drive -> left shoulder
 * 		Shift -> push down on sticks, left low, right high
 *	
 *	Operator stick 1 ->
 *		Hopper controls: button 8 toggle on/off, button 10 unjam
 *		Feeder controls: button 3 in, 4 out
 *		Climber controls: button 6
 *		Gear Door controls: button 5
 *		Gear Flap controls: button 12
 *
 *	Operator stick 2 (tuning stick)
 *		Turret: Twist stick
 *		Distance Presets: buttons 8,10,11 (left row of side buttons) close to far
 *		Flywheel controls: Thumb spool (2), trigger shoot (1)
 *		Tuning Controls: buttons 7,9 (tune up/down)
 *
 *
 */
@SuppressWarnings("unused")
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.

	/* Sticks functions */

	private static DoubleUnaryOperator inputExpo = (x) -> {
		return Constants.inputExpo * Math.pow(x, 3) + (1 - Constants.inputExpo) * x;
	};

	private static DoubleUnaryOperator deadband = (x) -> {
		return Math.abs(x) > Constants.inputDeadband ? x : 0;
	};
	
	//Driver
	public static Gamepad driver = new Gamepad(0);

	public static Button shiftDown = driver.getLeftStickClick();
	public static Button shiftUp = driver.getRightStickClick();
	
	public static Button driveStraight = driver.getRightShoulder();
	public static Button backUp = driver.getButtonB();
	
	public static Button visionAim = driver.getLeftTriggerClick();
	public static Button visionShoot = driver.getRightTriggerClick();
	
	public static Button toggleAutoShift = driver.getButtonA();
	

	public static DoubleSupplier throttle = () -> deadband.applyAsDouble(driver.getLeftY());
	public static DoubleSupplier turn = () -> deadband.applyAsDouble(driver.getRightX());
	
	public static Button precisionDrive = driver.getLeftShoulder();
	
	
	//Operator
	public static Joystick operator = new Joystick(2);

	
	
	
	public static Button climb1 = new JoystickButton(operator, 6);
	
	
	
	// All-in-one
	public static Gamepad advancedOperator = new Gamepad(4);
	
	public static Button climb2 = new JoystickButton(advancedOperator, 3);
	public static Button leftTrigger = advancedOperator.getLeftTriggerClick();
	public static Button rightTrigger = advancedOperator.getRightTriggerClick();
	
	public static Button leftBumper = advancedOperator.getLeftShoulder();
	public static Button rightBumper = advancedOperator.getRightShoulder();


	
	public OI() {
		
		precisionDrive.whileHeld(new PrecisionDrive(throttle, turn, toggleAutoShift::get, shiftDown::get, shiftUp::get));
		driveStraight.whileHeld(new TeleopDriveStraight(throttle));
		backUp.whenPressed((new AutoDriveStraight(() -> 0.5 , 0.25)));
		
		
		
		
		
		climb1.whileHeld(new SetState<Climber.State>(climber, Climber.State.CLIMB, Climber.State.STOPPED));
		climb2.whileHeld(new SetState<Climber.State>(climber, Climber.State.CLIMB, Climber.State.STOPPED));
		
		leftTrigger.whileHeld(new MoveFangs(-0.4));
		rightTrigger.whileHeld(new MoveFangs(0.4));
		
		leftBumper.whileHeld(new SetState<Feeder.State>(feeder, Feeder.State.UNFEED, Feeder.State.STOPPED));
		rightBumper.whileHeld(new SetState<Feeder.State>(feeder, Feeder.State.FEED, Feeder.State.STOPPED));
		
	}
}
