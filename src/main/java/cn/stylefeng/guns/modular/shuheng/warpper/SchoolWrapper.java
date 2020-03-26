package cn.stylefeng.guns.modular.shuheng.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class SchoolWrapper extends BaseControllerWrapper{
    public SchoolWrapper(Map<String, Object> single) {
        super(single);
    }

    public SchoolWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public SchoolWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public SchoolWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("schoolYearName", map.get("schoolYear")+"年");
    }
}
