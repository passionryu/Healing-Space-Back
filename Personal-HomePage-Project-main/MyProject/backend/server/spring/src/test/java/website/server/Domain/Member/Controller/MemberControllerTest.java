//package website.server.Domain.Member.Controller;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import website.server.Domain.Member.DTO.Request.MemberRequest;
//import website.server.Domain.Member.Service.MemberService;
//import website.server.Domain.Member.Service.MemberServiceImpl;
//
//import java.time.LocalDate;
//
//@WebMvcTest(MemberController.class)  // 컨트롤러를 테스트
//public class MemberControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;  // MockMvc를 통해 API 요청 테스트
//
////    @Mock
////    private MemberServiceImpl memberService;  // MemberService를 Mock 객체로 주입
//
//    @MockBean
//    private MemberService memberService; // MemberService를 목(mock) 객체로 주입
//
//
//    @InjectMocks
//    private MemberController memberController;  // 실제 테스트할 컨트롤러 객체
//
//    private MemberRequest memberRequest;
//
//    @BeforeEach
//    void setUp() {
//        // 빌더 패턴을 통해 객체 초기화
//        memberRequest = MemberRequest.builder()
//                .username("testUser")
//                .email("test@example.com")
//                .password("password123")
//                .birth(LocalDate.parse("1990-01-01"))
//                .build();
//    }
//
//
//    @Test
//    void testRegister() throws Exception {
//        // 테스트할 서비스 메서드의 동작을 mock 설정
//        Long createdId = 1L;
//        when(memberService.register(any(MemberRequest.class))).thenReturn(createdId);
//
//        // 회원가입 API 호출 테스트
//        MvcResult result = mockMvc.perform(post("/member/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(memberRequest))) // 요청 본문에 회원가입 정보 넣기
//                .andExpect(status().isOk())  // HTTP 상태 코드 200 OK 확인
//                .andExpect(content().string("회원 가입 성공! userID는" + createdId + "입니다."))  // 응답 본문 검증
//                .andReturn();
//
//        // 응답 본문을 출력해 확인
//        System.out.println(result.getResponse().getContentAsString());
//    }
//}
