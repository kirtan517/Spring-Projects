package com.kirtan.tacoonline.data;

import com.kirtan.tacoonline.Taco;
import com.kirtan.tacoonline.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
