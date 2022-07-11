package tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoinSetTest {

    @Test
    @DisplayName("동전을 생성한다")
    void create_coin() {
        CoinSet coin = CoinSet._100_COIN;
        assertThat(coin.value).isEqualTo(100);
    }

    @Test
    @DisplayName("동전")
    void coin() {
        CoinSet coinSet = CoinSet.valueOf(10);
        assertThat(coinSet).isEqualTo(CoinSet._10_COIN);
    }

    @ParameterizedTest
    @MethodSource("coinSet")
    @DisplayName("동전은 500원, 100원, 50원, 10원이 있다")
    void exist_coin_500_100_50_10(int value, CoinSet coin) {
        assertThat(coin.value).isEqualTo(value);
    }

    static List<Arguments> coinSet() {
        return List.of(
                Arguments.of(500, CoinSet._500_COIN),
                Arguments.of(100, CoinSet._100_COIN),
                Arguments.of(50, CoinSet._50_COIN),
                Arguments.of(10, CoinSet._10_COIN)
        );
    }

    @Test
    void exist_coin_500() {
        CoinSet coin = CoinSet._500_COIN;
        assertThat(coin.value).isEqualTo(500);
    }

    @Test
    void exist_coin_100() {
        CoinSet coin = CoinSet._100_COIN;
        assertThat(coin.value).isEqualTo(100);
    }

    @Test
    void exist_coin_50() {
        CoinSet coin = CoinSet._50_COIN;
        assertThat(coin.value).isEqualTo(50);
    }

    @Test
    @DisplayName("전체 동전은 금액이 큰 순서대로 반환된다")
    void return_higher_coin_first() {
        assertThat(CoinSet.highestOrder()).containsExactly(CoinSet._500_COIN, CoinSet._100_COIN, CoinSet._50_COIN, CoinSet._10_COIN);
    }
}