# heart-beat

---
* (원래 목적) : native image를 테스트 한다. graalvm 21.3.0 + spring-native
* 사람의 심장박동수를 모사한 Mock
* 나이별, 운동 상황별 적절히 심장 박동을 하게 끔 모사한다.
* 심장 박동 시작이 있듯이, 심 정지도 있다.
* 심 정지에 대한 후속처리는 이 프로젝트의 역할이 아니다.

---

## spring-native 는 운영중인 프로젝트 개선 방향에 어떤 영향을 끼칠까?
- spring-native 의 장/단점은 공식 문서를 참고한다.
- spring-native build에 macos면 8GB의 dodcker 메모리 할당이 적어도 필요했다.
- macos 8GB모델에서 docker에 8GB전체 할당을 통해 빌드는 가능했으나 [34분](doc/resources/spring-native-build-success.png)이나 걸렸다...
- 실 사용은 jenkins 파이프라인의 마지막 근처 stage에서나 가능할 듯으로 보인다.
- 배포 과정을 완료하기에 너무 지연되는 것이라 생각하기에, release용 브랜치의 경우에나 활용 가능성이 있을 듯 싶다.
- 운영상황에서 기동시간이 빨라야할 필요성이 있는 경우는, 트래픽 폭주로 인한 전 서버 장애상황에 신규 컨테이너가 기동해줘야하는 상황일 것이다. 이에 대한 대응에 부합하므로, spring-native image는 각종 cloud기반 카오스 테스트를 통과함으로써 기존 spring-boot image와의 차별점을 확인해야 할 것이다.

## 연령별 심박수 예시
* [연령별 심박수 표](doc/resources/heart-beat-pattern-with-age.png)
* 정상 심박수는 연령에 따라 차이가 있으며, 영아는 정상적으로 빠른 박동수 (분당 140-160회)를 보이며, 소아기, 청소년기가 되면서 점차 정상 박동수가 성인과 비슷한 정도 (분당 60-80회)로 감소합니다.

## 운동 중 심박수 예시
* 가벼운 운동(산책, 하이킹)은 심박수 120이하
* 강한 운동(줄넘기, 달리기)은 심박수 120~160
* 격한 운동(농구, 축구)은 심박수 160 이상이 되었을 때를 말합니다.

---
## spring-native 로 docker 이미지로 기동시간이 짧게 걸리게 빌드 하는 법
* 
  ```shell 
  gradle bootBuildImage
  ```
* if occured OOM on build ? :
  * Exception in thread "native-image pid watcher" java.lang.OutOfMemoryError: GC overhead limit exceeded
  * https://github.com/spring-projects-experimental/spring-native/issues/650#issuecomment-804632209
  * 추천대로면, Macos 에서는 8GB의 메모리를 docker에 줘야한단다....;;;
    * https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#_system_requirements
    * On MacOS, it is recommended to increase the memory allocated to Docker to at least 8GB, and potentially add more CPUs as well. See this Stackoverflow answer for more details. On Microsoft Windows, make sure to enable the Docker WSL 2 backend for better performance.
  * 