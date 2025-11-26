package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ReplyDAO;
import model.dto.ReplyDTO;

public class InsertReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setContent(request.getParameter("content"));
		replyDTO.setWriter(request.getParameter("writer"));
		replyDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		ActionForward forward = new ActionForward();
		if(replyDAO.insert(replyDTO)){
			forward.setPath("boardPage.do?bid="+replyDTO.getBid());
			forward.setRedirect(true);
		}
		else{
			request.setAttribute("msg", "댓글 작성 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "boardPage.do?bid="+replyDTO.getBid());
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
