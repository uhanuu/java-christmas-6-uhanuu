package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorPrefixTest {

    @Test
    @DisplayName("[ERROR] 로 시작하는 prefix를 반환한다.")
    public void getErrorPrefix() {
        //given
        String prefix = "[ERROR] ";
        // when
        String errorPrefix = ErrorPrefix.PREFIX.getErrorPrefix();
        // then
        assertThat(prefix).isEqualTo(errorPrefix);
    }

}