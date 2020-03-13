package cn.stylefeng.guns.modular.shuheng.mapper;

import cn.stylefeng.guns.modular.shuheng.entity.College;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengpp
 * @since 2020-03-12
 */
public interface CollegeMapper extends BaseMapper<College> {
   Page<Map<String, Object>> selectCollege(@Param("page") Page page, @Param("collegeName") String collegeName);
}
