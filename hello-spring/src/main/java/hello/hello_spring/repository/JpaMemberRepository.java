package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

  public JpaMemberRepository(EntityManager em) {
    this.em = em;
  }

  // jpa는 EntityManger로 모든걸 동작한다
  // jpa를 쓰려면 EntityManager를 주입받아야 한다
  private  final EntityManager em;

  @Override
  public Member save(Member member) {
    // 디비에 영구적으로 저장된다
    em.persist(member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    Member member = em.find(Member.class, id);
    return Optional.ofNullable(member);
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    return result.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    // 객체, 정확히는 엔티티를 대상으로 쿼리를 날린다
    return em.createQuery("select m from Member m", Member.class).getResultList();
  }
}
