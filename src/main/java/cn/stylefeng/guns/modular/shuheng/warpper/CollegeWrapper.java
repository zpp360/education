package cn.stylefeng.guns.modular.shuheng.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.util.DecimalUtil;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class CollegeWrapper extends BaseControllerWrapper {

    public CollegeWrapper(Map<String, Object> single) {
        super(single);
    }

    public CollegeWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CollegeWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CollegeWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("schoolName", ConstantFactory.me().getSchoolName(DecimalUtil.getLong(map.get("schoolId"))));
    }
}
