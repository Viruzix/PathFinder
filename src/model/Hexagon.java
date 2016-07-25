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
    private Point[] vert = new Point[6];
    private ArrayList<RandevuPoint> randPoints;

    public Hexagon(Point center, int r, int num) {
        this.center = center;
        this.r = r;
        this.num = num;

    }

    public Hexagon(ArrayList<RandevuPoint> randPoints){
        this.randPoints = randPoints;
    }


    public void init() {
        int xc = (int) center.getX();
        int yc = (int) center.getY();
        int temp = (int)(Math.sqrt(3)/2 * r);

        Point p1 = new Point(xc, yc - r);
        Point p2 = new Point(xc + temp, yc - r / 2);
        Point p3 = new Point(xc + temp, yc + r / 2);
        Point p4 = new Point(xc, yc + r);
        Point p5 = new Point(xc - temp, yc + r / 2);
        Point p6 = new Point(xc - temp, yc - r / 2);
        vert[0] = p1;
        vert[1] = p2;
        vert[2] = p3;
        vert[3] = p4;
        vert[4] = p5;
        vert[5] = p6;
    }

    public Point[] getPoints(){
        return vert;
    }

    public ArrayList<RandevuPoint> getRandevuPoint(){
        return randPoints;
    }

    public boolean containsPoint(Point p){
        boolean flag = false;
        for (Point aVert : vert) {
            //System.out.println(aVert.x + " " + aVert.y);
            if (aVert.equals(p)) {
                flag = true;
            }
        }

        return flag;
    }

    public int getNum(){
        return num;
    }
}
