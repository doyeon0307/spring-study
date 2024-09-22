package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
  // 테스트 대상
  MemoryMemberRepository repository = new MemoryMemberRepository();

  // @가 하나 끝날 때마다 실행되는 콜백 함수
  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("spring");
    repository.save(member);

    // getId의 반환타입이 Optional이므로 get으로 값을 꺼냄
    Member result = repository.findById(member.getId()).get();


    // 검증: 저장한 member와 db에서 꺼낸 member가 동일해야 한다
    // assertj가 제공하는 Assertions.assertThat 이용
    // member가 result와 같은가
    Assertions.assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    // spring2로 대체하면 빨간줄
    Member result = repository.findByName("spring1").get();
    Assertions.assertThat(result).isEqualTo(member1);
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring1");
    repository.save(member2);

    List<Member> result = repository.findAll();
    Assertions.assertThat(result.size()).isEqualTo(2);

    // 클래스 통채로 테스트를 돌릴 때, 메스드 간의 순서가 보장되지 않는다
    // finaAll이 먼저 실행되면 member1이라는 객체가 이미 생성이 된 상태로 findByName이 수행되어
    // findByName은 단독으로 돌렸을 때 문제가 없었음에도 에러가 발생한다
    // 따라서 모든 테스트는 종료 후 clear가 필요하다 => AfterEach
  }
}
