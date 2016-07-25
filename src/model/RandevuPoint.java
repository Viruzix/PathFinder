package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ovod on 25.07.16.
 */
public class RandevuPoint {

    private int x;
    private int y;

    private ArrayList<Hexagon> neighbors;

    public RandevuPoint(int x, int y, ArrayList<Hexagon> neighbors) {
        this.x = x;
        this.y = y;
        this.neighbors = neighbors;
    }

    public RandevuPoint(Point p, ArrayList<Hexagon> neighbors){
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
        return p.x == x && p.y == y;
    }
}
