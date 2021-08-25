package com.quoctan.utils;

import com.quoctan.pojos.Cart;
import java.util.Map;


public class Utils {
    public  static int countCart(Map<Integer, Cart> cart) {
        int count = 0;
        if (cart != null) {
            for (Cart c: cart.values()) {
                count += c.getCount();
            }
        }
        return count;
    }
}
