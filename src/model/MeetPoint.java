package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ovod on 25.07.16.
 */
public class MeetPoint {

    private int x;
    private int y;


    private ArrayList<Hexagon> neighbors;

    public MeetPoint(int x, int y, ArrayList<Hexagon> neighbors) {
        this.x = x;
        this.y = y;
        this.neighbors = neighbors;
    }

    public MeetPoint(Point p, ArrayList<Hexagon> neighbors){
        this.x = p.x;
        this.y = p.y;
        this.neighbors = neighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Hexagon> getNeighbors() {
        return neighbors;
    }

    public Point toPoint(){
        return new Point(x,y);
    }

    public boolean sameCoord(Point p){
        return ((int)p.getX()) == x && ((int)p.getY()) == y;
    }
}
