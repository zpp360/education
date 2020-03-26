package cn.stylefeng.guns.modular.system.api;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.JwtTokenUtil;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.mapper.UserMapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *gunsApi
 */
@RequestMapping("/gunsApi")
@RestController
public class GunsApiController extends BaseController {
    @Autowired
    private UserMapper userMapper;

    /**
     * api登录接口，通过账号密码获取token
     * @return
     */
    @RequestMapping("/auth")
    public Object auth(@RequestParam(value = "username",required = true)String username,
                       @RequestParam(value = "password",required = true)String password){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password.toCharArray());
        User user = userMapper.getByAccount(username);
        String credentials = user.getPassword();
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(new ShiroUser(),credentials,credentialsSalt,"");

        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        boolean flag = md5CredentialsMatcher.doCredentialsMatch(usernamePasswordToken,simpleAuthenticationInfo);
        if(flag){
            return JwtTokenUtil.generateToken(String.valueOf(user.getUserId()));
        }else{
            return new ErrorResponseData(500,"账号密码错误");
        }

    }
}
