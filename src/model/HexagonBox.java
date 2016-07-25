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
    private RandevuBox randevuBox;
    //int[][] adjMatrix;

    public HexagonBox(int r, int width, int height) {
        this.r = r;
        this.width = width;
        this.height = height;
        hexBox = new ArrayList<>();
        initBox();
    }

    private void initBox() {
        int x = 0;
        int y = 0;
        int num = 0;
        int acc = 0;
        int temp = (int) (Math.sqrt(3) * r);

        while (y < height) {
            while (x < width) {
                num++;
                Hexagon hex = new Hexagon(new Point(x, y), r, num);
                hex.init();

                hexBox.add(hex);
                x += temp;
            }

            if (acc % 2 == 0) {
                x = -temp / 2;
            } else {
                x = 0;
            }
            acc++;
            y += (1.5 * r);
        }
        initRandevuBox();
    }

    private void initRandevuBox(){
        randevuBox = new RandevuBox(hexBox);
        updateHexagons();
    }

    private void updateHexagons(){
        ArrayList<RandevuPoint> rdPs = randevuBox.getRandevuPoints();
        //System.out.println(rdPs.size());
        for (int i = 0; i < hexBox.size() ; i++) {
            Hexagon hex = hexBox.get(i);
            ArrayList<RandevuPoint> newRanPts = new ArrayList<>();
            Point[] pts = hex.getPoints();

            for (int j = 0; j < pts.length ; j++) {
                Point p = pts[j];
                for (RandevuPoint rp : rdPs){
                    //System.out.println("Hui");
                    if (rp.sameCoord(p)){
                        //System.out.println("Pizda");
                        newRanPts.add(new RandevuPoint(p, rp.getNeighbors()));
                    }

                }
            }
            hexBox.set(i, new Hexagon(newRanPts));
        }
    }

    public ArrayList<Hexagon> getHexBox() {
        return hexBox;
    }
    public RandevuBox getRandevuBox(){
        return randevuBox;
    }
}




