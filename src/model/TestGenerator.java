package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ovod on 27.07.16.
 */
public class TestGenerator {
    private String[] paths;
    private int numOfPaths;
    private int numOfPoints;
    private int width;
    private int height;

    public TestGenerator(int numOfPaths, int numOfPoints, int width, int height) {
        this.numOfPaths = numOfPaths;
        this.width = width;
        this.numOfPoints = numOfPoints;
        this.height = height;
        paths = new String[numOfPaths];
        generate();
    }

    private void generate() {
        for (int j = 0; j < numOfPaths; j++) {
            ArrayList<Point> startPoints = new ArrayList<>();
            ArrayList<Point> endPoints = new ArrayList<>();
            for (int i = 0; i < numOfPoints; i++) {
                startPoints.add(new Point((int) (Math.random() * width), (int) (Math.random() * height)));
                endPoints.add(new Point((int) (Math.random() * width), (int) (Math.random() * height)));
            }
            paths[j] = generateString(startPoints, endPoints);

        }

    }

    private String generateString(ArrayList<Point> startPoints, ArrayList<Point> endPoints) {
        String ans = "";
        for (int i = 0; i < startPoints.size(); i++) {
            String temp = (int)startPoints.get(i).getX() + " " + (int)startPoints.get(i).getY()
                    + " " + (int)endPoints.get(i).getX() + " " + (int)endPoints.get(i).getY() + " ";
            //System.out.println(temp);
            ans += temp;
        }
        return ans;
    }

    public String[] getPaths() {
        return paths;
    }
}
