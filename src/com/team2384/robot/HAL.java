package com.team2384.robot;

import com.kauailabs.navx.frc.AHRS;
import com.team2384.robot.subsystems.Drivetrain;
import com.team2384.robot.subsystems.Fangs;
import com.team2384.robot.subsystems.Feeder;
import com.team2384.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.SPI;

public class HAL {
	
	// subsystems
	public static Drivetrain drivetrain = new Drivetrain();
	public static Fangs fangs = new Fangs();
	public static Climber climber = new Climber();
	public static Feeder feeder = new Feeder();
	public static AHRS navX = new AHRS(SPI.Port.kMXP);
}
