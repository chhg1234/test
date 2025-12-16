package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WritePageActionTest {

    @Test
    @DisplayName("Write page action returns write.jsp and redirect true")
    void testExecute() {
        WritePageAction action = new WritePageAction();
        ActionForward forward = action.execute((HttpServletRequest) null, (HttpServletResponse) null);

        assertEquals("write.jsp", forward.getPath());
        assertTrue(forward.isRedirect());
    }
}
