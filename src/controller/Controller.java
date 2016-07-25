package controller;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Hexagon;
import model.HexagonBox;
import model.RandevuBox;
import model.RandevuPoint;

import java.awt.*;
import java.util.ArrayList;

/**
 * В данном классе после нажатия клавиши Init инициализируются HexagonBox и RandevuBox.
 * HexagonBox - ArrayList<Hexagon>
 * Hexagon - ArrayList<RandevuPoint> имеет ссылки на все точки в гексагоне(RandevuPoint)
 * RandevuPoint имеет ссылки на три ближайщих гексагона
 */
public class Controller {

    public Button checkBtn;
    public TextField coords;
    public TextArea info;
    public Canvas map;
    public Button initBtn;
    public TextField radLabel;

    public HexagonBox hexagonBox;
    public RandevuBox randevuBox;


    public void handleCheck(ActionEvent actionEvent) {
        String data = coords.getText();
        info.appendText(data + "\n");
        ArrayList<Point> ps = parser(data);
        // Скорее всего нужно быть
        System.out.println(hexagonBox.getHexBox().get(0).getRandevuPoint().get(0).getNeighbors().get(0).getRandevuPoint().get(0).getX());
        for (int i = 0; i < ps.size() - 1; i++) {
            drawLines(ps.get(i), ps.get(i + 1));
        }


    }

    private void drawLines(Point p1, Point p2) {
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setLineWidth(1);
        gc.setFill(Color.GREEN);
        gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
        gc.setFill(Color.RED);
        gc.fillOval(p1.x - 3, p1.y - 3, 6, 6);
        gc.fillOval(p2.x - 3, p2.y - 3, 6, 6);
    }


    /**
     * Вызывается каждый раз при нажатии на кнопку Init.
     * Отрисовывает гексы на рабочем поле.
     * Инициализирует HexagonBox, RandevuBox.
     * @param r - радиус гекса
     * @param width ширина рабочего поля
     * @param height высота рабочего поля
     */

    private void initCanvas(int r, int width, int height) {
        hexagonBox = new HexagonBox(r, width, height);
        System.out.println(hexagonBox.getHexBox().size());
        randevuBox = hexagonBox.getRandevuBox();
        ArrayList<Hexagon> temp = hexagonBox.getHexBox();
        GraphicsContext gc = map.getGraphicsContext2D();
        for (Hexagon hexagon : temp) {
            ArrayList<RandevuPoint> randevuPoints = hexagon.getRandevuPoint();
            gc.setFill(Color.BLACK);
            gc.setLineWidth(1);
            for (int i = 0; i < randevuPoints.size(); i++) {
                Point p1, p2;
                if (i == randevuPoints.size() - 1) {
                    p1 = randevuPoints.get(i).toPoint();
                    p2 = randevuPoints.get(0).toPoint();
                } else {
                    p1 = randevuPoints.get(i).toPoint();
                    p2 = randevuPoints.get(i + 1).toPoint();
                }
                gc.strokeLine(p1.x, p1.y, p2.x, p2.y);

                //System.out.println(p1.x + " " + p2.y);
            }
            for (RandevuPoint rp: randevuPoints){
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


    /** Обработчик кнопки Init
     * @param actionEvent
     */
    public void handleInit(ActionEvent actionEvent) {
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 500, 500);
        int r = Integer.parseInt(radLabel.getText());
        initCanvas(r, 500, 500);
    }
}
