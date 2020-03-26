package cn.stylefeng.guns.modular.shuheng.controller;

import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.HtmlUtil;
import cn.stylefeng.guns.modular.shuheng.entity.School;
import cn.stylefeng.guns.modular.shuheng.service.SchoolService;
import cn.stylefeng.guns.modular.shuheng.warpper.SchoolWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  分校 前端控制器
 * </p>
 *
 * @author zhengpp
 * @since 2019-10-22
 */
@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController {

   private String PREFIX = "/modular/shuheng/school/";

   @Autowired
   private SchoolService schoolService;

   @RequestMapping("")
   public String index(){
      return PREFIX + "school.html";
   }

   @RequestMapping("/list")
   @ResponseBody
   public Object list(@RequestParam(value = "schoolName", required = false) String schoolName){
      Page<Map<String,Object>> page = new Page();
      if(ShiroKit.isAdmin()){
         page = schoolService.listSchool(schoolName,null);
      }else if(ShiroKit.isGeneral() && ShiroKit.isSchoolAdmin()){
         ShiroUser shiroUser = ShiroKit.getUser();
         Long schoolId = shiroUser.getSchoolId();
         page = schoolService.listSchool(schoolName,schoolId);
      }
      page = new SchoolWrapper(page).wrap();
      return LayuiPageFactory.createPageInfo(page);
   }

   @RequestMapping("/school_add")
   public String addView() {
      return PREFIX + "school_add.html";
   }

   @RequestMapping(value = "/add")
   @ResponseBody
   public ResponseData add(School school) {
      ShiroUser user = ShiroKit.getUser();
      if (ToolUtil.isOneEmpty(school,school.getSchoolName())) {
         throw new RequestEmptyException();
      }
      school.setCreateUser(user.getId());
      school.setCreateTime(new Date());
      school.setUpdateUser(user.getId());
      school.setUpdateTime(new Date());
      this.schoolService.save(school);
      return SUCCESS_TIP;
   }


   @RequestMapping("/school_edit")
   public String editView(@RequestParam Long schoolId){
      if (ToolUtil.isEmpty(schoolId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      //用来记录日志
      School school = this.schoolService.getById(schoolId);
      LogObjectHolder.me().set(school);
      return PREFIX + "school_edit.html";
   }

   @RequestMapping(value = "/edit")
   @ResponseBody
   public ResponseData edit(School school){
      ShiroUser user = ShiroKit.getUser();
      if (school == null) {
         throw new RequestEmptyException();
      }
      school.setUpdateUser(user.getId());
      school.setUpdateTime(new Date());
      this.schoolService.updateById(school);
      return SUCCESS_TIP;
   }

   @RequestMapping(value = "/getSchoolInfo")
   @ResponseBody
   public ResponseData getSchoolInfo(@RequestParam Long schoolId) {
      if (ToolUtil.isEmpty(schoolId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      School school = this.schoolService.getById(schoolId);
      return ResponseData.success(school);
   }


   /**
   * 删除
   * @param schoolId
   * @return
   */
   @RequestMapping(value = "/delete")
   @ResponseBody
   public ResponseData delete(@RequestParam Long schoolId) {
      if (ToolUtil.isEmpty(schoolId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      this.schoolService.removeById(schoolId);
      return SUCCESS_TIP;
   }

   /**
    * 学校下拉框
    * @return
    */
   @RequestMapping(value = "/selectSchool")
   @ResponseBody
   public Object selectSchool(){
      QueryWrapper<School> queryWrapper = new QueryWrapper<>();
      queryWrapper.select("school_id","school_name");
      List<Map<String,Object>> list = schoolService.listMaps(queryWrapper);
      String options = HtmlUtil.listMap2HtmlOptions(list,"school_id","school_name");
      return options;
   }

}
