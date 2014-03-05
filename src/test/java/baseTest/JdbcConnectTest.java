/*package baseTest;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.framework.base.BaseTest;
import com.framework.parser.JdbcParser;
import com.system.dao.DemoDao;
import com.system.service.UserService;

public class JdbcConnectTest extends BaseTest{
	
	@Resource
	private UserService userService;
	
	@Resource
	private DemoDao demoDao;
	
	@Test
	public void testJdbc(){
		
		//List<Map<String, Object>> map = userService.excuteSQL("select * from ZKM_user ", null, null);
		//System.out.println(map);
		
		String sql = " SELECT * FROM ZKM_USER "+ 	
				" WHERE 1=1 ";
		
		Map u = (Map) demoDao.sqlQueryToModel(sql, null, new JdbcParser<Object>(){
			@Override
			public Object parse(Map<String, Object> src) {
				
				return src;
			}
		}, null).get(0);
		
	//System.out.println(u.get("name"));
	}
	
	
}

*/