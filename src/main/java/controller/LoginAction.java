package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setCondition("LOGIN");
		memberDTO.setMid(request.getParameter("mid"));
		memberDTO.setMpw(request.getParameter("mpw"));
		MemberDTO data = memberDAO.selectOne(memberDTO);

		ActionForward forward = new ActionForward();
		if(data != null){
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", data.getMid());
			session.setAttribute("userNameInfo", data.getName());
			session.setAttribute("userMrole", data.getMrole());
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
		}
		else{
			request.setAttribute("msg", "로그인 실패...");
			request.setAttribute("location", "mainPage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}	
		return forward;
	}

}
