package tacos.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.domain.Order;
import tacos.domain.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert orderTacoInsert;
    private ObjectMapper mapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc){
        this.orderInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order");

        this.orderTacoInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");

        this.mapper = new ObjectMapper();
    }
    @Override
    public Order save(Order order){
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        order.setPlacedAt(LocalDateTime.now());
        saveOrderDetails(order);
        order.getTacos().stream().forEach(
                taco -> saveTacoToOrder(taco, orderId)
        );

        return order;
    }

    private void saveOrderDetails(Order order){
        mapper.registerModule(new JavaTimeModule());
        @SuppressWarnings("unchecked")
        Map<String, Object> values = mapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        orderInsert.execute(values);
    }

    private void saveTacoToOrder(Taco taco, String orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInsert.execute(values);
    }
}
