package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링이 처음 뜰 때 스프링 컨테이너라고 커다란 통이 생김
// 컨트롤러 어노테이션을 달아주면 이 클래스 객체를 생성해서 그 통에 넣어줌
// 그리고 스프링이 이걸 관리해줌
// 이게 스프링 빈

@Controller
public class MemberController {
  private final MemberService memberService;

  // 이 컨트롤러는 MemberService에 의존적이다
  // 그런데 MemberService를 사용하고 싶다고 new로 새로운 객체를 생성하는 건
  // 좋지 않다
  // memberService를 사용하는 다른 컨트롤러와 공용으로 쓸 수 없다
  // -> 스프링 컨테이너로 등록을 한다 (컨스트럭터+어노테이션)

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
  // 서비스에 @Service를 달아준다
  // @Autowired로 컨트롤러와 서비스가 연결된다
  // 이게 의존성 주입

  // 마찬가지로 서비스와 레포지토리도 연결한다
  // 레포지토리에 @Repository를 달아주는데, 주의할 점은
  // MemberRepository는 인터페이스다!
  // 구현체인 MemoryMemberRepository가 진짜 레포지토리다
  // 서비스에 가서도 @Autowired를 써서
  // 서비스와 레포지토리가 연결되게 한다

  // /members/new path에서 createMemberForm.html을 보여준다
  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  // html에서 post로 넘어오면 이거
  // url이 같아도 method에 따라 다른 메서드를 호출한다
  @PostMapping("/members/new")
  public String create(MemberForm form) {
    // 멤버 만들기
    Member member = new Member();
    member.setName(form.getName());

    // 회원가입 시키기 -서비스로 넘김
    memberService.join(member);

    // 홈 화면으로 넘김
    return "redirect:/";
  }


}
