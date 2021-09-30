package tdd;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CoinSet implements Comparable<CoinSet> {
    _500_COIN(500), _100_COIN(100), _50_COIN(50), _10_COIN(10);

    int value;

    CoinSet(int value) {
        this.value = value;
    }

    public static CoinSet valueOf(final int amount) {
        return Arrays.stream(values())
                .filter(coin -> coin.value == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(amount + "원의 동전은 존재하지 않습니다"));
    }

    public static List<CoinSet> highestOrder() {
        return Arrays.stream(CoinSet.values())
                .sorted((o1, o2) -> Integer.compare(o2.value, o1.value))
                .collect(Collectors.toList());
    }
}
