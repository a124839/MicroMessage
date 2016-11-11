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
		
			req.setCharacterEncoding("UTF-8");//���ñ���
			String command = req.getParameter("command");//��ҳ���õ�����
			String description =req.getParameter("description");
			//����ҳ���ϵĲ�ѯֵ
			req.setAttribute("command", command);
			req.setAttribute("description", description);
			//ʵ����service��
			ListService listService = new ListService();	
			//��ѯ��Ϣ�б�����ҳ��
			req.setAttribute("messageList", listService.queryList(command, description));						
			req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
}
