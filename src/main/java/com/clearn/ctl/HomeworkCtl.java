package com.clearn.ctl;

import com.clearn.bean.Comment;
import com.clearn.bean.Homework;
import com.clearn.bean.HomeworkAndUser;
import com.clearn.bean.HomeworkDto;
import com.clearn.dao.HomeworkRepository;
import com.clearn.services.IHomeworkService;
import com.clearn.services.IHomeworkUserService;
import com.clearn.utils.Constant;
import com.clearn.utils.UserHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author Administrator
 * @Date 2018.17:04
 */
@RequestMapping("/homework")
@Controller
public class HomeworkCtl {

    @Autowired
    private IHomeworkService homeworkService;

    @Autowired
    private IHomeworkUserService homeworkUserService;

    /**
     * 跳转到作业管理列表；
     * @return
     */
    @RequestMapping("/toHomeworkMgr")
    public ModelAndView toHomeworkMgr(){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("homeworkMgr",map);
    }

    /**
     * 跳转到作业列表； - 供老师查看学生答复的情况；
     * @return
     */
    @RequestMapping("/toHomeworkList")
    public ModelAndView toHomeworkList(){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("homeworkList",map);
    }

    @RequestMapping("/homeworkList")
    @ResponseBody
    public Map<String,Object> homeworkList(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        Long userId = UserHelp.getUserId(request);
        List<HomeworkAndUser> homeworkAndUsers = homeworkUserService.findHomeworkAndUserByTeacherId(userId);
        List<HomeworkDto> list = new ArrayList<>();
        Homework homework = null;
        HomeworkDto homeworkDto = null;
        String homeworkType = null;
        for(HomeworkAndUser homeworkAndUser:homeworkAndUsers){
            homework = homeworkService.getHomeworkById(homeworkAndUser.getHomeworkId());
            homeworkDto = new HomeworkDto();
            homeworkDto.setHomeworkId(homework.getId());
            homeworkType =homework.getHomeworkType();
            if(homeworkType.equals(Constant.HOMEWORK_TYPE_CHOICE)){
                homeworkDto.setHomeworkType("选择");
            }else if(homeworkType.equals(Constant.HOMEWORK_TYPE_JUDGE)){
                homeworkDto.setHomeworkType("判断");
            }else if(homeworkType.equals(Constant.HOMEWORK_TYPE_SHORTANSWER)){
                homeworkDto.setHomeworkType("简答");
            }
            homeworkDto.setSubject(homework.getSubject());
            homeworkDto.setTitle(homework.getTitle());
            homeworkDto.setHomeworkAndUserId(homeworkAndUser.getId());
            homeworkDto.setStudentName(homeworkAndUser.getStudentName());
            list.add(homeworkDto);
        }
        map.put("list",list);
        return map;
    }


    /**
     * 根据作业ID删除作业；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteHomework")
    @ResponseBody
    public String deleteHomework(HttpServletRequest request,HttpServletResponse response){
        String homeworkId = request.getParameter("homeworkId");
        homeworkService.deleteHomeworkById(Long.parseLong(homeworkId));
        return "success";
    }

    /**
     * 修改作业状态：已发布；
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/pubHomework")
    public String pubHomework(HttpServletRequest request,HttpServletResponse response){
        String homeworkId = request.getParameter("homeworkId");
        Homework homework = homeworkService.getHomeworkById(Long.parseLong(homeworkId));
        homework.setStatus(Constant.HOMEWORK_STATUS_PUB);
        homeworkService.updateHomework(homework);
        return "success";
    }

    /**
     * 答复作业；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/answerHomework")
    @ResponseBody
    public String answerHomework(HttpServletRequest request,HttpServletResponse response){
        String homeworkId = request.getParameter("homeworkId");
        String answer = request.getParameter("answer");

        Homework homework = homeworkService.getHomeworkById(Long.parseLong(homeworkId));
        HomeworkAndUser homeworkAndUser = new HomeworkAndUser();
        homeworkAndUser.setHomeworkId(Long.parseLong(homeworkId));
        homeworkAndUser.setTeacherName(homework.getTeacherName());
        homeworkAndUser.setAnswer(answer);
        homeworkAndUser.setStudentId(UserHelp.getUserId(request));
        homeworkAndUser.setStudentName(UserHelp.getUserName(request));
        homeworkAndUser.setTeacherId(homework.getTeacherId());
        homeworkAndUser.setSubjectName(homework.getSubject());
        homeworkUserService.saveHomeworkAndUser(homeworkAndUser);
        return "success";
    }

    /**
     * 跳转到新建作业界面
     * @return
     */
    @RequestMapping("/toNewHomework")
    public ModelAndView toNewHomework(){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("/newHomework",map);
    }

    @RequestMapping("/toCheckHomework")
    public ModelAndView toCheckHomework(HttpServletRequest request,HttpServletResponse response){
        String homeworkAndUserId = request.getParameter("homeworkAndUserId");
        HomeworkAndUser homeworkAndUser = homeworkUserService.getHomeworkAndUserById(Long.parseLong(homeworkAndUserId));
        Long homeworkId = homeworkAndUser.getHomeworkId();

        Map<String,Object> map = new HashMap<>();
        Homework homework = homeworkService.getHomeworkById(homeworkId);
        map.put("homework",homework);
        String homeworkType = homework.getHomeworkType();
        // 如果是选择题，那么需要对选项进行处理；
        if(homeworkType.equals(Constant.HOMEWORK_TYPE_CHOICE)){
            String content = homework.getContent();
            String[] contentArr = content.split("#");
            List<String> contentList = Arrays.asList(contentArr);
            map.put("contentList",contentList);
        }
        String answer = "";
        if(homeworkAndUser!=null){
            answer = homeworkAndUser.getAnswer();
        }

        String teacherComment = "";
        String score = "";
        if(homeworkAndUser!=null){
            teacherComment = homeworkAndUser.getTeacherComment();
            score = homeworkAndUser.getScore();
        }

        map.put("answer",answer);
        map.put("teacherComment",teacherComment);
        map.put("score",score);
        map.put("flag","ls");
        map.put("homeworkAndUserId",homeworkAndUserId);
        return new ModelAndView("/answerHomework",map);


    }

    /**
     * 审批作业；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/checkHomework")
    @ResponseBody
    public String checkHomework(HttpServletRequest request,HttpServletResponse response){
        String homeworkAndUserId = request.getParameter("homeworkAndUserId");
        String teacherComment =  request.getParameter("teacherComment");
        String score =  request.getParameter("score");
        HomeworkAndUser homeworkAndUser = homeworkUserService.getHomeworkAndUserById(Long.parseLong(homeworkAndUserId));
        homeworkAndUser.setTeacherComment(teacherComment);
        homeworkAndUser.setScore(score);
        homeworkUserService.saveHomeworkAndUser(homeworkAndUser);
        return "success";
    }


    /**
     * 跳转到回答问题界面；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toAnswerHomework")
    public ModelAndView toAnswerHomework(HttpServletRequest request,HttpServletResponse response){
        String homeworkId = request.getParameter("homeworkId");
        Map<String,Object> map = new HashMap<>();
        Homework homework = homeworkService.getHomeworkById(Long.parseLong(homeworkId));
        map.put("homework",homework);
        String homeworkType = homework.getHomeworkType();
        // 如果是选择题，那么需要对选项进行处理；
        if(homeworkType.equals(Constant.HOMEWORK_TYPE_CHOICE)){
            String content = homework.getContent();
            String[] contentArr = content.split("#");
            List<String> contentList = Arrays.asList(contentArr);
            map.put("contentList",contentList);
        }
        HomeworkAndUser homeworkAndUser = homeworkUserService.getHomeworkAndUserByUserIdAndHomeworkId(UserHelp.getUserId(request),Long.parseLong(homeworkId));
        String answer = "";
        if(homeworkAndUser!=null){
            answer = homeworkAndUser.getAnswer();
        }
        String teacherComment = "";
        String score = "";
        if(homeworkAndUser!=null){
            teacherComment = homeworkAndUser.getTeacherComment();
            score = homeworkAndUser.getScore();
        }

        map.put("answer",answer);
        map.put("flag","xs");
        map.put("teacherComment",teacherComment);
        map.put("score",score);

        return new ModelAndView("/answerHomework",map);
    }


    /**
     * 新建作业
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createHomework")
    @ResponseBody
    public String createHomework(HttpServletRequest request,HttpServletResponse response){
        // 课程标题；
        String homeworkTitle = request.getParameter("homeworkTitle");
        // 课程名称；
        String kcName = request.getParameter("kcName");
        // 课程选项；
        String homeworkContent = request.getParameter("homeworkContent");
        // 作业类型；
        String homeworkType = request.getParameter("homeworkType");

        String userName = UserHelp.getUserName(request);
        Long userId = UserHelp.getUserId(request);
        Homework homework = new Homework();
        homework.setContent(homeworkContent);
        homework.setTitle(homeworkTitle);
        homework.setHomeworkType(homeworkType);
        homework.setStatus(Constant.HOMEWORK_STATUS_UNPUB);
        homework.setSubject(kcName);
        homework.setTeacherId(userId);
        homework.setTeacherName(userName);
        homeworkService.saveHomework(homework);
        return "success";
    }


    /**
     * 加载作业列表；
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request, HttpServletResponse response){

        String currentPage = request.getParameter("currentPageNo");
        String pageSize = request.getParameter("pageTotalNum");
        if(currentPage==null){
            currentPage = "1";
        }
        if(pageSize==null){
            pageSize = "10";
        }
        String userName = UserHelp.getUserName(request);
        String flag = "ls";
        if(userName.contains("xs")){
            flag = "xs";
        }

        Long userId = UserHelp.getUserId(request);
        Map<String,Object> resultMap = null;
        if(flag.equals("ls")){
            resultMap = getTeacherHomeworkListAndTotalNum(userId,currentPage,pageSize);
        }else{
            resultMap = getStudentHomeworkListAndTotalNum(userId,currentPage,pageSize);
        }

        List<HomeworkDto> list = (List<HomeworkDto>) resultMap.get("homeworkList");
        Integer totalNum = (Integer) resultMap.get("totalNum");

        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("totalNum",totalNum);

        map.put("flag",flag);
        return map;


    }

    /**
     * 获得学生的作业列表；
     * @param studentId
     * @param currentPage
     * @param pageSize
     * @return
     */
    private Map<String,Object> getStudentHomeworkListAndTotalNum(Long studentId, String currentPage, String pageSize) {
        Map<String,Object> map = new HashMap<>();

        // 构造查询条件；
        Specification<Homework> spec = new Specification<Homework>() {
            @Override
            public Predicate toPredicate(Root<Homework> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> statusPath = null;
                statusPath = root.get("status");
                Predicate predicate = criteriaBuilder.equal(statusPath,Constant.HOMEWORK_STATUS_PUB);
                return predicate;
            }
        };

        Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1,Integer.parseInt(pageSize)-1);
        // 获得分页查询结果；
        Page<Homework> page = homeworkService.findAll(spec,pageable);
        Iterator it = page.iterator();

        List<HomeworkDto> list = new ArrayList<>();
        Homework homework = null;
        HomeworkDto homeworkDto = null;
        String homeworkType = null;
        while(it.hasNext()){
            homeworkDto = new HomeworkDto();
            homework = (Homework) it.next();
            homeworkDto.setHomeworkId(homework.getId());
            homeworkDto.setStatus(homework.getStatus());
            homeworkDto.setSubject(homework.getSubject());
            homeworkType =homework.getHomeworkType();
            if(homeworkType.equals(Constant.HOMEWORK_TYPE_CHOICE)){
                homeworkDto.setHomeworkType("选择");
            }else if(homeworkType.equals(Constant.HOMEWORK_TYPE_JUDGE)){
                homeworkDto.setHomeworkType("判断");
            }else if(homeworkType.equals(Constant.HOMEWORK_TYPE_SHORTANSWER)){
                homeworkDto.setHomeworkType("简答");
            }
            homeworkDto.setTeacherName(homework.getTeacherName());
            homeworkDto.setTitle(homework.getTitle());
            list.add(homeworkDto);
        }

        // 获得记录总数
        int totalNum = homeworkService.findAll(spec).size();
        map.put("homeworkList",list);
        map.put("totalNum",totalNum);

        return map;
    }

    /**
     * 获得老师的作业列表；
     * @param teacherId
     * @param currentPage
     *@param pageSize @return
     */
    private Map<String,Object> getTeacherHomeworkListAndTotalNum(Long teacherId, String currentPage, String pageSize) {
        Map<String,Object> map = new HashMap<>();
        // 构造查询条件；
        Specification<Homework> spec = new Specification<Homework>() {
            @Override
            public Predicate toPredicate(Root<Homework> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> idPath = null;
                idPath = root.get("teacherId");
                Predicate predicate = criteriaBuilder.equal(idPath,teacherId);
                return predicate;
            }
        };

        Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1,Integer.parseInt(pageSize)-1);
        // 获得分页查询结果；
        Page<Homework> page = homeworkService.findAll(spec,pageable);
        Iterator it = page.iterator();

        List<HomeworkDto> list = new ArrayList<>();
        Homework homework = null;
        HomeworkDto homeworkDto = null;
        String homeworkType = "";
        while(it.hasNext()){
            homeworkDto = new HomeworkDto();
            homework = (Homework) it.next();
            homeworkDto.setHomeworkId(homework.getId());
            homeworkDto.setStatus(homework.getStatus());
            homeworkDto.setSubject(homework.getSubject());
            homeworkType =homework.getHomeworkType();
            if(homeworkType.equals(Constant.HOMEWORK_TYPE_CHOICE)){
                homeworkDto.setHomeworkType("选择");
            }else if(homeworkType.equals(Constant.HOMEWORK_TYPE_JUDGE)){
                homeworkDto.setHomeworkType("判断");
            }else if(homeworkType.equals(Constant.HOMEWORK_TYPE_SHORTANSWER)){
                homeworkDto.setHomeworkType("简答");
            }
            homeworkDto.setTeacherName(homework.getTeacherName());
            homeworkDto.setTitle(homework.getTitle());
            list.add(homeworkDto);
        }

        // 获得记录总数
        int totalNum = homeworkService.findAll(spec).size();
        map.put("homeworkList",list);
        map.put("totalNum",totalNum);
        return map;
    }

}
