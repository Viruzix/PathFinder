package controller;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.*;

import java.awt.*;
import java.util.ArrayList;


/**
 * В данном классе после нажатия клавиши Init инициализируются HexagonBox и MeetPointsBox.
 * HexagonBox - ArrayList<Hexagon>
 * Hexagon - ArrayList<MeetPoint> имеет ссылки на все точки в гексагоне(MeetPoint)
 * MeetPoint имеет ссылки на три ближайщих гексагона
 */
public class Controller {

    public Button checkBtn;
    public TextField coords;
    public TextArea info;
    public Canvas map;
    public Button initBtn;
    public TextField radLabel;
    public SplitPane splitPane;

    private int mapWidth;
    private int mapHeight;

    public HexagonBox hexagonBox;
    public MeetPointsBox meetPointsBox;



    public void handleCheck(ActionEvent actionEvent) {
        String data = coords.getText();
        info.appendText(data + "\n");
        coords.setText("");
        ArrayList<Point> ps = parser(data);
        TestGenerator ts = new TestGenerator(4, 2, 500, 500);
        //ArrayList<Point> ps1 = parser(ts.generateString());
        /*for (int i = 0; i < ps.size() - 1; i++) {
            drawLine(ps.get(i), ps.get(i + 1));
        }*/

        String[] testPaths = ts.getPaths();
        for(String st: testPaths){
            ArrayList<Point> pts = parser(st);
            for (int i = 0; i < pts.size() - 1 ; i++) {
                drawLine(pts.get(i), pts.get(i + 1));
            }
        }


    }


    private void drawLine(Point p1, Point p2) {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                Color.DARKGRAY, Color.PURPLE, Color.AQUAMARINE, Color.BURLYWOOD};
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setLineWidth(1.5);
        Color temp = colors[(int) ((Math.random() * 10000) % 8)];
        gc.setFill(temp);
        gc.setStroke(temp);
        gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
        gc.fillOval(p1.x - 4, p1.y - 4, 8, 8);
        gc.fillOval(p2.x - 4, p2.y - 4, 8, 8);
    }


    /**
     * Вызывается каждый раз при нажатии на кнопку Init.
     * Отрисовывает гексы на рабочем поле.
     * Инициализирует HexagonBox, MeetPointsBox.
     *
     * @param r      - радиус гекса
     * @param width  ширина рабочего поля
     * @param height высота рабочего поля
     */

    private void initCanvas(int r, int width, int height) {

        hexagonBox = new HexagonBox(r, width, height);
        meetPointsBox = new MeetPointsBox(hexagonBox);

        ArrayList<Hexagon> temp = hexagonBox.getHexBox();
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (Hexagon hexagon : temp) {
            ArrayList<MeetPoint> meetPoints = hexagon.getRandevuPoint();
            gc.setFill(Color.BLACK);
            Point centerPoint = hexagon.getCenter();
            gc.fillText(hexagon.getNum() + "", centerPoint.getX() - 5, centerPoint.getY() - r / 2);
            gc.setLineWidth(1);
            for (int i = 0; i < meetPoints.size(); i++) {
                Point p1, p2;
                if (i == meetPoints.size() - 1) {
                    p1 = meetPoints.get(i).toPoint();
                    p2 = meetPoints.get(0).toPoint();
                } else {
                    p1 = meetPoints.get(i).toPoint();
                    p2 = meetPoints.get(i + 1).toPoint();
                }
                gc.setStroke(Color.BLACK);
                gc.strokeLine(p1.x, p1.y, p2.x, p2.y);


            }
            for (MeetPoint rp : meetPoints) {
                Point p = rp.toPoint();
                gc.fillOval(p.x - 5, p.y - 5, 10, 10);
                gc.setFill(Color.WHITE);
                gc.fillOval(p.x - 3, p.y - 3, 6, 6);
                gc.setFill(Color.BLACK);
            }

        }

    }


    /**
     * Парсит строку и возвращает список Point
     *
     * @param st строка полученная через ui
     * @return коорды
     */
    private ArrayList<Point> parser(String st) {
        String[] temp = st.split(" ");

        if (temp.length % 2 == 1) return null;
        else {
            ArrayList<Point> ps = new ArrayList<>();
            //int count = 0;

            for (int i = 0; i < temp.length / 2; i++) {
                ps.add(new Point(Integer.parseInt(temp[2 * i]), Integer.parseInt(temp[2 * i + 1])));
            }
            return ps;
        }

    }


    /**
     * Обработчик кнопки Init
     *
     * @param actionEvent
     */
    public void handleInit(ActionEvent actionEvent) {
        /*mapWidth = (int)splitPane.getHeight() - 20;

        mapHeight = (int)splitPane.getWidth() - 80;
        System.out.println(mapHeight + " " + mapHeight + " " + map.getLayoutX() + " " + map.getLayoutY());
        map.setLayoutX(10);
        map.setLayoutY(40);*/
        GraphicsContext gc = map.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 800);
        int r = Integer.parseInt(radLabel.getText());
        initCanvas(r, 800, 800);
    }
}
