package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMid(request.getParameter("mid"));
		memberDTO.setMpw(request.getParameter("mpw"));
		memberDTO.setName(request.getParameter("name"));
		
		ActionForward forward = new ActionForward();
		if(memberDAO.insert(memberDTO)){
			request.setAttribute("msg", "회원가입 성공! 로그인해서 이용해주세요.");
			request.setAttribute("location", "mainPage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		else{
			request.setAttribute("msg", "회원가입 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "joinPage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
