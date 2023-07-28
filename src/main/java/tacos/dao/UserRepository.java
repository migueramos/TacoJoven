package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUserName(String userName);
}
