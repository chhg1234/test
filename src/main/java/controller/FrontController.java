package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String command = request.getRequestURI();
		command = command.substring(request.getContextPath().length());
		System.out.println("[로그] command : "+command);
		
		ActionForward forward = null;
		if(command.equals("/mainPage.do")){
			MainPageAction action = new MainPageAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/write.do")){
			WriteAction action = new WriteAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/deleteMember.do")){
			DeleteMemberAction action = new DeleteMemberAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/logout.do")) {
			LogoutAction action = new LogoutAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/mypage.do")) {
			MypageAction action = new MypageAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/writePage.do")) {
			WritePageAction action = new WritePageAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/boardPage.do")) {
			BoardPageAction action = new BoardPageAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/login.do")) {
			LoginAction action = new LoginAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/deleteBoard.do")) {
			DeleteBoardAction action = new DeleteBoardAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/join.do")) {
			JoinAction action = new JoinAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/updateName.do")) {
			UpdateNameAction action = new UpdateNameAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/updateReplyContent.do")) {
			UpdateReplyContentAction action = new UpdateReplyContentAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/deleteReply.do")) {
			DeleteReplyAction action = new DeleteReplyAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/insertReply.do")) {
			InsertReplyAction action = new InsertReplyAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/updateTitle.do")) {
			UpdateTitleAction action = new UpdateTitleAction();
			forward = action.execute(request, response);
		}
		else if(command.equals("/joinPage.do")) {
			JoinPageAction action = new JoinPageAction();
			forward = action.execute(request, response);
		}
		
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		}
		else {
			//pageContext.forward(forward.getPath());
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
		
	}

}
