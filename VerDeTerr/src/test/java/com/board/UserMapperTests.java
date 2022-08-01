package com.board;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;
import com.board.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class UserMapperTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testOfInsert() {
		UserDTO params = new UserDTO();
		params.setId("hi_gorae");
		params.setPw("eom1234");
		params.setPwHint("아무거나");
		params.setEmail("hi_gorae@naver.com");
		params.setAge(30);
		params.setGender("male");
		params.setUserType("ISFP");
		params.setManagerYn(true);

		int result = userMapper.insertUser(params);
		System.out.println("결과는 " + result + "입니다.");
	}

	@Test
	public void testOfSelectDetail() {
		UserDTO user = userMapper.selectUserDetail("hi_gorae");
		try {
			// String userJson = new ObjectMapper().writeValueAsString(user);
			String userJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user);

			System.out.println("=========================");
			System.out.println(userJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void testOfUpdate() {
        UserDTO params = new UserDTO();
        params.setId("id11");
        params.setPw("pw11");
        params.setPwHint("confirm11");
        int result = userMapper.updateUser(params);
        if (result == 1) {
            UserDTO user = userMapper.selectUserDetail("id11");
            try {
                // String boardJson = new ObjectMapper().writeValueAsString(board);
                String userJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user);

                System.out.println("=========================");
                System.out.println(userJson);
                System.out.println("=========================");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }


	@Test
	public void testOfDelete() {
		int result = userMapper.deleteUser("hi_gorae");
		if (result == 1) {
			UserDTO user = userMapper.selectUserDetail("hi_gorae");
			try {
				// String userJson = new ObjectMapper().writeValueAsString(user);
				String userJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user);

				System.out.println("=========================");
				System.out.println(userJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testMultipleInsert() {
		for (int i = 3; i <= 50; i++) {
			UserDTO params = new UserDTO();
			params.setId("id" + i);
			params.setPw("pw" + i);
			params.setPwHint("confirm" + i);
			params.setEmail("id" + i + "@gmail.com");
			params.setAge(20 + i % 10);
			if (i % 4 == 0) {
				params.setGender("male");
				params.setUserType("ENFP");
			} else if (i % 4 == 1) {
				params.setGender("male");
				params.setUserType("ISTP");
			} else if (i % 4 == 2) {
				params.setGender("female");
				params.setUserType("ESTJ");
			} else {
				params.setGender("female");
				params.setUserType("ISFP");
			}
			userMapper.insertUser(params);
		}
	}

	@Test
	public void testOfLogin() {
		UserDTO result = userMapper.selectUserDetail("dhtmddms");
		System.out.println(result);
		if(result.getId()==null){
			System.out.println("가입되지 않은 아이디 입니다.");
		}else if(result.getPw().equals("1234")) {
			System.out.println("로그인 성공입니다.");
		}else {
			System.out.println("로그인 실패입니다.");
		}
	}

	@Test
	public void testSelectHistory() {
		int userHistoryCount = userMapper.selectUserHistoryCount("id11");
		if (userHistoryCount > 0) {
			List<SurveyOutputDTO> testList = userMapper.selectUserHistory("id11");
			if (CollectionUtils.isEmpty(testList) == false) {
				System.out.println("=========================");
				System.out.println("id11님의 테스트 내역");
				for (SurveyOutputDTO test : testList) {
					System.out.println(test.getTestdate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " : " + test.getUsertype());
				}
				System.out.println("=========================");
			}
		}

	}
}
