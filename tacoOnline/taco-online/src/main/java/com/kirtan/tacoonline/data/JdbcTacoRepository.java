package com.kirtan.tacoonline.data;

import com.kirtan.tacoonline.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;


public class JdbcTacoRepository implements TacoRepository {
    private JdbcOperations jdbcOperations;


    @Override
    public Taco save(Taco design) {

        return null;
    }

}
