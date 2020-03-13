package cn.stylefeng.guns.modular.shuheng.service;

import cn.stylefeng.guns.modular.shuheng.entity.Major;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.stylefeng.guns.modular.shuheng.mapper.MajorMapper;
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
 * @since 2020-03-13
 */
@Service
public class MajorService extends ServiceImpl<MajorMapper,Major> {
   public Page<Map<String,Object>> listMajor(String majorName) {
      Page page = LayuiPageFactory.defaultPage();
      return this.baseMapper.selectMajor(page,majorName);
   }
}
