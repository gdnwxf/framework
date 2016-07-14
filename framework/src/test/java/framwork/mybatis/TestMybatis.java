
package framwork.mybatis;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.abel533.sql.SqlMapper;
import com.wch.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/beans.xml")
public class TestMybatis {
	@Autowired
	ApplicationContext ctx;
	 
	@Autowired
	SqlMapper mapper;
	
//	@Autowired
//	UserService userService; 
	@Test
	public void testSqlHelper() throws Exception {
		/*List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("getUsers");
		for (Map<String, Object> map : selectList) {
			System.out.println(map);
			
		}*/
//		List<Map<String, Object>> selectList2 = mapper.selectList("select t.* from tuser t where t.userid= 1");
//		for (Map<String, Object> map : selectList2) {
//			System.out.println(map);
//			
//		}
		
		SqlSessionTemplate sessionTemplate =  (SqlSessionTemplate) ctx.getBean("sqlSessionTemplate");
		Map<String ,Object> data = new HashMap<String ,Object>();
		data.put("id", 1);
		data.put("username", "lisi");
		int update = sessionTemplate.update("nankang.dao.AgentMapper.updateByPrimaryKeySelective", data);
		System.out.println(update);
		System.out.println(sessionTemplate);
//		System.out.println(user);
		//获取CommonMapper，继而包装成EntityMapper
		 
	}
	
	@Test
	public void testSpringMethod() throws Exception {
		System.out.println(mapper.getClass());
		System.out.println(ctx.getBean("dataSource").getClass());
//		System.out.println(userService.getClass());
	}
	
}
