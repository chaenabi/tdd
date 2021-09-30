package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class VendingMachineTest {

    ChangeModule machine;

    @BeforeEach
    void setUp() {
        machine = new ChangeModule();
    }

    @Test
    @DisplayName("잔돈모듈 생성")
    void create_vendingMachine() {
        assertThat(machine).isNotNull();
    }

    @Test
    @DisplayName("모듈에 500원을 넣으면 500원이 들어있음을 알 수 있다.")
    void canSee500_whenInput_500_In_ChangeModule() {
        machine.put(500);
        assertThat(machine.getChanges()).isEqualTo(500);
    }

    @Test
    @DisplayName("1000원이 들어있는 모듈에 500원을 넣으면 1500원이 들어있음을 알 수 있다.")
    void canSee1500_when_Input_500_after_Input_1000_In_ChangeModule() {
        machine.put(1000);
        machine.put(500);
        assertThat(machine.getChanges()).isEqualTo(1500);
    }

    @ParameterizedTest(name = "모듈에 {0}원을 넣으면 {0}원이 들어있음을 알 수 있다")
    @ValueSource(ints = {500, 1000})
    @DisplayName("자판기에 {0}원을 넣으면 {0}원이 들어있음을 알 수 있다")
    void canSeeChanges_when_Input_change_In_ChangeModule(int change) {
        machine.put(change);
        assertThat(machine.getChanges()).isEqualTo(change);
    }

    @Test
    @DisplayName("1000원이 들어있는 모듈에서 1000원을 차감하면 0원이 된다")
    void change_will_be_0_when_withdraw_1000_In_ChangeModule() {
        machine.put(1000);
        machine.withdraw(1000);
        assertThat(machine.getChanges()).isEqualTo(0);
    }

    @Test
    @DisplayName("0원이 들어있는 모듈에서는 500원을 차감할 수 없다")
    void can_withdraw_500_when_0_change_In_ChangeModule() {
        assertThatIllegalStateException()
                .isThrownBy(() -> machine.withdraw(500));
    }

    @Test
    @DisplayName("잔돈 모듈은 최소 개수의 동전을 반환할 수 잇다")
    void changes_must_returns_as_minimum_quantity() {

    }
}