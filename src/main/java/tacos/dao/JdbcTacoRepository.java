package tacos.dao;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository{
    private JdbcTemplate jdbc;
    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco){
        String tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        taco.getIngredients().stream().forEach(ingredient -> saveIngredientToTaco(ingredient, tacoId));

        return taco;
    }

    private String saveTacoInfo(Taco taco){
        String id = UUID.randomUUID().toString();
        taco.setCreatedAt(LocalDateTime.now());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory("Insert Into Taco (id, name, createdAt) values (?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP)
                .newPreparedStatementCreator(Arrays.asList(id, taco.getName(),
                        Timestamp.valueOf(taco.getCreatedAt())));

        jdbc.update(psc);
        return  id;
    }

    private void saveIngredientToTaco(Ingredient ingredient, String id){
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) " +
                "values (?,?)", id, ingredient.getId());
    }
}
