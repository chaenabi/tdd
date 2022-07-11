package tdd;

public class PasswordCheck {

    public PasswordStrength check(String password) {
        if (password == null || password.isEmpty()) return PasswordStrength.INVALID;
        int metCount = 0;
        metCount = getMetCount(password);
        if (metCount == 3) return PasswordStrength.STRONG;
        if (metCount == 2) return PasswordStrength.NORMAL;
        if (metCount == 1) return PasswordStrength.NOTGOOD;
        if (metCount == 0) return PasswordStrength.INVALID;
        return PasswordStrength.INVALID;
    }

    private int getMetCount(String password) {
        int count = 0;
        if (password.length() >= 8) count++;
        if (isContainsNumber(password)) count++;
        if (isContainsUpper(password)) count++;
        return count;
    }

    private boolean isContainsUpper(String password) {
        boolean isContainsUpper = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                isContainsUpper = true;
                break;
            }
        }
        return isContainsUpper;
    }

    private boolean isContainsNumber(String password) {
        boolean isContainsNumber = false;
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                isContainsNumber = true;
                break;
            }
        }
        return isContainsNumber;
    }

}

enum PasswordStrength {
    NOTGOOD, NORMAL, STRONG, INVALID
}