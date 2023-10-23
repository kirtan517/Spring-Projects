package com.kirtan.tacoonline.data;

import com.kirtan.tacoonline.Ingredient;
import com.kirtan.tacoonline.IngredientRef;
import com.kirtan.tacoonline.Taco;
import com.kirtan.tacoonline.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.*;


@Repository
@Transactional
public class JdbcOrderRepository implements OrderRepository {

   private JdbcOperations jdbcOperations;

   public JdbcOrderRepository (JdbcOperations jdbcOperations){
       this.jdbcOperations = jdbcOperations;
   }

   private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients ){
       for(Ingredient ingredient : ingredients){
           jdbcOperations.update("insert into Ingredient_Ref values(?,?)",tacoId,ingredient.getId());
       }


   }


   public long saveTaco(long orderId ,Taco taco){

       System.out.println(orderId);

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco (name,created_at,taco_order_key)"+
                "value (?,?,?)",Types.VARCHAR,Types.TIMESTAMP,Types.BIGINT
        );

        pscf.setReturnGeneratedKeys(true);

       taco.setCreatedAt(new Date());

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                taco.getName(),
                taco.getCreatedAt(),
                orderId
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc,keyHolder);
       System.out.println(keyHolder);
       System.out.println(taco);

        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

       Map<String, Object> values = new HashMap<>();
       values.put("Taco_Order", orderId);
       values.put("Taco", taco.getId());

        saveIngredientRefs(tacoId,taco.getIngredients());
       return tacoId;
   }

   @Override
   public TacoOrder save(TacoOrder order){
       PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
               "insert into Taco_Order ( name,street,city,state," +
                       "zip,cc_number,cc_expiration,cc_cvv,placed_at)"+
                       "values (?,?,?,?,?,?,?,?,?)",
               Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
               Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
               Types.TIMESTAMP
       );
       pscf.setReturnGeneratedKeys(true);
       order.setPlacedAt(new Date());
       PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
               Arrays.asList(
                       order.getName(),
                       order.getStreet(),
                       order.getCity(),
                       order.getState(),
                       order.getZip(),
                       order.getCcNumber(),
                       order.getCcExpiration(),
                       order.getCcCVV(),
                       order.getPlacedAt()
               )
       );

       GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
       jdbcOperations.update(psc,keyHolder);
       long orderId = keyHolder.getKey().longValue();
       order.setId(orderId);

       List<Taco> tacos = order.getTacos();
       for(Taco taco : tacos) {
           saveTaco(orderId, taco);
       }
       return order;
   }

}
