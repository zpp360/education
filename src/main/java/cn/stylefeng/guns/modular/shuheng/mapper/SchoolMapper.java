package cn.stylefeng.guns.modular.shuheng.mapper;

import cn.stylefeng.guns.modular.shuheng.entity.School;
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
 * @since 2019-10-22
 */
public interface SchoolMapper extends BaseMapper<School> {
   Page<Map<String, Object>> selectSchool(@Param("page") Page page,@Param("schoolName") String schoolName,@Param("schoolId") Long schoolId);
}
