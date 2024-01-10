// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  public static class Hardware {
    WPI_TalonFX leftMaster, rightMaster, leftSlave1, rightSlave1, leftSlave2, rightSlave2;

    public Hardware(WPI_TalonFX leftMaster, WPI_TalonFX rightMaster, WPI_TalonFX leftSlave1, WPI_TalonFX rightSlave1, WPI_TalonFX leftSlave2, WPI_TalonFX rightSlave2) {
      this.leftMaster = leftMaster;
      this.rightMaster = rightMaster;
      this.leftSlave1 = leftSlave1;
      this.rightSlave1 = rightSlave1;
      this.leftSlave2 = leftSlave2;
      this.rightSlave2 = rightSlave2;
    }
  }

  private WPI_TalonFX m_lMasterMotor, m_rMasterMotor, m_lSlaveMotor1, m_rSlaveMotor1, m_lSlaveMotor2, m_rSlaveMotor2;

  private DifferentialDrive m_drivetrain;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(Hardware drivetrainHardware) {
    this.m_lMasterMotor = drivetrainHardware.leftMaster;
    this.m_rMasterMotor = drivetrainHardware.rightMaster;
    this.m_lSlaveMotor1 = drivetrainHardware.leftSlave1;
    this.m_rSlaveMotor1 = drivetrainHardware.rightSlave1;
    this.m_lSlaveMotor2 = drivetrainHardware.leftSlave2;
    this.m_rSlaveMotor2 = drivetrainHardware.rightSlave2;

    this.m_drivetrain = new DifferentialDrive(m_lMasterMotor, m_rMasterMotor);

    m_rSlaveMotor1.setInverted(true);
    m_rSlaveMotor2.setInverted(true);
    m_rMasterMotor.setInverted(true);

    m_lSlaveMotor1.follow(m_lMasterMotor);
    m_rSlaveMotor1.follow(m_rMasterMotor);
    m_lSlaveMotor2.follow(m_lMasterMotor);
    m_rSlaveMotor2.follow(m_rMasterMotor);
  }

  public static Hardware initializHardware() {
    Hardware h = new Hardware(
        new WPI_TalonFX(Constants.DriveHardware.LEFT_MASTER),
        new WPI_TalonFX(Constants.DriveHardware.RIGHT_MASTER),
        new WPI_TalonFX(Constants.DriveHardware.LEFT_SLAVE1),
        new WPI_TalonFX(Constants.DriveHardware.RIGHT_SLAVE1),
        new WPI_TalonFX(Constants.DriveHardware.LEFT_SLAVE2),
        new WPI_TalonFX(Constants.DriveHardware.RIGHT_SLAVE2));

    return h;
  }

  public void set(double speed, double turn) {
    m_drivetrain.arcadeDrive(speed, turn);
  }

  public void stop() {
    set(0, 0);
  }

  public void teleop(double speed, double turn) {
    m_drivetrain.arcadeDrive(speed, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
