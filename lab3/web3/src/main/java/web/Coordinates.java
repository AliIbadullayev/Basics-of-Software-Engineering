package web;

public class Coordinates {
    private Integer xValue;
    private Float yValue;


    public Coordinates(Integer xValue, Float yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
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
}
