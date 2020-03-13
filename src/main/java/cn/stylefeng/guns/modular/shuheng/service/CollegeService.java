package cn.stylefeng.guns.modular.shuheng.service;

import cn.stylefeng.guns.modular.shuheng.entity.College;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.stylefeng.guns.modular.shuheng.mapper.CollegeMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengpp
 * @since 2020-03-12
 */
@Service
public class CollegeService extends ServiceImpl<CollegeMapper,College> {
   public Page<Map<String,Object>> listCollege(String collegeName) {
      Page page = LayuiPageFactory.defaultPage();
      return this.baseMapper.selectCollege(page,collegeName);
   }
}
