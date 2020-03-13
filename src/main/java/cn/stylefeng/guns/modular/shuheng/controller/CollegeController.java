package cn.stylefeng.guns.modular.shuheng.controller;


import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.shuheng.entity.College;
import cn.stylefeng.guns.modular.shuheng.service.CollegeService;
import cn.stylefeng.guns.modular.shuheng.warpper.CollegeWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import java.util.Date;

import org.springframework.stereotype.Controller;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengpp
 * @since 2020-03-12
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {

   private String PREFIX = "/modular/shuheng/college/";

   @Autowired
   private CollegeService  collegeService;

   @RequestMapping("")
   public String index(){
      return PREFIX + "college.html";
   }

   @RequestMapping("/list")
   @ResponseBody
   public Object list(@RequestParam(value = "collegeName", required = false) String collegeName){
      Page<Map<String,Object>> page = collegeService.listCollege(collegeName);
      page = new CollegeWrapper(page).wrap();
      return LayuiPageFactory.createPageInfo(page);
   }

   @RequestMapping("/college_add")
   public String addView() {
      return PREFIX + "college_add.html";
   }

   @RequestMapping(value = "/add")
   @ResponseBody
   public ResponseData add(College college) {
      ShiroUser user = ShiroKit.getUser();
      if (college == null) {
         throw new RequestEmptyException();
      }
      this.collegeService.save(college);
      return SUCCESS_TIP;
   }


   @RequestMapping("/college_edit")
   public String editView(@RequestParam Long collegeId){
      if (ToolUtil.isEmpty(collegeId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      //用来记录日志
      College college = this.collegeService.getById(collegeId);
      LogObjectHolder.me().set(college);
      return PREFIX + "college_edit.html";
   }

   @RequestMapping(value = "/edit")
   @ResponseBody
   public ResponseData edit(College college){
      ShiroUser user = ShiroKit.getUser();
      if (college == null) {
         throw new RequestEmptyException();
      }
      this.collegeService.updateById(college);
      return SUCCESS_TIP;
   }

   @RequestMapping(value = "/getCollegeInfo")
   @ResponseBody
   public ResponseData getCollegeInfo(@RequestParam Long collegeId) {
      if (ToolUtil.isEmpty(collegeId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      College college = this.collegeService.getById(collegeId);
      return ResponseData.success(college);
   }


   /**
   * 删除
   * @param collegeId
   * @return
   */
   @RequestMapping(value = "/delete")
   @ResponseBody
   public ResponseData delete(@RequestParam Long collegeId) {
      if (ToolUtil.isEmpty(collegeId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      this.collegeService.removeById(collegeId);
      return SUCCESS_TIP;
   }
}
