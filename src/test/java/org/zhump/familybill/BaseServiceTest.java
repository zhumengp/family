package org.zhump.familybill;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Title:BaseServiceTest
 * Description:TODO 描述一下这个类是干嘛的
 *
 * @author zhump
 * @version 1.0.0
 * @date
 */
@SpringBootTest(classes= FamilyBillApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BaseServiceTest {
}
