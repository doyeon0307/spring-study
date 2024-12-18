package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// jpa를 쓰려면 항상 Transactional이 필요하다
@Transactional
@Service
public class MemberService {
  // 서비스가 있으려면 레포지토리가 있어야 한다
  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 회원가입
  public Long join(Member member) {
    validateDuplicateMember(member); // 중복 회원 검증

    memberRepository.save(member);
    return member.getId();
  }

  // 전체 회원 조회F
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  // id로 회원 조회
  public Optional<Member> findOne(Long id) {
    return memberRepository.findById(id);
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName()).ifPresent(m -> {
      throw new IllegalStateException("이미 존재하는 회원입니다");
    });
  }

}
