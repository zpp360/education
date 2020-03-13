package cn.stylefeng.guns.modular.shuheng.mapper;

import cn.stylefeng.guns.modular.shuheng.entity.Major;
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
 * @since 2020-03-13
 */
public interface MajorMapper extends BaseMapper<Major> {
   Page<Map<String, Object>> selectMajor(@Param("page") Page page, @Param("majorName") String majorName);
}
