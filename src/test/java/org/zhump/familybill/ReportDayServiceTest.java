package org.zhump.familybill;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhump.familybill.service.ReportDayService;

/**
 * Title:ReportDayServiceTest
 * Description:TODO 描述一下这个类是干嘛的
 *
 * @author zhump
 * @version 1.0.0
 * @date
 */
public class ReportDayServiceTest extends BaseServiceTest {

    @Autowired
    private ReportDayService reportDayService;

    @Test
    public void test1(){
        try {
            reportDayService.test();
        }catch (Exception e){

        }

    }
}
