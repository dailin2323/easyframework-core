/*package baseTest;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.base.BaseContext;
import com.framework.base.BaseTest;
import com.wcm.model.User;
import com.wcm.service.UserService;

public class HibernateConnectTest extends BaseTest{// extends BaseTest
	
	@Autowired
	private UserService userService;
	@Resource
	private BaseContext baseContext;
	
	@Test
	public void test(){
		//JSON.toJSONString(object, prettyFormat)
		//userService.test();
		
		//List<User> u = new LinkedList<User>();
		//u.getClass().getDeclaredConstructors()[0].;
		//p =(ParameterizedType) p.getActualTypeArguments()[0];
		//System.out.println(p.getRawType());
		User user = new User();
		user.setName("bbb3");
	//	user.setId(3392L);
		user.setAge(9);
		user.setBirthday(new Date());
		user.setNickName("bbb3");
		user.setRealName("bbb3");
		user.setPassword("bbb3");
		user.setSex((byte)0);
		
		userService.save(user);
	}
}


*/