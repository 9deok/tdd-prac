package prac.tdd;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addMonth = getMonth(payData.getPayAmount());

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addMonth);
        } else {
            return payData.getBillingDate().plusMonths(addMonth);
        }
    }

    private int getMonth(int payAmount) {
        int month = (payAmount / 100_000) * 12;
        payAmount = payAmount % 100_000;
        month += payAmount / 10_000;
        return month;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addMonth) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addMonth);
        if (!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayLengthOfCandiMon = lastDayOfMonth(candidateExp);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (dayLengthOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLengthOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }

    private boolean isSameDayOfMonth(LocalDate firstBillingDate, LocalDate candidateExp) {
        int dayOfFirstBilling = firstBillingDate.getDayOfMonth();
        int dayOfCandidateExp = candidateExp.getDayOfMonth();
        if (dayOfFirstBilling == dayOfCandidateExp) {
            return true;
        }
        return false;
    }
}
