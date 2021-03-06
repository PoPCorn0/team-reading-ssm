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
import com.snsoft.teamreading.pojo.Student;
import com.snsoft.teamreading.pojo.User;
import com.snsoft.teamreading.service.StudentService;
import com.snsoft.teamreading.utils.ModelAndViewUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller("TeacherStudentController")
@RequestMapping("/teacher")
public class StudentController {

    @Resource
    StudentService studentService;
    @Resource
    ModelAndView mv;

    /**
     * 添加一个学生
     *
     * @param id    学生id
     * @param pwd   学生密码
     * @param name  学生姓名
     * @param score 学生初始积分
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ModelAndView addStudent(@RequestParam("id") String id,
                                   @RequestParam("pwd") String pwd,
                                   @RequestParam("name") String name,
                                   @RequestParam("score") int score) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setPwd(pwd);
        student.setScore(score);

        try {
            return studentService.addStudent(student);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }

    /**
     * 移除一个学生
     *
     * @param user session中用户信息
     * @param id   学生id
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
    public ModelAndView deleteStudent(@SessionAttribute("user") User user,
                                      @RequestParam("id") String id) {
        try {
            return studentService.deleteStudent(user.getId(), id);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }
}
