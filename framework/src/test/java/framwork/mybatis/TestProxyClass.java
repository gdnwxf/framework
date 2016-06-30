
package framwork.mybatis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wch.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/beans.xml")
public class TestProxyClass {
  
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationContext atx ;
	@Test
	public void testClassName() throws Exception {
		
		System.out.println(userService.getClass());
		System.out.println(atx.getBean("userService").getClass());
		System.out.println(atx.getBean("userController").getClass());
	}
}
