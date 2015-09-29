package comapps.stansbluenote.app;

public class StaffListObject {

    private String name;
    private String other;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





    @Override
    public String toString() {
        return "staffList [name=" + name + ", other=" + other +
                ", image=" + image + "]";
    }


}
