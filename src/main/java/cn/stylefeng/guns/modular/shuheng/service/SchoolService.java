package cn.stylefeng.guns.modular.shuheng.service;

import cn.stylefeng.guns.modular.shuheng.entity.School;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.stylefeng.guns.modular.shuheng.mapper.SchoolMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import java.util.Map;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengpp
 * @since 2019-10-22
 */
@Service
public class SchoolService extends ServiceImpl<SchoolMapper,School> {
   public Page<Map<String,Object>> listSchool(String schoolName,Long schoolId) {
      Page page = LayuiPageFactory.defaultPage();
      return this.baseMapper.selectSchool(page,schoolName,schoolId);
   }
}
