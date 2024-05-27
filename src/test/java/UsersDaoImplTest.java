import org.example.model.User;
import org.example.service.UsersDaoImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsersDaoImplTest {

    static UsersDaoImpl usersDao;

    @BeforeAll
    static void setup() {
        usersDao = new UsersDaoImpl();
    }

    @Test
    void crud_user_test() {
        User user = new User(4,"1a","UserName");
        User user2 = new User(5,"2a","UserName");
        usersDao.add(user);
        usersDao.add(user2);
        assertThat(usersDao.printAll()).hasSize(2);
        usersDao.deleteAll();
        assertThat(usersDao.printAll()).isEmpty();
    }
}
