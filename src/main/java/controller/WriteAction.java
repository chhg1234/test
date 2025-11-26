package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class WriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setWriter(request.getParameter("writer"));
		boardDTO.setContent(request.getParameter("content"));
		
		ActionForward forward = new ActionForward();
		if(boardDAO.insert(boardDTO)){
			//response.sendRedirect("controller.jsp?command=MAINPAGE");
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
		}
		else{
			//out.println("<script>alert('글 작성 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=WRITEPAGE';</script>");
			request.setAttribute("msg", "글 작성 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "writePage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}	
		return forward;

	}

}
