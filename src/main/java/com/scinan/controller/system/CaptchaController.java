package com.scinan.controller.system;

import com.scinan.controller.BaseController;
import com.scinan.utils.CaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by alex on 2017/5/5.
 */
@Controller
public class CaptchaController extends BaseController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaUtils.generate(request, response);
    }
}
