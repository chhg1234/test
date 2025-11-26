package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class MainPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCondition(request.getParameter("condition"));
		boardDTO.setKeyword(request.getParameter("keyword"));
		if(boardDTO.getCondition() == null
				|| boardDTO.getCondition().isBlank()
				|| boardDTO.getCondition().isEmpty()){
			boardDTO.setCondition("ALL");
		}
		request.setAttribute("datas", boardDAO.selectAll(boardDTO));
		
		// pageContext.forward("main.jsp");
		ActionForward forward = new ActionForward();
		forward.setPath("main.jsp"); // 어디로 갈지
		forward.setRedirect(false); // 어떻게 갈지
		return forward;
		
	}

}
