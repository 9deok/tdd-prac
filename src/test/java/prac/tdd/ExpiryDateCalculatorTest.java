package prac.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    @Test
    public void 만원_납부하면_한달_뒤_만료일이_됨() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2021, 10, 8))
                .payAmount(10000)
                .build(),
            LocalDate.of(2021, 11, 8));
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2021, 2, 3))
                .payAmount(10000)
                .build(),
            LocalDate.of(2021, 3, 3));
    }

    @Test
    public void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2021, 1, 31))
                .payAmount(10000)
                .build(),
            LocalDate.of(2021, 2, 28));
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2021, 5, 31))
                .payAmount(10000)
                .build(),
            LocalDate.of(2021, 6, 30));
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2020, 1, 31))
                .payAmount(10000)
                .build(),
            LocalDate.of(2020, 2, 29));
    }

    @Test
    public void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        //given
        PayData payData = PayData.builder()
            .firstBillingDate(LocalDate.of(2021, 01, 31))
            .billingDate(LocalDate.of(2021, 02, 28))
            .payAmount(10000)
            .build();
        PayData payData2 = PayData.builder()
            .firstBillingDate(LocalDate.of(2021, 01, 30))
            .billingDate(LocalDate.of(2021, 02, 28))
            .payAmount(10000)
            .build();
        PayData payData3 = PayData.builder()
            .firstBillingDate(LocalDate.of(2021, 05, 31))
            .billingDate(LocalDate.of(2021, 6, 30))
            .payAmount(10000)
            .build();

        //when

        //then
        assertExpiryDate(payData, LocalDate.of(2021, 03, 31));
        assertExpiryDate(payData2, LocalDate.of(2021, 03, 30));
        assertExpiryDate(payData3, LocalDate.of(2021, 07, 31));
    }

    @Test
    public void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(PayData.builder()
            .billingDate(LocalDate.of(2021, 3, 1))
            .payAmount(20000)
            .build(), LocalDate.of(2021, 5, 1));

        assertExpiryDate(PayData.builder()
            .billingDate(LocalDate.of(2021, 3, 1))
            .payAmount(30000)
            .build(), LocalDate.of(2021, 6, 1));
    }

    @Test
    public void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 1, 31))
                .billingDate(LocalDate.of(2021, 2, 28))
                .payAmount(20000)
                .build(),
            LocalDate.of(2021, 4, 30));

        assertExpiryDate(PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 3, 31))
                .billingDate(LocalDate.of(2021, 4, 30))
                .payAmount(30000)
                .build(),
            LocalDate.of(2021, 7, 31));
    }

    @Test
    public void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(PayData.builder()
            .billingDate(LocalDate.of(2021, 1, 28))
            .payAmount(100_000)
            .build(), LocalDate.of(2022, 1, 28));
        assertExpiryDate(PayData.builder()
            .billingDate(LocalDate.of(2021, 1, 28))
            .payAmount(200_000)
            .build(), LocalDate.of(2023, 1, 28));
        assertExpiryDate(PayData.builder()
            .billingDate(LocalDate.of(2021, 1, 28))
            .payAmount(130_000)
            .build(), LocalDate.of(2022, 4, 28));
    }


    private void assertExpiryDate(
        PayData payData, LocalDate expectExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectExpiryDate, expiryDate);
    }


}
