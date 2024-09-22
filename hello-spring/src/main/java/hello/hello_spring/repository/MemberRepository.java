package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
  Member save(Member member);
  // optianal: 결과가 null인 경우 처리
  Optional<Member> findById(Long id);
  Optional<Member> findByName(String name);
  List<Member> findAll();
}
