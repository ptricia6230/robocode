package edu.ufl.cise.cs1.robots;

import robocode.*;

import java.awt.Color;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.HitByBulletEvent;
import robocode.BulletMissedEvent;
import robocode.WinEvent;

public class TrashaBot extends TeamRobot
{
        //main method for every robot
        public void run() {

            //sets colors
            setBodyColor(Color.green);
            setGunColor(Color.green);
            setRadarColor(Color.green);
            setBulletColor(Color.green);
            setScanColor(Color.green);

            while(true) {
                //object array for velocity, and locations
                Object[] robotInfo = {(this.getX()), (this.getY()), (this.getVelocity())};
                //DON'T FORGET TO ADD TARGET COORDINATES WHEN YOU DO THAT TO ROBOT INFO

                setTurnRadarLeft(360);
                ahead(200);
                turnGunLeft(90);
                back(200);

                try {
                    if (this.getTime()% 3 == 0) {
                        //broadcasts location and velocity to other teammates
                        broadcastMessage(robotInfo);
                    }
                }
                catch (Exception e) {
                }

                execute();
            }
        }

        //what happens when robot sees another robot
         public void onScannedRobot(ScannedRobotEvent e) {
            //if other robot is teamate then you don't shoot
             if (isTeammate(e.getName())) {
                 return;
             }
             if (e.getBearing() == e.getHeading()) {
                 setAhead(80);
                 setFire(30);
             }
             else {
                 setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
                 setFire(30);
             }
         }

         //what happens when robots bullet misses
          public void onBulletMissed(BulletMissedEvent e) {
            setTurnLeft(90);
            setTurnGunRight(30);
            setAhead(50);
            fire(20);
          }

         //what happens when robot is hit by bullet
          public void onHitByBullet(HitByBulletEvent e) {
             setTurnLeft(90);
             setAhead(100);
             setTurnLeft(50);
             setFire(3);
           }

         //what happens when robot collides with wall
          public void onHitWall(HitWallEvent e) {
            back(100);
            turnRight(90);
            ahead(100);
          }

          //what happens when your robot collides with another robot
          public void onHitRobot(HitRobotEvent e) {
            if(isTeammate(e.getName())) {
                return;
            }
            else {
                fire(3);
            }
          }

        //what happens when robot wins
          public void onWin(WinEvent e) {
            //victory dance
             turnLeft(36000);

          }
}