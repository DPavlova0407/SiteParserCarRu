/**
 * Created by Daria on 06.02.2018.
 */
public class Car {
    private static String url; //+
    private static String mark_model; //+
    private static String year; //+
    private static String probeg; //+
    private static String description; //+
    private static String price; //+

    public Car(String url, String mark_model, String year, String probeg, String description, String price){
        this.url = url;
        this.mark_model = mark_model;
        this.year = year;
        this.probeg = probeg;
        this.description = description;
        this.price = price;
    }
    public String getUrl(){
        return this.url;
    }
    public String getMark_model(){
        return this.mark_model;
    }
    public String getYear(){
        return this.year;
    }

    public String getProbeg(){
        return this.probeg;
    }

    public String getDescription(){
        return this.description;
    }
    public String getprice(){
        return this.price;
    }
    @Override
    public String toString(){
        return "url: " + getUrl() + "\n"
                + "mark-model: " + getMark_model() + "\n"
                + "year: " + getYear() + "\n"
                + "probeg: " + getProbeg() + "\n"
                + "description: " + getDescription() + "\n"
                + "price: " + getprice() + '\n';
    }
}
