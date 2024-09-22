package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemoryMemberRepository memberRepository;
  MemberService memberService;

  // memberService에는 데이터가 저장되지 않는다
  // MemoryMemberRepository에 데이터가 있고, 이걸 초기화해야 한다
  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("hello");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();// 저장소에서 꺼낸 멤버
    org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // when
    memberService.join(member1);
    // 중복 회원을 저장하면 설정한 예외가 터져야 함

    // try-catch 이용 (권장되지 않음)
//    try {
//      memberService.join(member2);
//      //fail();
//    } catch (IllegalStateException e) {
//      Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//    }

    // join에서 정의한 IlegalStateException이 동일하게 발생하는지 확인
    // try-catch 없이 execption을 확인할 수 있다
    IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    // then
  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}