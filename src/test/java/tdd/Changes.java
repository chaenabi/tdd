package tdd;

import java.util.ArrayList;
import java.util.List;

public class Changes {

    private int amount;

    public Changes(int amount) {
        this.amount = amount;
    }

    public List<CoinSet> coin() {
        List<CoinSet> list = new ArrayList<>();
        for (CoinSet coin : CoinSet.highestOrder()) {
            final int quotient = amount / coin.value; // 몫
            for (int i = 0; i < quotient; i++) {
                list.add(coin);
            }
            amount = amount - (quotient * coin.value);
        }
        return list;
    }
}
