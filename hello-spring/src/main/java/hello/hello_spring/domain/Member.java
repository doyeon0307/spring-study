package hello.hello_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity -> JPA에서 관리하는 모델이다!
@Entity
public class Member {

  // ID는 디비가 자동으로 생성해준다. 이를 IDENTITY라 한다
  // ID는 column 이름 그대로 사용한 것이어서 매핑된다
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
