package com.team2384.robot.subsystems;

import com.ctre.CANTalon;
import com.team2384.ninjaLib.SetState.StatefulSubsystem;
import com.team2384.robot.Constants;

/*
 * This subsystem includes the conveyor streaming balls into the shooter
 */


public class Feeder extends com.team2384.ninjaLib.SetState.StatefulSubsystem<Feeder.State> {

	private CANTalon feeder = new CANTalon(Constants.kFeederTalonID);
	private State state = State.STOPPED;
	
	public Feeder(){
		feeder.reverseOutput(true);
	}
	
	public enum State {
		FEED, UNFEED, STOPPED
	}
	
	public void feed(){
		feeder.enableBrakeMode(false);
			feeder.set(1);
	}
	public void unfeed(){
		feeder.enableBrakeMode(false);
			feeder.set(-0.4);
	}
	public void stop() {
		feeder.enableBrakeMode(true);
		feeder.set(0);
	}

	@Override
	public void setState(State state) {
		switch (state) {
			case FEED:
				feed();
				break;
				
			case UNFEED:
				unfeed();
				break;
	
			default:
			case STOPPED:
				stop();
				break;
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
