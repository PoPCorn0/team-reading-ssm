/*
 * @copyright ：神农大学生软件创新中心 版权所有 © 2019
 *
 * @author 16级信息与计算科学潘鹏程
 *
 * @version
 *
 * @date 2019.08.22
 *
 * @Description
 */

package com.snsoft.teamreading.controller.teacherController;

import com.snsoft.teamreading.enums.Code;
import com.snsoft.teamreading.pojo.User;
import com.snsoft.teamreading.service.AssistantService;
import com.snsoft.teamreading.utils.ModelAndViewUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller("TeacherAssistantController")
@RequestMapping("teacher")
public class AssistantController {
    @Resource
    AssistantService assistantService;
    @Resource
    ModelAndView mv;
    /**
     * 设置导师助手，如果传入的id已经是导师助手则取消
     *
     * @param user      session中用户信息
     * @param studentId 学生id
     * @param teamId    团队id
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/setAssistant", method = RequestMethod.POST)
    public ModelAndView setAssistant(@SessionAttribute("user") User user,
                                     @RequestParam("studentId") String studentId,
                                     @RequestParam("teamId") String teamId) {
        try {
            return assistantService.setAssistant(user.getId(), studentId, teamId);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }
}
