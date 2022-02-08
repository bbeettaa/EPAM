package nau.university.HW_Task_10.HW;

import java.beans.DefaultPersistenceDelegate;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PersonHelper {
    static String pathDir = "resources" + File.separator + "csv" + File.separator;

    public static void getDataFromURL_onlyCSV(List<String> urls) throws IOException {

        for (int i =0;i<urls.size();i++) {
            if(!urls.get(i).contains(".csv")){
                urls.remove(i);
                i--;
            }
        }

        String dataToFile="";
        for (var file : urls) {
            List<Person> persons = getPersonFromURL(file);
            dataToFile+=WriteToFileOutput(file,persons);
        }

        String fileToSave = "Output_Result_url.csv";
        File outFile = new File("resources"+File.separator+fileToSave);
        if(!outFile.exists()) outFile.createNewFile();
        FileWriter fw = new FileWriter(outFile, Charset.forName("WINDOWS-1251"));
        fw.write(dataToFile);
        fw.close();

    }

    public static void getDataFromLocalCVS() throws IOException {
        List<String> csvList = new ArrayList<>();

        File dir = new File("resources"+File.separator+"csv");
        if (dir.isDirectory())
            for(var file :dir.list())
                csvList.add(file);

        for (int i =0;i<csvList.size();i++) {
            if(csvList.get(i).contains("Output_Result.csv") || !csvList.get(i).contains(".csv")){
                csvList.remove(i);
                i--;
            }
        }
        String dataToFile="";
        for (var file : csvList) {
            List<Person> persons = getUserFromCsv(file);
            dataToFile+=WriteToFileOutput(file,persons);
        }

        String fileToSave = "Output_Result.csv";
        File outFile = new File("resources"+File.separator+fileToSave);
        if(!outFile.exists()) outFile.createNewFile();
        FileWriter fw = new FileWriter(outFile, Charset.forName("WINDOWS-1251"));
        fw.write(dataToFile);
        fw.close();
    }

/*    public static void createFileWithOutputResult(List<String> fileNames) throws IOException {
        File outFile = new File("resources"+File.separator+"Output_Result.csv");
        if(!outFile.exists()) outFile.createNewFile();

        FileWriter fw = new FileWriter(outFile, Charset.forName("WINDOWS-1251"));
        String cap = "Працівник; Посада; Оплата по окладу; Премія місячна; Сума\n";
        for (var file : fileNames) {
            fw.write("\n" + file.replace("zp-", "") + "\n");
            List<Person> persons = getUserFromCsv(file);

            fw.write(";Усі працівнки\n");
            fw.write(cap);
            fw.write(getPersonsInfo(persons));
            fw.write("\n;Працівники з найбільшим окладом\n");
            fw.write(cap);
            fw.write(getPersonsInfo(getUserWithMaxSalary(persons)));
            fw.write("\n;Працівники з найбільшою місячною премією\n");
            fw.write(cap);
            fw.write(getPersonsInfo(getUserWithMonthlyAward(persons)));
            fw.write("\n;Працівники з найбільшою сумою\n");
            fw.write(cap);
            fw.write(getPersonsInfo(getUserWithGeneralSum(persons)));

            fw.write("\n\n");
        }
        fw.close();
    }*/

    private static String WriteToFileOutput (String fileName,List<Person> persons) throws IOException {
        String dataToFile="";

        String cap = "Працівник; Посада; Оплата по окладу; Премія місячна; Сума\n";

        dataToFile+=("\n" + fileName.replace("zp-", "") + "\n");
        dataToFile+=(";Усі працівнки\n");
        dataToFile+=(cap);
        dataToFile+=(getPersonsInfo(persons));
        dataToFile+=("\n;Працівники з найбільшим окладом\n");
        dataToFile+=(cap);
        dataToFile+=(getPersonsInfo(getUserWithMaxSalary(persons)));
        dataToFile+=("\n;Працівники з найбільшою місячною премією\n");
        dataToFile+=(cap);
        dataToFile+=(getPersonsInfo(getUserWithMonthlyAward(persons)));
        dataToFile+=("\n;Працівники з найбільшою сумою\n");
        dataToFile+=(cap);
        dataToFile+=(getPersonsInfo(getUserWithGeneralSum(persons)));

        dataToFile+=("\n\n");

        return dataToFile;
    }

    private static String getPersonsInfo(List<Person> persons) {
        String output = "";
        for (Person person : persons) {
            output += (person.getPersonProps() + "\n");
        }
        return output;
    }

    private static List<Person> getUserFromCsv(String filename) throws IOException {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathDir + filename, Charset.forName("UTF-8")))) {

            br.readLine();
            while (br.ready()) {
                String text = br.readLine();
                String[] words = text.split(";");
                persons.add(new Person(
                        words[0],
                        words[1],
                        Double.parseDouble(words[2].replace(",", ".").replace(" ₴","").replace(" ","")),
                        Double.parseDouble(words[6].replace(",", ".").replace(" ₴","").replace(" ","")),
                        Double.parseDouble(words[words.length-1].replace(",", ".").replace(" ₴","").replace(" ",""))
                ));
            }
        }
        return persons;
    }

    private static List<Person> getPersonFromURL(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();

        List<Person> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), Charset.forName("UTF-8")))) {
            br.readLine();
            while (br.ready()) {
                String text = br.readLine();
                String[] words = text.split(";");
                users.add(new Person(
                        words[0],
                        words[1],
                        Double.parseDouble(words[2].replace(",", ".").replace(" ₴","").replace(" ","")),
                        Double.parseDouble(words[6].replace(",", ".").replace(" ₴","").replace(" ","")),
                        Double.parseDouble(words[words.length-1].replace(",", ".").replace(" ₴","").replace(" ",""))
                ));
            }

        }
        return users;
    }

    private static List<Person> getUserWithMaxSalary(List<Person> persons) {
        List<Person> maxUsers = new ArrayList<>();
        Person maxSalaryUser = persons.get(0);
        for (var person : persons) {
            if (maxSalaryUser.getSalary() < person.getSalary())
                maxSalaryUser = person;
        }
        for (var person : persons) {
            if (maxSalaryUser.getSalary() == person.getSalary())
                maxUsers.add(person);
        }
        return maxUsers;
    }

    private static List<Person> getUserWithMonthlyAward(List<Person> persons) {
        List<Person> maxUsers = new ArrayList<>();
        Person maxSalaryUser = persons.get(0);
        for (var person : persons) {
            if (maxSalaryUser.getMonthlyAward() < person.getMonthlyAward())
                maxSalaryUser = person;
        }
        for (var person : persons) {
            if (maxSalaryUser.getMonthlyAward() == person.getMonthlyAward())
                maxUsers.add(person);
        }
        return maxUsers;
    }

    private static List<Person> getUserWithGeneralSum(List<Person> persons) {
        List<Person> maxUsers = new ArrayList<>();
        Person maxSalaryUser = persons.get(0);
        for (var person : persons) {
            if (maxSalaryUser.getGeneralSum() < person.getGeneralSum())
                maxSalaryUser = person;
        }
        for (var person : persons) {
            if (maxSalaryUser.getGeneralSum() == person.getGeneralSum())
                maxUsers.add(person);
        }
        return maxUsers;
    }


}
