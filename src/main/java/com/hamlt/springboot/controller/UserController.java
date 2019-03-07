package com.hamlt.springboot.controller;

import com.hamlt.springboot.bean.User;
import com.hamlt.springboot.exception.CustomException;
import com.hamlt.springboot.interceptor.LoginInterceptor;
import com.hamlt.springboot.scheduler.AsyncExecute;
import com.hamlt.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.Future;

/**
 * springboot 不建议使用jsp
 */
@RestController
@RequestMapping(value="/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AsyncExecute asyncExecute;

    //返回的是json对象（前提是@RestController而不是@Controller）
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // int i = 10 / 0;  // 制造异常
        User u = new User();
        u.setId(id);
        u.setUsername("zhs");
        u.setPassword("***");
        return u;
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    //返回到freemarker视图
    @RequestMapping(value="/view/{id}", method= RequestMethod.GET)
    public ModelAndView getUserToView(@PathVariable Long id, ModelAndView view) {
        //参数校验
        if (id == null || id == 0) {
            throw new CustomException(400, "id不能为空");
        }
        User u = new User();
        u.setId(id);
        u.setUsername("zhs");
        u.setPassword("***");
        view.addObject("user", u);
        view.setViewName("userEdit");
        return view;
    }

    //异步执行任务
    @RequestMapping(value="/task", method= RequestMethod.GET)
    public String task() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future<Boolean> a1 = asyncExecute.scheduled1();
        Future<Boolean> a2 = asyncExecute.scheduled1();
        while(!a1.isDone() || !a2.isDone()) {
          if(a1.isDone() && a2.isDone()) {
             break;
          }
        }
        long end = System.currentTimeMillis();
        long total = end - start;
        log.info("》》》 总时间：{}", total);
        return "总时间：" + total;
    }

    //测试事务
    @RequestMapping(value="/remove", method= RequestMethod.GET)
    public boolean remove()  {
        return userService.deleteUser();
    }



}
