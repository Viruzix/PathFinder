package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ovod on 24.07.16.
 */
public class HexagonBox {
    private ArrayList<Hexagon> hexBox;
    private int r;
    private int width;
    private int height;
    //int[][] adjMatrix;

    public HexagonBox(int r, int width, int height) {
        this.r = r;
        this.width = width;
        this.height = height;
        hexBox = new ArrayList<>();
        initBox();
    }

    private void initBox() {
        int temp = (int) (Math.sqrt(3) * r);
        int x = temp / 2;
        int y = r;
        int num = 0;
        int acc = 0;


        while (y < height) {
            while (x < width) {
                num++;
                Hexagon hex = new Hexagon(new Point(x, y), r, num);
                hex.init();
                hexBox.add(hex);
                ArrayList<MeetPoint> mpoints = new ArrayList<>();
                Point[] points = hexBox.get(hexBox.size() - 1).getPoints();
                for (Point p : points) {
                    ArrayList<Hexagon> hexgs = new ArrayList<>();
                    for (int i = hexBox.size() - 1; i > 0; i--) {
                        Hexagon hexTemp = hexBox.get(i);
                        if (hexTemp.containsPoint(p)){
                            hexgs.add(hexTemp);
                        }
                    }
                    mpoints.add(new MeetPoint(p, hexgs));
                }
                hexBox.get(hexBox.size() - 1).setMeetPoints(mpoints);

                x += temp;
            }

            if (acc % 2 == 0) {
                x = temp;
            } else {
                x = temp / 2;
            }
            acc++;
            y += (1.5 * r);
        }

    }




    public ArrayList<Hexagon> getHexBox() {
        return hexBox;
    }

}




