package baseTest;

import org.junit.Test;

public class BaseDaoTest {
	
	@Test
	public void test(){
		
	}
	
	/*@Resource
	private UserService userService;
	
	@Test
	public void add(){
		User user = new User();
		user.setName("1");
		userService.save(user);
		
		User user2 = new User();
		user2.setName("2");
		userService.save(user2);
		
		User user3 = new User();
		user3.setName("3");
		userService.save(user3);
		
		User user4 = new User();
		user4.setName("4");
		userService.save(user4);
	}
	
	@Test
	public void delete(){
		User user = (User) userService.getById(1L, null, null);
		userService.delete(user);
	}
	
	@Test
	public void update(){
		User user = (User) userService.getById(6L, null, null);
		user.setName("66");
		userService.update(user);
	}
	
	@Test
	public void getById(){
		UserBo user = (UserBo)userService.getById(6L, UserBo.class, null);
		logger.info(user.getName());
		
		UserBo user2 = (UserBo)userService.getById(6L, null, new ObjectAdapter(){
			@Override
			public Object adapt(Object src) {
				User u = (User)src;
				UserBo user = new UserBo(); 
				user.setName(u.getName());
				return user;
			}});
		logger.info(user2.getName());
	}

	@Test
	public void getByIds(){
		List ids = new LinkedList();
		ids.add(3L);
		ids.add(4L);
		List<UserBo> users = (List<UserBo>)userService.getByIds(ids, UserBo.class, null);
		for(UserBo u:users){
			logger.info(u.getName());
		}
		
		users = (List<UserBo>)userService.getByIds(ids, null, new ObjectAdapter(){
			@Override
			public Object adapt(Object src) {
				User u = (User)src;
				UserBo user = new UserBo(); 
				user.setName(u.getName());
				return user;
			}});
		for(UserBo u:users){
			logger.info(u.getName());
		}
	}

	@Test
	public void getByName(){
		User user = (User)userService.getByName("zhangsan", null, null);
		logger.info(user.getName());
	}
	
	@Test
	public void getByNames(){
		List<String> names = new LinkedList();
		names.add("zhangsan");
		names.add("lisi");
		names.add("aa");
		List<UserBo> users = (List<UserBo>)userService.getByNames(names, UserBo.class, null);
		for(UserBo u:users){
			logger.info(u.getName());
		}
	}
	
	@Test
	public void findAllExcept(){
		List ids = new LinkedList();
		ids.add(3L);
		List<UserBo> users = (List<UserBo>)userService.findAllExcept(ids, UserBo.class, null);
		for(UserBo u:users){
			logger.info(u.getName());
		}
		
		users = (List<UserBo>)userService.findAllExcept(ids, null, new ObjectAdapter(){
			@Override
			public Object adapt(Object src) {
				User u = (User)src;
				UserBo user = new UserBo(); 
				user.setName(u.getName());
				return user;
			}});
		for(UserBo u:users){
			logger.info(u.getName());
		}	
	}
	
	@Test
	public void find(){
		PageQueryParam pageQueryParam = new PageQueryParam();
		List<User> users = userService.find(pageQueryParam, null, null, null, null);
		for(User user:users){
			logger.info(user.getName());
		}
		
		List<UserBo> users2 = userService.find(pageQueryParam, null, null, UserBo.class, null);
		for(UserBo user:users2){
			logger.info(user.getName());
		}
		
		List<UserBo> users3 = userService.find(pageQueryParam, null, null, UserBo.class, null);
		for(UserBo user:users3){
			logger.info(user.getName());
		}
		
	}
	
	@Test
	public void findAll(){
		List<User> users1 = userService.findAll(null, null);
		for(User user:users1){
			logger.info(user.getName());
		}
		
		List<UserBo> users2 = userService.findAll(UserBo.class, null);
		for(UserBo user:users2){
			logger.info(user.getName());
		}
		
		List<UserBo> users3 = userService.findAll(null, new ObjectAdapter() {
			@Override
			public Object adapt(Object src) {
				User user = (User)src;
				UserBo ub = new UserBo();
				ub.setName(user.getName());
				return ub;
			}
		});
		for(UserBo user:users3){
			logger.info(user.getName());
		}
	}
	
	@Test
	public void getCount(){
		logger.info(userService.getCount(null));
	}
	
	@Test
	public void pageQuery(){
		//userService.pageQuery(pageQueryParam, criterions, orders, targetClass, objectAdapter);
	}
	
	@Test
	public void getDataGrid(){
		
	}
	
	@Test
	public void getTree(){
		
	}
	
	@Test
	public void getTreeGrid(){
		
	}
	
	@Test
	public void getUniqueResult(){
		
	}

	@Test
	public void getDictById(){
		
	}
	
	@Test
	public void getDictByType(){
		
	}
	
	@Test
	public void getDictByCode(){
		
	}
	
	///---------------sql 测试--------------------
	@Test
	public void sqlQuery(){
		List<Map> users = userService.sqlQuery("SELECT * FROM ZKM_USER ", null, null, null);
		for(Map m:users){
			logger.info(m.get("name"));
		}
		
		List<User> users2 = userService.sqlQuery("SELECT * FROM ZKM_USER ", null, User.class, null);
		for(User m:users2){
			logger.info(m.getName());
		}
		
		List<User> users3 = userService.sqlQuery("SELECT * FROM ZKM_USER ", null, null, new MapAdapter(){
			@Override
			public Object adapt(Map<String, Object> src) {
				User user = new User();
				user.setName((String)src.get("name"));
				return user;
			}});
		for(User m:users3){
			logger.info(m.getName());
		}
	}
	
	@Test
	public void sqlGetUniqueResult(){
		
	}
	
	@Test
	public void sqlExecute(){
		
	}
	
	@Test
	public void sqlGetCount(){
		
	}
	
	@Test
	public void sqlGetDictById(){
		
	}
	
	@Test
	public void sqlGetDictByType(){
		
	}
	
	@Test
	public void sqlGetDictByCode(){
		
	}*/
	

}