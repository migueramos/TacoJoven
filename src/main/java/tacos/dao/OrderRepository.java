package tacos.dao;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Order;

public interface OrderRepository extends CrudRepository<Order, String> {

}
