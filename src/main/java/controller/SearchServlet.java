package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] GET 요청");

		BoardDAO bDAO = new BoardDAO();
		BoardDTO bDTO = new BoardDTO();
		bDTO.setCondition(request.getParameter("condition"));
		bDTO.setKeyword(request.getParameter("keyword"));
		ArrayList<BoardDTO> datas = bDAO.selectAll(bDTO);
		System.out.println(datas);

		String json = new Gson().toJson(datas);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] POST 요청");
	}

}
