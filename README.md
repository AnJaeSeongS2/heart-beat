# heart-beat

---
* (원래 목적) : native image를 테스트 한다. graalvm 21.3.0 + spring-native
* 사람의 심장박동수를 모사한 Mock
* 나이별, 운동 상황별 적절히 심장 박동을 하게 끔 모사한다.
* 심장 박동 시작이 있듯이, 심 정지도 있다.
* 심 정지에 대한 후속처리는 이 프로젝트의 역할이 아니다.


---

## 연령별 심박수 예시
* [연령별 심박수 표](doc/resources/heart-beat-pattern-with-age.png)
* 정상 심박수는 연령에 따라 차이가 있으며, 영아는 정상적으로 빠른 박동수 (분당 140-160회)를 보이며, 소아기, 청소년기가 되면서 점차 정상 박동수가 성인과 비슷한 정도 (분당 60-80회)로 감소합니다.

## 운동 중 심박수 예시
* 가벼운 운동(산책, 하이킹)은 심박수 120이하
* 강한 운동(줄넘기, 달리기)은 심박수 120~160
* 격한 운동(농구, 축구)은 심박수 160 이상이 되었을 때를 말합니다.

---
## 빌드 하는 법
* OOM on build:
  * Exception in thread "native-image pid watcher" java.lang.OutOfMemoryError: GC overhead limit exceeded
  * https://github.com/spring-projects-experimental/spring-native/issues/650#issuecomment-804632209
  * 추천대로면, Macos 에서는 8GB의 메모리를 docker에 줘야한단다....;;;
    * https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#_system_requirements
    * On MacOS, it is recommended to increase the memory allocated to Docker to at least 8GB, and potentially add more CPUs as well. See this Stackoverflow answer for more details. On Microsoft Windows, make sure to enable the Docker WSL 2 backend for better performance.