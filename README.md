# 미션 - 영화관 예매 시스템

## 🔍 진행 방식

- 미션은 **기능 요구 사항, 프로그래밍 요구 사항, 과제 진행 요구 사항** 세 가지로 구성되어 있다.
- 세 개의 요구 사항을 만족하기 위해 노력한다. 특히 기능을 구현하기 전에 기능 목록을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.

## 🚨 과제 제출 전 체크 리스트 - 0점 방지

- 기능 구현을 모두 정상적으로 했더라도 **요구 사항에 명시된 출력값 형식을 지키지 않을 경우 0점으로 처리**한다.
- 기능 구현을 완료한 뒤 아래 가이드에 따라 테스트를 실행했을 때 모든 테스트가 성공하는지 확인한다.
- **테스트가 실패할 경우 0점으로 처리**되므로, 반드시 확인 후 제출한다.

### 테스트 실행 가이드

- 터미널에서 `java -version`을 실행하여 Java 버전이 21인지 확인한다. Eclipse 또는 IntelliJ IDEA와 같은 IDE에서 Java 21로 실행되는지 확인한다.
- 터미널에서 Mac 또는 Linux 사용자의 경우 `./gradlew clean test` 명령을 실행하고, Windows 사용자의 경우 `gradlew.bat clean test` 또는 `.\gradlew.bat clean test` 명령을 실행할 때 모든 테스트가 아래와 같이 통과하는지 확인한다.

```
BUILD SUCCESSFUL in 0s
```

---

## 🚀 기능 요구 사항

우아한 시네마의 영화 예매 시스템을 구현한다.

### 배경

우아한 시네마는 최신 영화를 상영하는 멀티플렉스 영화관이다. 고객이 상영 시간표를 확인하고, 좌석을 선택하고, 할인을 적용하여 영화를 예매할 수 있는 시스템을 개발하시오.

---

### 도메인 설명

#### 상영관
- 영화관에는 여러 개의 상영관이 있다.
- 각 상영관은 고유한 번호와 좌석 배치를 가진다.
- 좌석은 행(A~H)과 열(1~10)로 구성된다. (총 80석)

#### 좌석 등급
- 좌석은 `일반`, `프리미엄` 두 가지 등급이 있다.
- 행 A~F는 일반석, 행 G~H는 프리미엄석이다.
- 프리미엄석은 일반석보다 2,000원 추가된다.

#### 상영 시간대
- 상영 시간대는 `조조`, `일반`, `심야` 세 가지가 있다.
- 조조: 첫 상영 시작 시간이 10:00 이전
- 심야: 상영 시작 시간이 22:00 이후
- 일반: 그 외 시간대
- 조조/심야는 기본 요금에서 2,000원 할인된다.

#### 관람 요금
- 기본 요금: 14,000원
- 프리미엄석 추가: +2,000원
- 조조/심야 할인: -2,000원

#### 관람객 할인
- 청소년(만 13세~18세): 20% 할인
- 경로(만 65세 이상): 30% 할인
- 장애인: 50% 할인
- 우대 할인은 **중복 적용되지 않으며**, 가장 큰 할인율 하나만 적용된다.

---

### 기능 요구 사항

#### 1. 데이터 로드
- 프로그램 시작 시 `src/main/resources/movies.md`에서 영화 목록을 불러온다.
- 프로그램 시작 시 `src/main/resources/schedules.md`에서 상영 시간표를 불러온다.

#### 2. 영화 목록 조회
- 현재 상영 중인 영화 목록을 출력한다.
- 각 영화의 제목, 상영 시간, 등급(연령 제한)을 표시한다.

#### 3. 상영 시간표 조회
- 영화를 선택하면 해당 영화의 상영 시간표를 출력한다.
- 상영관 번호, 상영 시작 시간, 잔여 좌석 수를 표시한다.
- 상영 시간대(조조/일반/심야)를 함께 표시한다.

#### 4. 좌석 조회
- 상영 시간표를 선택하면 해당 상영관의 좌석 배치도를 출력한다.
- 예매된 좌석과 빈 좌석을 구분하여 표시한다.
- 좌석 등급(일반/프리미엄)을 구분하여 표시한다.

#### 5. 좌석 선택
- 예매할 좌석을 선택한다.
- 여러 좌석을 한 번에 선택할 수 있다. (최대 8석)
- 이미 예매된 좌석은 선택할 수 없다.
- 선택한 좌석이 **연속된 좌석**인지 검증한다.
  - 같은 행에서 붙어있는 좌석만 선택 가능하다.
  - 단, 1석만 선택하는 경우는 예외로 연속 검증을 하지 않는다.

#### 6. 관람객 정보 입력
- 선택한 좌석 수만큼 관람객 정보를 입력받는다.
- 각 관람객의 생년월일을 입력받아 나이를 계산한다.
- 나이에 따라 적용 가능한 할인을 자동으로 판단한다.
- 장애인 여부는 별도로 입력받는다.

#### 7. 요금 계산
- **좌석별 기본 요금 계산**
  - 기본 요금: 14,000원
  - 프리미엄석: +2,000원
  - 조조/심야: -2,000원
- **관람객 할인 적용**
  - 청소년/경로/장애인 할인 중 가장 큰 할인율 적용
  - 할인은 좌석별 기본 요금에 적용
- **총 결제 금액**: 모든 좌석의 할인 적용 금액 합계

#### 8. 예매 완료
- 예매 내역을 확인하고 예매를 완료한다.
- 예매 완료 시 예매 번호를 발급한다.
- 예매된 좌석은 해당 상영에서 더 이상 선택할 수 없다.

#### 9. 예매 조회 및 취소
- 예매 번호로 예매 내역을 조회할 수 있다.
- 예매를 취소할 수 있다.
  - 상영 시작 1시간 전까지만 취소 가능하다.
  - 취소 시 좌석은 다시 예매 가능 상태가 된다.

---

### 예외 처리

- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 해당 부분부터 입력을 다시 받는다.
  - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

---

## 💾 사전 제공 정보

### 영화 목록 (src/main/resources/movies.md)

```
영화코드,제목,상영시간,등급
MV001,아바타: 물의 길,192,12세 이상
MV002,범죄도시3,105,15세 이상
MV003,스즈메의 문단속,122,전체 관람가
MV004,존 윅 4,169,청소년 관람불가
MV005,슈퍼 마리오 브라더스,92,전체 관람가
MV006,가디언즈 오브 갤럭시 Vol.3,150,12세 이상
```

### 상영 시간표 (src/main/resources/schedules.md)

```
상영코드,영화코드,상영관,상영시작시간
SC001,MV001,1,09:00
SC002,MV001,1,13:00
SC003,MV001,2,17:00
SC004,MV002,1,10:30
SC005,MV002,2,14:00
SC006,MV002,2,19:30
SC007,MV002,3,22:30
SC008,MV003,3,09:30
SC009,MV003,1,16:00
SC010,MV004,2,21:00
SC011,MV004,3,23:00
SC012,MV005,3,11:00
SC013,MV005,1,14:30
SC014,MV006,2,10:00
SC015,MV006,3,15:30
```

- 상영 날짜는 현재 날짜로 가정한다.
- 두 파일의 내용은 수정이 가능하다. 단, 형식은 유지해야 한다.

---

## ✍🏻 입출력 요구 사항

### 기능 선택

프로그램 시작 시 환영 메시지와 기능 메뉴를 출력한다.

```
안녕하세요. 우아한 시네마입니다. 🎬

기능을 선택하세요.
1. 영화 예매
2. 예매 확인
3. 예매 취소
Q. 종료
```

### 1. 영화 예매

#### 영화 목록 조회

```
[영화 목록]

No  제목                          상영시간    등급
===========================================================
1   아바타: 물의 길               192분      12세 이상
2   범죄도시3                     105분      15세 이상
3   스즈메의 문단속               122분      전체 관람가
4   존 윅 4                       169분      청소년 관람불가
5   슈퍼 마리오 브라더스          92분       전체 관람가
6   가디언즈 오브 갤럭시 Vol.3    150분      12세 이상
===========================================================

예매할 영화 번호를 입력하세요.
2
```

#### 상영 시간표 조회

```
[범죄도시3 상영 시간표]

No  상영관    시작시간    종료시간    시간대    잔여좌석
==========================================================
1   1관      10:30      12:15      조조      72석
2   2관      14:00      15:45      일반      80석
3   2관      19:30      21:15      일반      65석
4   3관      22:30      00:15      심야      80석
==========================================================

예매할 상영 번호를 입력하세요.
1
```

#### 좌석 조회

```
[1관 좌석 배치도]

             === 스크린 ===

     1   2   3   4   5   6   7   8   9  10
  +----------------------------------------+
A |  O   O   O   O   O   O   O   O   O   O  |
B |  O   O   O   O   X   X   O   O   O   O  |
C |  O   O   O   O   O   O   O   O   O   O  |
D |  O   O   X   X   X   O   O   O   O   O  |
E |  O   O   O   O   O   O   O   O   O   O  |
F |  O   O   O   O   O   O   O   X   X   X  |
  +----------------------------------------+
G | [O] [O] [O] [O] [O] [O] [O] [O] [O] [O] | ★프리미엄
H | [O] [O] [O] [O] [O] [O] [O] [O] [O] [O] | ★프리미엄
  +----------------------------------------+

O: 선택가능 | X: 예매완료 | [O]: 프리미엄 선택가능 | [X]: 프리미엄 예매완료

잔여 좌석: 72석 (일반 56석 / 프리미엄 16석)
```

#### 좌석 선택

```
예매할 좌석 수를 입력하세요. (최대 8석)
3

예매할 좌석을 입력하세요. (예: A1,A2,A3)
G3,G4,G5

선택한 좌석: G3, G4, G5 (프리미엄석)
```

#### 좌석 선택 에러 - 연속되지 않은 좌석

```
예매할 좌석을 입력하세요. (예: A1,A2,A3)
A1,A3,A5

[ERROR] 좌석은 같은 행에서 연속으로 선택해야 합니다.
```

#### 좌석 선택 에러 - 다른 행 선택

```
예매할 좌석을 입력하세요. (예: A1,A2,A3)
A1,B1,C1

[ERROR] 좌석은 같은 행에서 연속으로 선택해야 합니다.
```

#### 좌석 선택 에러 - 이미 예매된 좌석

```
예매할 좌석을 입력하세요. (예: A1,A2,A3)
B4,B5,B6

[ERROR] 이미 예매된 좌석이 포함되어 있습니다. (B5, B6)
```

#### 관람객 정보 입력

```
[관람객 정보 입력]

3명의 관람객 정보를 입력해 주세요.

1번째 관람객 생년월일을 입력하세요. (예: 2000-01-15)
1990-05-20

장애인 할인을 적용하시겠습니까? (Y/N)
N

→ 성인 (할인 없음)

2번째 관람객 생년월일을 입력하세요. (예: 2000-01-15)
2008-03-10

장애인 할인을 적용하시겠습니까? (Y/N)
N

→ 청소년 (20% 할인)

3번째 관람객 생년월일을 입력하세요. (예: 2000-01-15)
1955-12-25

장애인 할인을 적용하시겠습니까? (Y/N)
Y

→ 경로 + 장애인 → 장애인 할인 적용 (50% 할인)
```

#### 요금 계산 및 예매 확인

```
[예매 정보]

영화: 범죄도시3
상영: 1관 | 10:30 (조조)
좌석: G3, G4, G5 (프리미엄석)
날짜: 2024-01-15

=================================================
좌석      기본요금    좌석추가    시간할인    관람객할인    금액
=================================================
G3       14,000원   +2,000원   -2,000원        0원    14,000원
         (프리미엄)             (조조)       (성인)
-------------------------------------------------
G4       14,000원   +2,000원   -2,000원   -2,800원    11,200원
         (프리미엄)             (조조)     (청소년)
-------------------------------------------------
G5       14,000원   +2,000원   -2,000원   -7,000원     7,000원
         (프리미엄)             (조조)     (장애인)
=================================================
총 결제 금액                                        32,200원
=================================================

예매를 완료하시겠습니까? (Y/N)
Y

예매가 완료되었습니다! 🎉
예매 번호: WC20240115001
즐거운 관람 되세요!
```

### 2. 예매 확인

```
[예매 확인]

예매 번호를 입력하세요.
WC20240115001

=================================================
예매 번호: WC20240115001
=================================================
영화: 범죄도시3
상영: 1관 | 2024-01-15 10:30 (조조)
좌석: G3, G4, G5
인원: 성인 1명, 청소년 1명, 장애인 1명
결제 금액: 32,200원
=================================================
```

#### 예매 확인 에러 - 존재하지 않는 예매

```
예매 번호를 입력하세요.
WC99999999999

[ERROR] 존재하지 않는 예매 번호입니다.
```

### 3. 예매 취소

```
[예매 취소]

예매 번호를 입력하세요.
WC20240115001

=================================================
예매 번호: WC20240115001
=================================================
영화: 범죄도시3
상영: 1관 | 2024-01-15 10:30 (조조)
좌석: G3, G4, G5
결제 금액: 32,200원
=================================================

예매를 취소하시겠습니까? (Y/N)
Y

예매가 취소되었습니다.
```

#### 예매 취소 에러 - 상영 1시간 전 이후

```
예매 번호를 입력하세요.
WC20240115001

[ERROR] 상영 시작 1시간 전까지만 취소가 가능합니다.
상영 시작: 10:30 | 현재 시간: 09:45
```

### 에러 메시지

- 존재하지 않는 영화 번호: `[ERROR] 존재하지 않는 영화입니다.`
- 존재하지 않는 상영 번호: `[ERROR] 존재하지 않는 상영 시간표입니다.`
- 잘못된 좌석 형식: `[ERROR] 잘못된 좌석 형식입니다. (예: A1,A2,A3)`
- 존재하지 않는 좌석: `[ERROR] 존재하지 않는 좌석입니다. ({좌석})`
- 이미 예매된 좌석: `[ERROR] 이미 예매된 좌석이 포함되어 있습니다. ({좌석})`
- 연속되지 않은 좌석: `[ERROR] 좌석은 같은 행에서 연속으로 선택해야 합니다.`
- 최대 좌석 수 초과: `[ERROR] 최대 8석까지 예매할 수 있습니다.`
- 좌석 수 불일치: `[ERROR] 선택한 좌석 수({N}석)와 입력한 좌석 수({M}석)가 일치하지 않습니다.`
- 잘못된 생년월일: `[ERROR] 잘못된 생년월일 형식입니다. (예: 2000-01-15)`
- 연령 제한 위반: `[ERROR] {등급} 영화는 관람이 불가능합니다.`
- 존재하지 않는 예매: `[ERROR] 존재하지 않는 예매 번호입니다.`
- 취소 불가 시간: `[ERROR] 상영 시작 1시간 전까지만 취소가 가능합니다.`

---

## 🎯 프로그래밍 요구 사항

### 프로그래밍 요구 사항 1

- JDK 21 버전에서 실행 가능해야 한다. **JDK 21에서 정상적으로 동작하지 않을 경우 0점 처리한다.**
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일을 변경할 수 없고, 제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  - 기본적으로 [Java Style Guide](https://github.com/woowacourse/woowacourse-docs/blob/main/styleguide/java)를 원칙으로 한다.

### 프로그래밍 요구 사항 2

- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
  - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
  - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 정리한 기능 목록이 정상적으로 작동하는지 테스트 코드로 확인한다.
  - 테스트 도구 사용법이 익숙하지 않다면 아래 문서를 참고하여 학습한 후 테스트를 구현한다.
    - [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
    - [AssertJ User Guide](https://assertj.github.io/doc/)
    - [AssertJ Exception Assertions](https://www.baeldung.com/assertj-exception-assertion)
    - [Guide to JUnit 5 Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5)

### 프로그래밍 요구 사항 3

- else 예약어를 쓰지 않는다.
  - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
  - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
- Java Enum을 적용하여 프로그램을 구현한다.
- 구현한 기능에 대한 단위 테스트를 작성한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.

### 프로그래밍 요구 사항 4

- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
  - 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- 입출력을 담당하는 클래스를 별도로 구현한다.
  - 아래 `InputView`, `OutputView` 클래스를 참고하여 입출력 클래스를 구현한다.
  - 클래스 이름, 메소드 반환 유형, 시그니처 등은 자유롭게 수정할 수 있다.

```java
public class InputView {
    public int readMovieNumber() {
        System.out.println("예매할 영화 번호를 입력하세요.");
        String input = Console.readLine();
        // ...
    }
    // ...
}
```

```java
public class OutputView {
    public void printMovieList(List<MovieDto> movies) {
        System.out.println("[영화 목록]");
        // ...
    }
    
    public void printSeatMap(SeatMapDto seatMap) {
        // 좌석 배치도 출력
    }
    // ...
}
```

### 라이브러리

- `camp.nextstep.edu.missionutils`에서 제공하는 `DateTimes` 및 `Console` API를 사용하여 구현해야 한다.
  - 현재 날짜와 시간을 가져오려면 `camp.nextstep.edu.missionutils.DateTimes`의 `now()`를 활용한다.
  - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

## ✏️ 과제 진행 요구 사항

- 미션은 [java-cinema](https://github.com/woowacourse-precourse/java-cinema) 저장소를 비공개 저장소로 생성해 시작한다.
- **기능을 구현하기 전 `docs/README.md`에 구현할 기능 목록을 정리**해 추가한다.
- **Git의 커밋 단위는 앞 단계에서 `docs/README.md`에 정리한 기능 목록 단위**로 추가한다.
  - [커밋 메시지 컨벤션](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 가이드를 참고해 커밋 메시지를 작성한다.
- 과제 진행 및 제출 방법은 [프리코스 과제 제출](https://docs.google.com/document/d/1cmg0VpPkuvdaetxwp4hnyyFC_G-1f2Gr8nIDYIWcKC8/edit?usp=sharing) 문서를 참고한다.

---

## ❗ 힌트

아래의 힌트를 참고하여 진행해도 좋다. 반드시 아래의 힌트를 따라야 하는 것은 아니며 사용하지 않아도 되고 수정도 가능하다.

### 영화 등급

```java
public enum MovieRating {
    ALL("전체 관람가", 0),
    TWELVE("12세 이상", 12),
    FIFTEEN("15세 이상", 15),
    ADULT("청소년 관람불가", 19);

    private final String description;
    private final int minimumAge;

    MovieRating(String description, int minimumAge) {
        this.description = description;
        this.minimumAge = minimumAge;
    }

    public boolean canWatch(int age) {
        return age >= minimumAge;
    }

    // 추가 기능 구현
}
```

### 좌석 등급

```java
public enum SeatGrade {
    STANDARD("일반", 0),
    PREMIUM("프리미엄", 2000);

    private final String name;
    private final int additionalPrice;

    SeatGrade(String name, int additionalPrice) {
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    // 추가 기능 구현
}
```

### 상영 시간대

```java
public enum TimeSlot {
    MORNING("조조", 2000),   // 10:00 이전
    REGULAR("일반", 0),
    LATE_NIGHT("심야", 2000); // 22:00 이후

    private final String name;
    private final int discount;

    TimeSlot(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }

    public static TimeSlot from(LocalTime time) {
        if (time.isBefore(LocalTime.of(10, 0))) {
            return MORNING;
        }
        if (time.isAfter(LocalTime.of(22, 0)) || time.equals(LocalTime.of(22, 0))) {
            return LATE_NIGHT;
        }
        return REGULAR;
    }

    // 추가 기능 구현
}
```

### 관람객 유형

```java
public enum AudienceType {
    ADULT("성인", 0),
    TEENAGER("청소년", 20),    // 만 13~18세
    SENIOR("경로", 30),        // 만 65세 이상
    DISABLED("장애인", 50);

    private final String name;
    private final int discountPercent;

    AudienceType(String name, int discountPercent) {
        this.name = name;
        this.discountPercent = discountPercent;
    }

    // 추가 기능 구현
}
```

### 좌석

```java
public class Seat {
    private final char row;        // A~H
    private final int column;      // 1~10
    private final SeatGrade grade;
    private boolean reserved;

    public String getPosition() {
        return String.valueOf(row) + column; // "A1", "G5" 등
    }

    // 추가 기능 구현
}
```

### 좌석 연속성 검증

```java
public class SeatValidator {
    public static boolean areConsecutive(List<Seat> seats) {
        if (seats.size() <= 1) {
            return true;
        }

        // 같은 행인지 확인
        char row = seats.get(0).getRow();
        boolean sameRow = seats.stream().allMatch(s -> s.getRow() == row);
        if (!sameRow) {
            return false;
        }

        // 열 번호가 연속인지 확인
        List<Integer> columns = seats.stream()
                .map(Seat::getColumn)
                .sorted()
                .toList();

        for (int i = 1; i < columns.size(); i++) {
            if (columns.get(i) - columns.get(i - 1) != 1) {
                return false;
            }
        }
        return true;
    }
}
```

### 요금 계산

```java
public class PriceCalculator {
    private static final int BASE_PRICE = 14000;

    public static int calculate(Seat seat, TimeSlot timeSlot, AudienceType audienceType) {
        int price = BASE_PRICE;
        price += seat.getGrade().getAdditionalPrice();
        price -= timeSlot.getDiscount();
        price -= calculateAudienceDiscount(price, audienceType);
        return price;
    }

    private static int calculateAudienceDiscount(int price, AudienceType type) {
        return price * type.getDiscountPercent() / 100;
    }
}
```

### 예매

```java
public class Reservation {
    private final String reservationNumber;
    private final Schedule schedule;
    private final List<Seat> seats;
    private final List<Audience> audiences;
    private final int totalPrice;
    private final LocalDateTime createdAt;

    // 추가 기능 구현
}
```

### 예매 번호 생성

```java
public class ReservationNumberGenerator {
    private static int sequence = 0;

    public static String generate(LocalDate date) {
        sequence++;
        String dateStr = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String seqStr = String.format("%03d", sequence);
        return "WC" + dateStr + seqStr; // WC20240115001
    }
}
```
