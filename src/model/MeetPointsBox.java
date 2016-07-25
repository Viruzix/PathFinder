package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ovod on 25.07.16.
 */
public class MeetPointsBox {
    private ArrayList<MeetPoint> meetPoints;

    public MeetPointsBox(HexagonBox hexagonBox) {
        meetPoints = new ArrayList<>();
        createFromHB(hexagonBox);
    }


    private void createFromHB(HexagonBox hexagonBox) {
        ArrayList<Hexagon> hexBox = hexagonBox.getHexBox();
        for (int i = 0; i < hexBox.size(); i++) {
            Hexagon tempHex = hexBox.get(i);
            Point[] ps = tempHex.getPoints();
            ArrayList<MeetPoint> mps = tempHex.getRandevuPoint();
            for (int j = 0; j < ps.length; j++) {
                Point p = ps[j];
                for (MeetPoint mp : meetPoints) {
                    if (mp.getX() != p.getX() && mp.getY() != p.getY()) {
                        meetPoints.add(new MeetPoint(p,mps.get(j).getNeighbors()));
                    }
                }
            }
        }


    }

    public ArrayList<MeetPoint> getMeetPoints() {
        return meetPoints;
    }
}
