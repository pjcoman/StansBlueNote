package comapps.stansbluenote.app;

public class BeerListObject {

    private String beername;
    private String beerwherefrom;
    private String beerabv;
    private String beertype;
    private String beerimage;

    public String getBeerName() {
        return beername;
    }

    public void setBeerName(String beername) {
        this.beername = beername;
    }

    public String getBeerWhereFrom() {
        return beerwherefrom;
    }

    public void setBeerWhereFrom(String beerwherefrom) {
        this.beerwherefrom = beerwherefrom;
    }

    public String getBeerAbv() {
        return beerabv;
    }

    public void setBeerAbv(String beerabv) {
        this.beerabv = beerabv;
    }

    public String getBeerType() {
        return beertype;
    }

    public void setBeerType(String beertype) {
        this.beertype = beertype;
    }


    public String getBeerImage() {
        return beerimage;
    }

    public void setBeerImage(String beerimage) {
        this.beerimage = beerimage;
    }

    @Override
    public String toString() {
        return "BeerList [beername=" + beername + ", beerwherefrom=" + beerwherefrom + ", beerabv=" + beerabv + ", beertype=" + beertype +
                ", beerimage=" + beerimage + "]";
    }


}
