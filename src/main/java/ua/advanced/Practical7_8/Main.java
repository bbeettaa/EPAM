package ua.advanced.Practical7_8;

import ua.advanced.Practical7_8.DAO.SomeService;

import java.io.File;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SomeService someService = new SomeService();

        //File file = new File();

        someService.findAllFilms().forEach(System.out::println);
        System.out.println();
        someService.findAllFilmsInCurrentAndPreviousYear(2008).forEach(System.out::println);
        System.out.println();
        someService.findActorsStarredInAtLeastFilms(2).forEach(System.out::println);
        System.out.println();
        someService.findActorThatIsDirector().forEach(System.out::println);
        System.out.println();
        someService.deleteFilmsFromPreviousYears(2008);
        System.out.println();
        someService.findAllFilms().forEach(System.out::println);
    }
}
