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

    public boolean checkRectangle(){
        return xValue >= -radiusValue && yValue <= radiusValue/2 && xValue <= 0 && yValue >=0;
    }

    public boolean checkTriangle(){
        return yValue >= -2*xValue - radiusValue  && yValue <= 0 && xValue <= 0;
    }

    public boolean checkCircle(){
        return (yValue*yValue) + (xValue*xValue) <= (radiusValue*radiusValue)  && yValue <= 0 && xValue >= 0;
    }

    public void checkHit(){
        if (checkCircle() || checkTriangle() || checkRectangle()) result = "good";
        else result = "bad";
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

    @Transient
    private Boolean x_minus_5;
    @Transient
    private Boolean x_minus_4;
    @Transient
    private Boolean x_minus_3;
    @Transient
    private Boolean x_minus_2;
    @Transient
    private Boolean x_minus_1;
    @Transient
    private Boolean x_0;
    @Transient
    private Boolean x_1;



    public Boolean getX_minus_5() {
        return x_minus_5;
    }

    public void setX_minus_5(Boolean x_minus_5) {
        this.x_minus_5 = x_minus_5;
        if (x_minus_5) xValue = -5;
    }

    public Boolean getX_minus_4() {
        return x_minus_4;

    }

    public void setX_minus_4(Boolean x_minus_4) {
        this.x_minus_4 = x_minus_4;
        if (x_minus_4) xValue = -4;
    }

    public Boolean getX_minus_3() {
        return x_minus_3;
    }

    public void setX_minus_3(Boolean x_minus_3) {
        this.x_minus_3 = x_minus_3;
        if (x_minus_3) xValue = -3;
    }

    public Boolean getX_minus_2() {
        return x_minus_2;
    }

    public void setX_minus_2(Boolean x_minus_2) {
        this.x_minus_2 = x_minus_2;
        if (x_minus_2) xValue = -2;
    }

    public Boolean getX_minus_1() {
        return x_minus_1;
    }

    public void setX_minus_1(Boolean x_minus_1) {
        this.x_minus_1 = x_minus_1;
        if (x_minus_1) xValue = -1;
    }

    public Boolean getX_0() {
        return x_0;
    }

    public void setX_0(Boolean x_0) {
        this.x_0 = x_0;
        if (x_0) xValue = 0;
    }

    public Boolean getX_1() {
        return x_1;
    }

    public void setX_1(Boolean x_1) {
        this.x_1 = x_1;
        if (x_1) xValue = 1;
    }





    public Integer getXValue() {
//        if (x_minus_5) xValue = -5;
//        if (x_minus_4) xValue = -4;
//        if (x_minus_3) xValue = -3;
//        if (x_minus_2) xValue = -2;
//        if (x_minus_1) xValue = -1;
//        if (x_0) xValue = 0;
//        if (x_1) xValue = 1;
        return xValue;
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
