package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mockConstruction;
import org.mockito.MockedConstruction;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

class MainPageActionTest {

    private MainPageAction mainPageAction;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        mainPageAction = new MainPageAction();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    @DisplayName("메인 페이지 이동 테스트")
    void testExecute() {
        // given
        when(request.getParameter("condition")).thenReturn("ALL");
        when(request.getParameter("keyword")).thenReturn("");

        try (MockedConstruction<BoardDAO> mocked = mockConstruction(BoardDAO.class,
                (mock, context) -> {
                    when(mock.selectAll(any(BoardDTO.class))).thenReturn(new ArrayList<BoardDTO>());
                })) {
            // when
            ActionForward forward = mainPageAction.execute(request, response);

            // then
            verify(request).setAttribute(eq("datas"), any(ArrayList.class));
            assertEquals("main.jsp", forward.getPath());
            assertFalse(forward.isRedirect());
        }
    }
}
