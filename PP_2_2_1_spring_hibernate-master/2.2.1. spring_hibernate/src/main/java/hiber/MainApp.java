package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User nikita = new User("Nikita", "Sedinkin", "sedinkin@mail.io");
      User sasha = new User("Sasha", "Yakovleva", "yakovleva@mail.io");
      User olga = new User("Olga", "Sedinkina", "sedinkina@mail.io");
      User vlad = new User("Vlad", "Bronnikov", "bronnikov@mail.io");

      Car volvo = new Car("Volvo", 9);
      Car bmw = new Car("BMW", 325);
      Car suzuki = new Car("Sisuki", 4);
      Car lada = new Car("Ladaa", 21014);

      userService.add(nikita.setCar(volvo).setUser(nikita));
      userService.add(sasha.setCar(bmw).setUser(sasha));
      userService.add(olga.setCar(suzuki).setUser(olga));
      userService.add(vlad.setCar(lada).setUser(vlad));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("BMW", 325));

      try {
         User notFoundUser = userService.getUserByCar("GAZ", 4211);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
