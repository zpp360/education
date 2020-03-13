package cn.stylefeng.guns.modular.shuheng.controller;


import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.shuheng.entity.Major;
import cn.stylefeng.guns.modular.shuheng.service.MajorService;
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
 * @since 2020-03-13
 */
@Controller
@RequestMapping("/major")
public class MajorController extends BaseController {

   private String PREFIX = "/modular/shuheng/major/";

   @Autowired
   private MajorService  majorService;

   @RequestMapping("")
   public String index(){
      return PREFIX + "major.html";
   }

   @RequestMapping("/list")
   @ResponseBody
   public Object list(@RequestParam(value = "majorName", required = false) String majorName){
      Page<Map<String,Object>> page = majorService.listMajor(majorName);
      return LayuiPageFactory.createPageInfo(page);
   }

   @RequestMapping("/major_add")
   public String addView() {
      return PREFIX + "major_add.html";
   }

   @RequestMapping(value = "/add")
   @ResponseBody
   public ResponseData add(Major major) {
      ShiroUser user = ShiroKit.getUser();
      if (major == null) {
         throw new RequestEmptyException();
      }
      this.majorService.save(major);
      return SUCCESS_TIP;
   }


   @RequestMapping("/major_edit")
   public String editView(@RequestParam Long majorId){
      if (ToolUtil.isEmpty(majorId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      //用来记录日志
      Major major = this.majorService.getById(majorId);
      LogObjectHolder.me().set(major);
      return PREFIX + "major_edit.html";
   }

   @RequestMapping(value = "/edit")
   @ResponseBody
   public ResponseData edit(Major major){
      ShiroUser user = ShiroKit.getUser();
      if (major == null) {
         throw new RequestEmptyException();
      }
      this.majorService.updateById(major);
      return SUCCESS_TIP;
   }

   @RequestMapping(value = "/getMajorInfo")
   @ResponseBody
   public ResponseData getMajorInfo(@RequestParam Long majorId) {
      if (ToolUtil.isEmpty(majorId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      Major major = this.majorService.getById(majorId);
      return ResponseData.success(major);
   }


   /**
   * 删除
   * @param majorId
   * @return
   */
   @RequestMapping(value = "/delete")
   @ResponseBody
   public ResponseData delete(@RequestParam Long majorId) {
      if (ToolUtil.isEmpty(majorId)) {
         throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
      }
      this.majorService.removeById(majorId);
      return SUCCESS_TIP;
   }
}
