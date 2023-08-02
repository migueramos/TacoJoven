package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.UserInf;

public interface UserRepository extends CrudRepository<UserInf, String> {
    UserInf findByUsername(String userName);
}
