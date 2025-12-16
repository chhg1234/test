package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedConstruction;

import java.io.IOException;

class FrontControllerTest {
    private FrontController controller;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        controller = new FrontController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    @DisplayName("mainPage.do는 forward로 main.jsp로 이동")
    void testMainPageForward() throws Exception {
        when(request.getRequestURI()).thenReturn("/context/mainPage.do");
        when(request.getContextPath()).thenReturn("/context");
        when(request.getRequestDispatcher("main.jsp")).thenReturn(dispatcher);
        // MainPageAction의 execute가 main.jsp, redirect=false를 반환하도록 mock
        try (MockedConstruction<MainPageAction> mocked = mockConstruction(MainPageAction.class,
            (mock, context) -> when(mock.execute(any(), any())).thenReturn(new ActionForward() {{ setPath("main.jsp"); setRedirect(false); }}))) {
            controller.doGet(request, response);
            verify(dispatcher).forward(request, response);
        }
    }

    @Test
    @DisplayName("writePage.do는 redirect로 write.jsp로 이동")
    void testWritePageRedirect() throws Exception {
        when(request.getRequestURI()).thenReturn("/context/writePage.do");
        when(request.getContextPath()).thenReturn("/context");
        // WritePageAction의 execute가 write.jsp, redirect=true를 반환하도록 mock
        try (MockedConstruction<WritePageAction> mocked = mockConstruction(WritePageAction.class,
            (mock, context) -> when(mock.execute(any(), any())).thenReturn(new ActionForward() {{ setPath("write.jsp"); setRedirect(true); }}))) {
            controller.doGet(request, response);
            verify(response).sendRedirect("write.jsp");
        }
    }
}
