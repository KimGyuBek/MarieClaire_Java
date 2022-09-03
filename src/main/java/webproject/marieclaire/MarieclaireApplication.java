package webproject.marieclaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarieclaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarieclaireApplication.class, args);
	}

}

//TODO 관리자 페이지
//TODO layout
//TODO controller logic service로 옮기기
//TODO redirection시 alert 구현
//TODO id대신 uuid를 사용할까? (보안...?)