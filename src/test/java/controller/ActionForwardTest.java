package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActionForwardTest {

    @Test
    @DisplayName("Getter and setter for path and redirect")
    void testAccessors() {
        ActionForward forward = new ActionForward();
        forward.setPath("path.jsp");
        forward.setRedirect(false);

        assertEquals("path.jsp", forward.getPath());
        assertFalse(forward.isRedirect());

        forward.setRedirect(true);
        assertTrue(forward.isRedirect());
    }
}
