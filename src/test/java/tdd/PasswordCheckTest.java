package tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/*
    [ 요구 사항 ]
    1. 길이가 8글자 이상
    2. 0-9 사이의 숫자를 포함해야함
    3. 대문자를 포함

    [ ] 3가지 규칙 모두 만족시 'STRONG'
    [ ] 2가지 규칙 만족시 'NORMAL'
    [ ] 1가지 규칙 만족시 'WEAK'
    [ ] 아무 것도 만족하지 않으면 'INVALID'
 */
public class PasswordCheckTest {

    PasswordCheck passwordCheck = new PasswordCheck();

    @Test
    @DisplayName("비밀번호가 8글자 이상일때 WEAK을 반환해야합니다.")
    void isPasswordOverEightWords() {
        assertThat(passwordCheck.check("asddsdfjksdafjklsadf")).isEqualTo(PasswordStrength.NOTGOOD);
    }

    @ParameterizedTest
    @ValueSource(strings = { "여덟글자를넘어갑니다여덟글자이상입니다"})
    void isPasswordOverEightWords_2(String password) {
        assertThat(passwordCheck.check(password)).isEqualTo(PasswordStrength.NOTGOOD);
    }
    
    @Test
    @DisplayName("비밀번호에 대문자가 하나 이상있을때 WEAK을 반환해야합니다.")
    void isPasswordContainsUpperCase() {
        assertThat(passwordCheck.check("asdDD")).isEqualTo(PasswordStrength.NOTGOOD);
    }

    @Test
    @DisplayName("비밀번호가 8글자이상이고 0과 9사이의 숫자가 있을때 NORMAL을 반환해야합니다.")
    void isPasswordOverEightWordsAndContainsDigit() {
        assertThat(passwordCheck.check("1".repeat(100))).isEqualTo(PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("비밀번호가 8글자이상이고 대문자가 하나 이상 있을때 NORMAL을 반환해야합니다.")
    void isPasswordOverEightWordsAndContainsUpperCase() {
        assertThat(passwordCheck.check("DASD".repeat(100))).isEqualTo(PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("비밀번호가 0과 9사이의 숫자를 포함하고 있고 대문자가 하나 이상 있을때 NORMAL을 반환해야합니다.")
    void isPasswordContainsDigitAndContainsUpperCase() {
        assertThat(passwordCheck.check("DASD".repeat(100))).isEqualTo(PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("비밀번호가 8글자 이상이고, 0과 9사이의 숫자를 포함하고 있으며, 대문자가 하나 이상 있을때 STRONG을 반환해야 합니다.")
    void successPasswordAllPass() {
        assertThat(passwordCheck.check("213DASD".repeat(100))).isEqualTo(PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("비밀번호가 모든 기준에 부합하지 않으면 INVALID를 반환해야 합니다.")
    void finalPasswordAllPass() {
        assertThat(passwordCheck.check("".repeat(100))).isEqualTo(PasswordStrength.INVALID);
    }

}
