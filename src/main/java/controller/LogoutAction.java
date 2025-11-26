package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); // session.removeAttribute("userInfo");
		
		//out.println("<script>alert('로그아웃 성공!');location.href='controller.jsp?command=MAINPAGE';</script>");
		request.setAttribute("msg", "로그아웃 성공!");
		request.setAttribute("location", "mainPage.do");

		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
