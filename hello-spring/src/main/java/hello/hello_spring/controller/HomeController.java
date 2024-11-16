package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  // 스프링은 컨테이너를 최우선으로 확인한다
  // "/" url이 이으므로 이게 홈 화면이 된다
  @GetMapping("/")
  public String home() {
    return "home";
  }
  // -> home.html
}
