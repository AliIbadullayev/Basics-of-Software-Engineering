package web;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ManagedBean implements Serializable {
    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private int id;

    private Integer xValue;
    private Float yValue;
    private Integer radiusValue;
    private String result;

    public boolean checkRectangle(Coordinates coordinates, int r){
        return coordinates.getxValue() >= -r && coordinates.getyValue() <= (double) r/2 && coordinates.getxValue() <= 0 && coordinates.getyValue() >=0;
    }

    public boolean checkTriangle(Coordinates coordinates, int r){

        return coordinates.getyValue() >= -2*coordinates.getxValue() - r && coordinates.getyValue() <= 0 && coordinates.getxValue() <= 0;
    }

    public boolean checkCircle(Coordinates coordinates, int r){
        return (coordinates.getyValue()*coordinates.getyValue() + coordinates.getxValue()*coordinates.getxValue() <= r*r)
                && coordinates.getyValue() <= 0 && coordinates.getxValue() >= 0;
    }

    public void checkHit(){
        result = isHit(xValue, yValue, radiusValue) ? "good" : "bad";
    }

    public boolean isHit(int x, float y, int r){
        Coordinates coordinates = new Coordinates(x, y);
        return checkCircle(coordinates, r) || checkTriangle(coordinates, r) || checkRectangle(coordinates, r);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getxValue() {
        return xValue;
    }

    public void setxValue(Integer xValue) {
        this.xValue = xValue;
    }


    public Float getyValue() {
        return yValue;
    }

    public void setyValue(Float yValue) {
        this.yValue = yValue;
    }

    public Integer getRadiusValue() {
        return radiusValue;
    }

    public void setRadiusValue(Integer radiusValue) {
        this.radiusValue = radiusValue;
    }

    public String backToStart(){
        return "toStart";
    }
    public String backToIndex(){
        return "toIndex";
    }

    public void result(){
        System.out.println(xValue);
        System.out.println(yValue);
        System.out.println(radiusValue);
        System.out.println(result);
    }
}
