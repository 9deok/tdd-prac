package prac.tdd;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayData {
    private LocalDate billingDate;
    private LocalDate firstBillingDate;
    private int payAmount;
}
