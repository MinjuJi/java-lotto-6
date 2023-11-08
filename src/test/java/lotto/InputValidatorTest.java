package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validator.InputValidator;

public class InputValidatorTest {
    @DisplayName("구입금액이 1,000원 단위로 입력되었는지 검사")
    @Test
    void 구입금액_천_단위_테스트() {
        // given
        int case1 = 5_500;
        int case2 = 5_000;

        // when
        Throwable result1 = catchThrowable(() -> {
            InputValidator.validatePurchaseAmount(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            InputValidator.validatePurchaseAmount(case2);
        });

        // then
        assertThat(result1).isInstanceOf(IllegalArgumentException.class);
        assertThat(result2).doesNotThrowAnyException();
    }

    @DisplayName("구입금액이 숫자로 입력되었는지 검사")
    @Test
    void 구입금액_숫자_테스트() {
        // given
        String case1 = "오천원";
        String case2 = "5000";

        // when
        Throwable result1 = catchThrowable(() -> {
            InputValidator.validateInputIsNumeric(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            InputValidator.validateInputIsNumeric(case2);
        });

        // then
        assertThat(result1).isInstanceOf(IllegalArgumentException.class);
        assertThat(result2).doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호가 콤마로 구분되어 입력되었는지 검사")
    @Test
    void 당첨_번호_콤마_구분_테스트() {
        // given
        String case1 = "123456";
        String case2 = "1/2/3/4/5/6";
        String case3 = "1,2,3/4/5/6";
        String case4 = "1,2,3,4,5,6";

        // when
        Throwable result1 = catchThrowable(() -> {
            InputValidator.validateSeparatedByComma(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            InputValidator.validateSeparatedByComma(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            InputValidator.validateSeparatedByComma(case3);
        });
        Throwable result4 = catchThrowable(() -> {
            InputValidator.validateSeparatedByComma(case4);
        });

        // then
        assertThat(result1).isInstanceOf(IllegalArgumentException.class);
        assertThat(result2).isInstanceOf(IllegalArgumentException.class);
        assertThat(result3).isInstanceOf(IllegalArgumentException.class);
        assertThat(result4).doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호 중 중복된 값이 있는지 검사")
    @Test
    void 당첨_번호_중복_테스트() {
        // given
        List<Integer> case1 = List.of(1, 2, 3, 4, 5, 5);
        List<Integer> case2 = List.of(1, 2, 3, 4, 5, 6);

        // when
        Throwable result1 = catchThrowable(() -> {
            InputValidator.validateLottoNumbersUniqueness(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            InputValidator.validateLottoNumbersUniqueness(case2);
        });

        // then
        assertThat(result1).isInstanceOf(IllegalArgumentException.class);
        assertThat(result2).doesNotThrowAnyException();
    }
}
