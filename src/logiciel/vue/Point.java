package logiciel.vue;

import java.util.Objects;

public class Point {

    private double coordX;
    private double coordY;

    public Point(double coordX, double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public double accesCoordX() {
        return coordX;
    }

    public double accesCoordY() {
        return coordY;
    }

    public void fixeCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void fixeCoordY(double coordY) {
        this.coordY = coordY;
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordX=" + coordX +
                ", coordY=" + coordY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.coordX, coordX) == 0 && Double.compare(point.coordY, coordY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordX, coordY);
    }
}
