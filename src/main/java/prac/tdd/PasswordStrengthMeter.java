package prac.tdd;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int conditionCount = getConditionCount(s);

        if(conditionCount <= 1) {
            return PasswordStrength.WEAK;
        }
        if(conditionCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private int getConditionCount(String s) {
        int conditionCount = 0;
        if(s.length() >= 8) {
            conditionCount++;
        }
        if(meetsContainingNumber(s)) {
            conditionCount++;
        }
        if(meetsContainingUppercase(s)) {
            conditionCount++;
        }
        return conditionCount;
    }

    private boolean meetsContainingUppercase(String s) {
        for (char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingNumber(String s) {
        for (char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
