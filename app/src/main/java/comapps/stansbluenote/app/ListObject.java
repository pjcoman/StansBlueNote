package comapps.stansbluenote.app;

public class ListObject {

    private String x;
    private String y;
    private String z;
    private String image;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "List [x=" + x + ", y=" + y + ", z=" + z +
                ", image=" + image + "]";
    }


}
