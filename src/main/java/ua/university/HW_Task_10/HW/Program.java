package ua.university.HW_Task_10.HW;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {

        List<String> urls= new ArrayList<>();
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2019.csv");
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2019.csv");
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/953aae94-8c82-4296-881f-f57b3a7be389/download/berezen-2019.csv");
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/edd8e5e6-4f95-4e61-a400-d1cf3994a43e/download/zp-kviten-2019.csv");
        PersonHelper.getDataFromURL_onlyCSV(urls);

        PersonHelper.getDataFromLocalCVS();

 //       getFromURL("https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests");
    }

//    public static void getFromURL(String url) throws IOException {
//        URL website = new URL(url);
//        URLConnection connection = website.openConnection();
//        //List<User> users = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(
//                connection.getInputStream(), Charset.forName("WINDOWS-1251")))) {
//            br.readLine();
//            while (br.ready()) {
//                String text = br.readLine();
//                String[] words = text.split(";");
//                users.add(new User(
//                        words[0],
//                        words[1],
//                        Double.parseDouble(words[2].replace(",", "."))
//                ));
//                System.out.println(text);
//            }
//        }
//    }


}

/*package nau.university.HW_Task_10.HW;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {

        List<String> urls= new ArrayList<>();
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/d2b1a7d2-9222-4dfa-b57e-c0bb0b21485b/download/sichen-zp-2019.csv");
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/38ef4e5e-72ec-4715-95d7-28c0fd42176c/download/liutii-zp-2019.csv");
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/953aae94-8c82-4296-881f-f57b3a7be389/download/berezen-2019.csv");
        urls.add("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/edd8e5e6-4f95-4e61-a400-d1cf3994a43e/download/zp-kviten-2019.csv");
        PersonHelper.getDataFromURL_onlyCSV(urls);

        PersonHelper.getDataFromLocalCVS();
    }

    public static void getFromURL(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        //List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), Charset.forName("WINDOWS-1251")))) {
            br.readLine();
            while (br.ready()) {
                String text = br.readLine();
//                String[] words = text.split(";");
//                users.add(new User(
//                        words[0],
//                        words[1],
//                        Double.parseDouble(words[2].replace(",", "."))
//                ));
                System.out.println();
            }
        }
        return users;
    }


}
*/
