package ua.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.epam.dao.entities.IEntityDao;
import ua.epam.dao.entities.user.UserDao;
import ua.epam.dao.entities.user.IUserDao;
import ua.epam.models.entities.User;
import ua.epam.dao.sevices.UserService;

@Service
public class MainJdbc {
    static UserDao userDao = new UserDao();

    public static void main(String[] args) {

        String pass =  new BCryptPasswordEncoder().encode("admin");
        System.out.println(pass);
        //boolean bb =  userDao.update(1,new User(1, "admin",pass, "2@2", "123", "123", "ADMIN"));
       // System.out.println(bb);
        //System.out.println(adminService.get(1).toString());
        //userService.get(0, 267, "", IUserDao.SortOrder.ID, IEntityDao.SortType.ASC).forEach(System.out::println);

    }
}
