package com.team2384.robot.commands;


import static com.team2384.robot.HAL.navX;
import static com.team2384.robot.HAL.drivetrain;
import static com.team2384.robot.HAL.fangs;

import com.team2384.auto.TestDrive;
import com.team2384.robot.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GeneralPeriodic extends Command {


	// Called just before this Command runs the first time
    protected void initialize() {
		//SmartDashboard.putData("little flywheel", shooter.littleFlywheel);
		//SmartDashboard.putData("big flywheel", shooter.bigFlywheel);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	SmartDashboard.putNumber("Gyro Yaw", navX.getYaw());
    
    	SmartDashboard.putNumber("Gyro Pitch", navX.getPitch());
    	SmartDashboard.putNumber("Gyro Roll", navX.getRoll());
    
    	SmartDashboard.putNumber("drive straight time throttle", Constants.kdriveStraightTestThrottle);
    	SmartDashboard.putNumber("Drive Straight time time", Constants.kdriveStraightTestTime);
    	
    	SmartDashboard.putNumber("drivetrain left rotations", drivetrain.leftMaster.getPosition());
    	SmartDashboard.putNumber("drivetrain right rotations", drivetrain.rightMaster.getPosition());
    	SmartDashboard.putNumber("drivetrain rotations", drivetrain.getRotations());
    	SmartDashboard.putNumber("drivetrain inches", drivetrain.getInches());
    	
    	
    	SmartDashboard.putNumber("fang position", fangs.get());
 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
