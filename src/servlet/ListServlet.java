package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ListService;

/**
 * @author 570
 *
 */
public class ListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.setCharacterEncoding("UTF-8");//设置编码
			String command = req.getParameter("command");//从页面拿到参数
			String description =req.getParameter("description");
			//保存页面上的查询值
			req.setAttribute("command", command);
			req.setAttribute("description", description);
			//实例化service层
			ListService listService = new ListService();	
			//查询消息列表并传给页面
			req.setAttribute("messageList", listService.queryList(command, description));						
			req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
