package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class LoginActionTest {
    @Test
    @DisplayName("로그인 성공시 mainPage.do로 redirect")
    void testLoginSuccess() {
        LoginAction action = new LoginAction();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("mid")).thenReturn("user1");
        when(request.getParameter("mpw")).thenReturn("pw1");
        when(request.getSession()).thenReturn(session);
        MemberDTO fakeUser = new MemberDTO();
        fakeUser.setMid("user1");
        fakeUser.setName("홍길동");
        fakeUser.setMrole("USER");
        try (MockedConstruction<MemberDAO> mocked = mockConstruction(MemberDAO.class,
            (mock, context) -> when(mock.selectOne(any(MemberDTO.class))).thenReturn(fakeUser))) {
            ActionForward forward = action.execute(request, response);
            verify(session).setAttribute("userInfo", "user1");
            verify(session).setAttribute("userNameInfo", "홍길동");
            verify(session).setAttribute("userMrole", "USER");
            assertEquals("mainPage.do", forward.getPath());
            assertTrue(forward.isRedirect());
        }
    }

    @Test
    @DisplayName("로그인 실패시 message.jsp로 forward")
    void testLoginFail() {
        LoginAction action = new LoginAction();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("mid")).thenReturn("user1");
        when(request.getParameter("mpw")).thenReturn("wrong");
        try (MockedConstruction<MemberDAO> mocked = mockConstruction(MemberDAO.class,
            (mock, context) -> when(mock.selectOne(any(MemberDTO.class))).thenReturn(null))) {
            ActionForward forward = action.execute(request, response);
            verify(request).setAttribute(eq("msg"), anyString());
            verify(request).setAttribute(eq("location"), eq("mainPage.do"));
            assertEquals("message.jsp", forward.getPath());
            assertFalse(forward.isRedirect());
        }
    }
}
