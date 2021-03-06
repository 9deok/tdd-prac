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
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    public void conditionWithOut_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    @Test
    public void conditionWithOut_Number_Then_Normal() {
        assertStrength("abcd!!@A", PasswordStrength.NORMAL);
    }

    @Test
    public void conditionWithOut_Uppercase_Then_Normal() {
        assertStrength("abcd1!@a", PasswordStrength.NORMAL);
    }

    @Test
    public void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    public void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    public void conditionOnlyLength_Then_Weak() {
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    public void conditionOnlyNumber_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    public void conditionOnlyUppercase_Then_Weak() {
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }

    @Test
    public void noCondition_Then_WEAK() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
