package algo;

import model.HexagonBox;
import model.MeetPointsBox;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ViruZ on 28.07.2016.
 */
public class PathFactory {
    private HexagonBox hexagonBox;
    private MeetPointsBox meetPointsBox;
    private double r;
    private ArrayList<Point> points;
    private ArrayList<ArrayList<Point>> answers;


    public void setData(HexagonBox hexagonBox, MeetPointsBox meetPointsBox, double r, ArrayList<Point> points){
        this.hexagonBox = hexagonBox;
        this.meetPointsBox = meetPointsBox;
        this.r = r;
        this.points = points;
        startFind();
    }

    public ArrayList<ArrayList<Point>> getPath(){
        return answers;
    }

    private void startFind(){

    }

}
