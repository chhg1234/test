package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BoardDAO;
import model.dao.MemberDAO;
import model.dao.ReplyDAO;
import model.dto.BoardDTO;
import model.dto.MemberDTO;
import model.dto.ReplyDTO;

public class DeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		HttpSession session = request.getSession();
		
		memberDTO.setMid((String)session.getAttribute("userInfo"));
		
		replyDTO.setCondition("DELMEM");
		replyDTO.setWriter((String)session.getAttribute("userInfo"));
		replyDAO.update(replyDTO);
		
		boardDTO.setCondition("DELMEM");
		boardDTO.setWriter((String)session.getAttribute("userInfo"));
		boardDAO.update(boardDTO);
		
		ActionForward forward = new ActionForward();
		if(memberDAO.delete(memberDTO)){
			//out.println("<script>alert('회원탈퇴 성공! 다음에 다시 이용해주세요.');location.href='controller.jsp?command=LOGOUT';</script>");
			request.setAttribute("msg", "회원탈퇴 성공! 다음에 다시 이용해주세요.");
			request.setAttribute("location", "logout.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		else{
			//out.println("<script>alert('회원탈퇴 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=MYPAGE';</script>");
			request.setAttribute("msg", "회원탈퇴 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "mypage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
