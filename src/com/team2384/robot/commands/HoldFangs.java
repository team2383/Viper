package com.team2384.robot.commands;

import static com.team2384.robot.HAL.fangs;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class HoldFangs extends Command{

    public HoldFangs() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(fangs);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	fangs.holdPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
