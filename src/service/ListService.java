package service;

import java.util.List;

import bean.Message;
import dao.ListDao;

public class ListService {
	//service����Dao�Ĳ�ѯ����
	public List<Message> queryList(String command,String description) {
		ListDao listDao = new ListDao();
		return listDao.queryList(command, description);
	}
}
