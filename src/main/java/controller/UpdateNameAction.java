package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class UpdateNameAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setMid(request.getParameter("mid"));
		
		if(memberDAO.update(memberDTO)){
			request.setAttribute("msg", "이름변경 성공! 로그인해서 이용해주세요.");
			request.setAttribute("location", "logout.do");
		}
		else{
			request.setAttribute("msg", "이름변경 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "mypage.do");
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
