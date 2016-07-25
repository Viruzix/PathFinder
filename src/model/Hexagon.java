package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ovod on 23.07.16.
 */
public class Hexagon {
    private Point center;
    private int r;
    private int num;
    private Point[] points = new Point[6];
    private ArrayList<MeetPoint> meetPoints;

    public Hexagon(Point center, int r, int num) {
        this.center = center;
        this.r = r;
        this.num = num;

    }

    public Hexagon(ArrayList<MeetPoint> randPoints, int num) {
        this.meetPoints = randPoints;
        this.num = num;
        updateCenterPoint();
    }

    public void setMeetPoints(ArrayList<MeetPoint> meetPoints) {
        this.meetPoints = meetPoints;
    }

    private void updateCenterPoint() {

        int x = 0;
        int y = 0;
        for (MeetPoint rp : meetPoints) {
            x += rp.getX();
            y += rp.getY();
            //System.out.print(x + " " + y + "; ");
        }
        //System.out.println();
        center = new Point(x/6, y/6);
    }


    public void init() {
        int xc = (int) center.getX();
        int yc = (int) center.getY();
        int temp = (int) (Math.sqrt(3) / 2 * r);

        Point p1 = new Point(xc, yc - r);
        Point p2 = new Point(xc + temp, yc - r / 2);
        Point p3 = new Point(xc + temp, yc + r / 2);
        Point p4 = new Point(xc, yc + r);
        Point p5 = new Point(xc - temp, yc + r / 2);
        Point p6 = new Point(xc - temp, yc - r / 2);
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        points[4] = p5;
        points[5] = p6;
    }

    public Point[] getPoints() {
        return points;
    }

    public ArrayList<MeetPoint> getRandevuPoint() {
        return meetPoints;
    }

    public boolean containsPoint(Point p) {
        boolean flag = false;
        for (Point aVert : points) {
            //System.out.println(aVert.x + " " + aVert.y);
            if (aVert.equals(p)) {
                flag = true;
            }
        }

        return flag;
    }

    public int getNum() {
        return num;
    }

    public Point getCenter() {
        return center;
    }
}
