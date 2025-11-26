package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dao.ReplyDAO;
import model.dto.BoardDTO;
import model.dto.ReplyDTO;

public class BoardPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		request.setAttribute("board", boardDAO.selectOne(boardDTO));
		request.setAttribute("replyDatas", replyDAO.selectAll(replyDTO));

		ActionForward forward = new ActionForward();
		forward.setPath("board.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
