package tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangesTest {

    @ParameterizedTest
    @CsvSource(value = {"10, _10_COIN", "50, _50_COIN", "100, _100_COIN", "500, _500_COIN"})
    @DisplayName("{0}원이 남아있다면 {0}원 1개로 돌려준다.")
    void return_1_quantity_nCoin_when_remain_n(int value, CoinSet coin) {
        Changes changes = new Changes(value);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet.size()).isEqualTo(1);
        assertThat(coinSet.get(0)).isEqualTo(coin);
    }

    @Test
    @DisplayName("1000원이 남아있다면 500원 2개로 돌려준다.")
    void return_2_quantities_500_coin_when_remain_1000() {
        Changes changes = new Changes(1000);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._500_COIN);
    }

    @Test
    @DisplayName("20원이 남아있다면 10원 2개로 돌려준다.")
    void return_2_quantities_10_coin_when_remain_20() {
        Changes changes = new Changes(20);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._10_COIN, CoinSet._10_COIN);
    }

    @Test
    @DisplayName("600원이 남아있다면 500원 1개, 100원 1개로 돌려준다.")
    void return_1_500_and_1_100_coin_when_remain_600() {
        Changes changes = new Changes(600);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._100_COIN);
    }

    @Test
    @DisplayName("650원이 남아있다면 500원 1개, 100원 1개, 50원 1개로 돌려준다.")
    void return_1_500_and_1_100_and_1_50_coin_when_remain_650() {
        Changes changes = new Changes(650);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._100_COIN, CoinSet._50_COIN);
    }

    @Test
    @DisplayName("잔돈을 거슬러 줄 수 없는 동전은 버리고 반환한다.")
    void return_changes_except_cannot_exchange() {
        Changes changes = new Changes(9);
        List<CoinSet> coinSet = changes.coin();
        assertThat(coinSet).isEmpty();

        changes = new Changes(624);
        coinSet = changes.coin();
        assertThat(coinSet).containsExactlyInAnyOrder(CoinSet._500_COIN, CoinSet._100_COIN, CoinSet._10_COIN, CoinSet._10_COIN);
    }

}
