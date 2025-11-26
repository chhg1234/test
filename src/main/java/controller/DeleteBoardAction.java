package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class DeleteBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		ActionForward forward = new ActionForward();
		if(boardDAO.delete(boardDTO)){
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
		}
		else{
			request.setAttribute("msg", "글 삭제 실패...");
			request.setAttribute("location", "mainPage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
