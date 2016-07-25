package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by ovod on 25.07.16.
 */
public class RandevuBox {
    private ArrayList<RandevuPoint> randevuPoints;

    public RandevuBox(ArrayList<Hexagon> hexagonBox) {
        createFromHB(hexagonBox);
    }


    private void createFromHB(ArrayList<Hexagon> hexBox) {
        HashSet<RandevuPoint> tempRandevuPoints = new HashSet<>();
        for (Hexagon hex : hexBox) {

            Point[] hexPoints = hex.getPoints();
            for (Point p : hexPoints) {
                ArrayList<Hexagon> neigbours = new ArrayList<>();
                for (Hexagon hexTemp : hexBox) {
                    if (hexTemp.containsPoint(p)) {
                        neigbours.add(hexTemp);
                    }
                }

                RandevuPoint rp = new RandevuPoint((int) p.getX(), (int) p.getY(), neigbours);
                if (!tempRandevuPoints.contains(rp)) {
                    tempRandevuPoints.add(rp);
                }

            }
        }

        randevuPoints = new ArrayList<>(tempRandevuPoints);


    }

    public ArrayList<RandevuPoint> getRandevuPoints() {
        return randevuPoints;
    }
}
