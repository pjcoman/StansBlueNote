package comapps.stansbluenote.app;

public class SpecialsListObject {

    private String day;
    private String special;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }


    @Override
    public String toString() {
        return "SpecialsList [day=" + day + ", special=" + special + "]";
    }


}
