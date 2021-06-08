package org.zhump.familybill.contants;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:PerssionConfig
 * Description:权限配置
 *
 * @author zhump
 * @version 1.0.0
 * @date 2021/06/08 0:06
 */
public class PerssionConfig {

    static final List<String> perssionList = new ArrayList<>();

    static {
        perssionList.add("category:add");
        perssionList.add("category:view");
        perssionList.add("category:update");
        perssionList.add("category:delete");
        perssionList.add("user:view");
        perssionList.add("user:add");
        perssionList.add("user:update");
        perssionList.add("user:delete");
    }

    public static List<String> getPerssionList() {
        return perssionList;
    }
}
