package cinema;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @Nested
    @DisplayName("영화 목록 조회 테스트")
    class MovieListTest {

        @Test
        @DisplayName("영화 목록 조회")
        void 영화_목록_조회() {
            assertSimpleTest(() -> {
                run("1", "1", "1", "1", "A1",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("영화 목록");
                assertThat(output()).contains("아바타: 물의 길");
                assertThat(output()).contains("범죄도시3");
                assertThat(output()).contains("192분");
                assertThat(output()).contains("12세 이상");
            });
        }
    }

    @Nested
    @DisplayName("상영 시간표 조회 테스트")
    class ScheduleTest {

        @Test
        @DisplayName("상영 시간표 조회")
        void 상영_시간표_조회() {
            assertSimpleTest(() -> {
                run("1", "2", "1", "1", "A1",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("범죄도시3 상영 시간표");
                assertThat(output()).contains("10:30");
                assertThat(output()).contains("조조");
                assertThat(output()).contains("22:30");
                assertThat(output()).contains("심야");
            });
        }

        @Test
        @DisplayName("존재하지 않는 영화 번호")
        void 존재하지_않는_영화() {
            assertSimpleTest(() -> {
                run("1", "99", "1", "1", "1", "A1",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 존재하지 않는 영화입니다");
            });
        }
    }

    @Nested
    @DisplayName("좌석 조회 테스트")
    class SeatMapTest {

        @Test
        @DisplayName("좌석 배치도 출력")
        void 좌석_배치도_출력() {
            assertSimpleTest(() -> {
                run("1", "2", "1", "1", "A1",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("스크린");
                assertThat(output()).contains("프리미엄");
                assertThat(output()).contains("선택가능");
            });
        }
    }

    @Nested
    @DisplayName("좌석 선택 테스트")
    class SeatSelectionTest {

        @Test
        @DisplayName("정상 좌석 선택 - 연속 좌석")
        void 연속_좌석_선택() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "3", "A1,A2,A3",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("선택한 좌석: A1, A2, A3");
            });
        }

        @Test
        @DisplayName("단일 좌석 선택")
        void 단일_좌석_선택() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "G5",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("선택한 좌석: G5");
                assertThat(output()).contains("프리미엄");
            });
        }

        @Test
        @DisplayName("연속되지 않은 좌석 선택 에러")
        void 비연속_좌석_에러() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "3", "A1,A3,A5", "A1,A2,A3",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 좌석은 같은 행에서 연속으로 선택해야 합니다");
            });
        }

        @Test
        @DisplayName("다른 행 좌석 선택 에러")
        void 다른_행_에러() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "3", "A1,B1,C1", "A1,A2,A3",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 좌석은 같은 행에서 연속으로 선택해야 합니다");
            });
        }

        @Test
        @DisplayName("최대 좌석 수 초과")
        void 최대_좌석_초과() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "9", "8",
                        "A1,A2,A3,A4,A5,A6,A7,A8",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 최대 8석까지 예매할 수 있습니다");
            });
        }

        @Test
        @DisplayName("존재하지 않는 좌석")
        void 존재하지_않는_좌석() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "Z99", "A1",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 존재하지 않는 좌석입니다");
            });
        }

        @Test
        @DisplayName("좌석 수 불일치")
        void 좌석_수_불일치() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "3", "A1,A2", "A1,A2,A3",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "2000-01-15", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 선택한 좌석 수");
                assertThat(output()).contains("일치하지 않습니다");
            });
        }
    }

    @Nested
    @DisplayName("관람객 정보 입력 테스트")
    class AudienceTest {

        @Test
        @DisplayName("성인 관람객")
        void 성인_관람객() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).contains("성인");
            });
        }

        @Test
        @DisplayName("청소년 관람객")
        void 청소년_관람객() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "2008-03-10", "N",
                        "N", "Q");
                assertThat(output()).contains("청소년");
                assertThat(output()).contains("20%");
            });
        }

        @Test
        @DisplayName("경로 관람객")
        void 경로_관람객() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1955-12-25", "N",
                        "N", "Q");
                assertThat(output()).contains("경로");
                assertThat(output()).contains("30%");
            });
        }

        @Test
        @DisplayName("장애인 할인")
        void 장애인_할인() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1990-05-20", "Y",
                        "N", "Q");
                assertThat(output()).contains("장애인");
                assertThat(output()).contains("50%");
            });
        }

        @Test
        @DisplayName("경로 + 장애인 중복 시 장애인만 적용")
        void 중복_할인_최대적용() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1955-12-25", "Y",
                        "N", "Q");
                assertThat(output()).contains("장애인 할인 적용");
                assertThat(output()).contains("50%");
            });
        }

        @Test
        @DisplayName("잘못된 생년월일 형식")
        void 잘못된_생년월일() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "19900520", "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 잘못된 생년월일 형식입니다");
            });
        }
    }

    @Nested
    @DisplayName("요금 계산 테스트")
    class PriceCalculationTest {

        @Test
        @DisplayName("일반석 + 일반 시간 + 성인")
        void 기본_요금() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1990-05-20", "N",
                        "Y", "Q");
                assertThat(output()).contains("14,000원");
            });
        }

        @Test
        @DisplayName("프리미엄석 추가 요금")
        void 프리미엄석_추가() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "G1",
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).contains("프리미엄");
                assertThat(output()).contains("+2,000원");
            });
        }

        @Test
        @DisplayName("조조 할인")
        void 조조_할인() {
            assertSimpleTest(() -> {
                run("1", "2", "1", "1", "A1", // 10:30 조조
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).contains("조조");
                assertThat(output()).contains("-2,000원");
            });
        }

        @Test
        @DisplayName("심야 할인")
        void 심야_할인() {
            assertSimpleTest(() -> {
                run("1", "2", "4", "1", "A1", // 22:30 심야
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).contains("심야");
                assertThat(output()).contains("-2,000원");
            });
        }

        @Test
        @DisplayName("청소년 할인 계산")
        void 청소년_할인_계산() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "2008-03-10", "N",
                        "N", "Q");
                // 14,000 × 20% = 2,800원 할인
                assertThat(output()).contains("-2,800원");
            });
        }
    }

    @Nested
    @DisplayName("예매 완료 테스트")
    class ReservationCompleteTest {

        @Test
        @DisplayName("예매 완료")
        void 예매_완료() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1990-05-20", "N",
                        "Y", "Q");
                assertThat(output()).contains("예매가 완료되었습니다");
                assertThat(output()).contains("예매 번호: WC");
            });
        }

        @Test
        @DisplayName("예매 취소")
        void 예매_취소() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "A1",
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).doesNotContain("예매가 완료되었습니다");
            });
        }
    }

    @Nested
    @DisplayName("예매 조회 테스트")
    class ReservationLookupTest {

        @Test
        @DisplayName("존재하지 않는 예매 번호")
        void 존재하지_않는_예매() {
            assertSimpleTest(() -> {
                run("2", "WC99999999999", "Q");
                assertThat(output()).contains("[ERROR] 존재하지 않는 예매 번호입니다");
            });
        }
    }

    @Nested
    @DisplayName("연령 제한 테스트")
    class AgeRestrictionTest {

        @Test
        @DisplayName("청소년 관람불가 영화 - 성인 관람")
        void 청불_성인_관람() {
            assertSimpleTest(() -> {
                run("1", "4", "1", "1", "A1", // 존 윅 4 (청불)
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).doesNotContain("[ERROR]");
            });
        }

        @Test
        @DisplayName("청소년 관람불가 영화 - 청소년 관람 불가")
        void 청불_청소년_관람불가() {
            assertSimpleTest(() -> {
                run("1", "4", "1", "1", "A1", // 존 윅 4 (청불)
                        "2008-03-10", "2000-01-01", "N", // 청소년 생년월일 입력 후 재입력
                        "N", "Q");
                assertThat(output()).contains("[ERROR]");
                assertThat(output()).contains("관람이 불가능합니다");
            });
        }
    }

    @Nested
    @DisplayName("입력 예외 테스트")
    class InputExceptionTest {
        @Test
        @DisplayName("잘못된 메뉴 선택")
        void 잘못된_메뉴_선택() {
            assertSimpleTest(() -> {
                run("X", "Q");
                assertThat(output()).contains("[ERROR]");
            });
        }

        @Test
        @DisplayName("잘못된 좌석 형식")
        void 잘못된_좌석_형식() {
            assertSimpleTest(() -> {
                run("1", "2", "2", "1", "가나다", "A1",
                        "1990-05-20", "N",
                        "N", "Q");
                assertThat(output()).contains("[ERROR] 잘못된 좌석 형식입니다");
            });
        }
    }
}
