package comapps.stansbluenote.app;

public class MenuListObject {

    private String item;
    private String price;
    private String type;


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "MenuList [item=" + item + ", " + "price=" + price + "type=" + type + "]";
    }


}
