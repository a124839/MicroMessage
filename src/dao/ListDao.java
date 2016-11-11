package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Message;

/**
 * @author 570
 * 和list表相关的数据库操作
 * 
 *
 */
public class ListDao {
	//查询列表
	public List<Message> queryList(String command,String description) {
		List<Message> messageList = new ArrayList<Message>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=true","root","root");			
			//where 1=1 用于语句拼接。。不明所以
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from imooc_mybatis_list where 1=1"); 			
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			//多条件查询时，利用List做缓冲，保存查询参数，然后再循环赋值
			List<String> paramerList = new ArrayList<String>();
			//语句拼接如果查询的指令非空且非空字符串则执行语句拼接查询
			if (command!=null&&!"".equals(command.trim())) {
				sql.append(" and COMMAND=? ");
				paramerList.add(command);
			}			
			if (description!=null&&!"".equals(description.trim())) {
				sql.append("and DESCRIPTION like '%' ? '%' ");
				paramerList.add(description);
			}
			System.out.println(sql);
			//把页面上的语句拿给要送到数据库查询的ps中
			for (int i = 0; i < paramerList.size(); i++) {
				System.out.println(paramerList.get(i));
				ps.setString(i+1, paramerList.get(i));
			}
			System.out.println("------------------"+paramerList.toString()+"------------------");
			System.out.println("=========="+ps+"==========");
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				Message message = new Message();
				messageList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
}
