package prac.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expect) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expect, result);
    }

    @Test
    public void allCondition_Then_Strong() {
        //given

        //when

        //then
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    public void conditionWithOut_Length_Then_Normal() {
        //given

        //when

        //then
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @Test
    public void conditionWithOut_Number_Then_Normal() {
        //given

        //when

        //then
        assertStrength("abcd!!@A", PasswordStrength.NORMAL);
    }

    @Test
    public void conditionWithOut_Uppercase_Then_Normal() {
        //given

        //when

        //then
        assertStrength("abcd1!@a", PasswordStrength.NORMAL);
    }

    @Test
    public void nullInput_Then_Invalid() {
        //given

        //when

        //then
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    public void emptyInput_Then_Invalid() {
        //given

        //when

        //then
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    public void conditionOnlyLength_Then_Weak() {
        //given

        //when

        //then
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    public void conditionOnlyNumber_Then_Weak() {
        //given

        //when

        //then
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    public void conditionOnlyUppercase_Then_Weak() {
        //given

        //when

        //then
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }

    @Test
    public void noCondition_Then_WEAK() {
        //given

        //when

        //then
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
