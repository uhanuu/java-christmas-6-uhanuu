package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventBadgeTest {

    @Test
    @DisplayName("할인 금액이 5000미만이면 없음을 반환한다.")
    public void noneTest() {
        // given
        int totalEventPrice = 4999;
        // when
        EventBadge badge = EventBadge.getBadgeByTotalEventPrice(totalEventPrice);
        // then
        String name = badge.getName();
        assertThat(name).isEqualTo("없음");
    }

    @Test
    @DisplayName("할인 금액이 5000원 이상 10000미만이면 별을 반환한다.")
    public void starTest() {
        // given
        int totalEventPrice = 9999;
        // when
        EventBadge badge = EventBadge.getBadgeByTotalEventPrice(totalEventPrice);
        // then
        String name = badge.getName();
        assertThat(name).isEqualTo("별");
    }

    @Test
    @DisplayName("할인 금액이 5000원 이상 20000미만이면 트리를 반환한다.")
    public void treeTest() {
        // given
        int totalEventPrice = 19999;
        // when
        EventBadge badge = EventBadge.getBadgeByTotalEventPrice(totalEventPrice);
        // then
        String name = badge.getName();
        assertThat(name).isEqualTo("트리");
    }

    @Test
    @DisplayName("할인 금액이 20000이상이면 산타를 반환한다.")
    public void santaTest() {
        // given
        int totalEventPrice = 20000;
        // when
        EventBadge badge = EventBadge.getBadgeByTotalEventPrice(totalEventPrice);
        // then
        String name = badge.getName();
        assertThat(name).isEqualTo("산타");
    }
}