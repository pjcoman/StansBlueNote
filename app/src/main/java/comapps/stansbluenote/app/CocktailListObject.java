package comapps.stansbluenote.app;

public class CocktailListObject {

    private String cocktailname;
    private String cocktailingredients;
    private String cocktailprice;
    private String cocktailimage;

    public String getCocktailName() {
        return cocktailname;
    }

    public void setCocktailName(String cocktailname) {
        this.cocktailname = cocktailname;
    }

    public String getCocktailIngredients() {
        return cocktailingredients;
    }

    public void setCocktailIngredients(String cocktailingredients) {
        this.cocktailingredients = cocktailingredients;
    }

    public String getCocktailPrice() {
        return cocktailprice;
    }

    public void setCocktailPrice(String cocktailprice) {
        this.cocktailprice = cocktailprice;
    }

    public String getCocktailImage() {
        return cocktailimage;
    }

    public void setCocktailImage(String cocktailimage) {
        this.cocktailimage = cocktailimage;
    }

    @Override
    public String toString() {
        return "cocktailList [cocktailname=" + cocktailname + ", cocktailingredients=" + cocktailingredients + ", cocktailprice=" + cocktailprice +
                ", cocktailimage=" + cocktailimage + "]";
    }


}
