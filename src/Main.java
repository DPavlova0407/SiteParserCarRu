import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Daria on 06.02.2018.
 */
public class Main {
    private static String URL = "https://car.ru/saratov/all/page";
    private static final ArrayList<Car> list = new ArrayList<Car>();

    public static void main(String[] args) throws IOException {
        //---------- выгрузка с сайта и сохранение в файл
        Load();
    }
    private static void Load() throws IOException {
        System.out.println("Data is loading, please wait."); //1
        long timestart = System.currentTimeMillis();
        int k = 1;
        LoadData(1, 300, URL, k);
        URL = "https://car.ru/samara/all/page";
        System.out.println("---------------------------------------------Samara");
        LoadData(1, 300, URL, k);
        dataToFile();

        long timeend = System.currentTimeMillis();
        System.out.println("Времени на загрузку с сайта затрачено: "+ ((timeend - timestart) / 1000) + " секунд");
    }

    private static void LoadData(int startPage, int numOfPages, String url, int k) {
   //     numOfPages = 1;
        for (int i = startPage; i < startPage + numOfPages; i++) {
            int page = i;
            Document doc = null;
            try {
                doc = Jsoup.connect(url + page + "/").userAgent("Yandex").get();
                Elements mainElements = doc.select("div.obvl-row-tile");
                if (mainElements != null){
                    for (Element element : mainElements) {
                        System.out.println(k);
                        k++;
                        //System.out.println(element.toString());

                        String carUrl = element.select("div.photos").attr("data-link");
                        System.out.println(carUrl);

                        String mark_model = element.select("div.mark-model").get(0).childNode(0).toString();
                        mark_model = mark_model.substring(1, mark_model.length());
                        System.out.println(mark_model);

                        String year_probeg = "";
                        String year = "-";
                        String probeg = "-";
                        if (element.select("div.year-probeg").get(0).childNodeSize() > 0) {
                            year_probeg = element.select("div.year-probeg").get(0).childNode(0).toString();
                            year_probeg = year_probeg.substring(1, year_probeg.length());

                            year = year_probeg;
                            probeg = "-";
                            if (year_probeg.contains(",")) {
                                int pos = year_probeg.indexOf(',');
                                probeg = year_probeg.substring(pos + 2, year_probeg.length());
                                year = year_probeg.substring(0, pos);
                            }
                        }
                        System.out.println(year);
                        System.out.println(probeg);

                        String description = element.select("div.description").get(0).childNode(0).toString();
                        description = description.substring(1, description.length());
                        System.out.println(description);

                        String price = element.select("div.price").get(0).childNode(0).toString();
                        price = price.substring(1, price.length());
                        System.out.println(price);

                        Car car = new Car(carUrl, mark_model, year, probeg, description, price);
                        list.add(car);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void dataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("cars.txt");
        System.out.println("Элементов загружено -----------------");
        System.out.println(list.size());
        for(Car c : list){
            fileWriter.write(c.toString());
        }
        fileWriter.close();
    }
}
