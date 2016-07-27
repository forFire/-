
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Customers;
import com.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-application.xml" }) // 加载配置文件
public class DemoTest {
	
	@Test
	public void test1() {
		System.out.println("12313");
		
	}
	
	@Resource
	CustomerService customerService;
	
	@Test
	public void customerTest(){
		Customers customers = new Customers();
		customers.setId(1);
		customers.setName("test");
		customerService.update(customers);
		
	}
	
	
	
}
