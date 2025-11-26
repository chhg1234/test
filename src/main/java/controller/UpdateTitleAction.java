package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class UpdateTitleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCondition("TITLE");
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		ActionForward forward = new ActionForward();
		if(boardDAO.update(boardDTO)){
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
		}
		else{
			request.setAttribute("msg", "제목 변경 실패...");
			request.setAttribute("location", "mainPage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}

}
