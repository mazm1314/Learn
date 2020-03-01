package com.clearn.ctl;

import com.clearn.bean.*;
import com.clearn.dao.UserRepository;
import com.clearn.services.*;
import com.clearn.utils.Constant;
import com.clearn.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @Author Administrator
 * @Date 2018.21:55
 * @Description
 */
@Controller
@RequestMapping("/index")
public class IndexCtl {

    @Autowired
    private IUserService userService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IHomeworkService homeworkService;
    @Autowired
    private IHomeworkUserService homeworkUserService;
    @Autowired
    private IMessageService messageService;


    /**
     * 跳转到登录页面；
     * @return
     */
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("login",map);
    }

    /**
     * 退出登录；
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("sessionUser");
        return new ModelAndView("login");
    }

    /**
     * 跳转到用户详情页；
     * @param request
     * @param response
     * @return
     */
    // Personinfo.jsp
    @RequestMapping("/toPersonInfo")
    public ModelAndView toPersonInfo(HttpServletRequest request,HttpServletResponse response){
        String userId = request.getParameter("userId");
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        User user = userService.getUserById(userId);
        map.put("userName",user.getUserName());
        return new ModelAndView("Personinfo",map);
    }


    /**
     * 获得文件列表；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fileList")
    @ResponseBody
    public Map<String,Object> fileList(HttpServletRequest request,HttpServletResponse response){
        String currentPage = request.getParameter("currentPageNo");
        String pageSize = request.getParameter("pageTotalNum");
        if(currentPage==null){
            currentPage = "1";
        }

        if(pageSize==null){
            pageSize = "10";
        }

        Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1,Integer.parseInt(pageSize)-1);
        Page<File> page = fileService.findPage(pageable);
        Iterator it = page.iterator();
        List<File> list = new ArrayList<>();
        File file = null;
        while(it.hasNext()){
            file = (File) it.next();
            list.add(file);
        }
        int totalNum = fileService.findAll().size();

        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("totalNum",totalNum);
        // "sessionUser",userName+"_"+user.getUserId()
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");
        String flag = "ls";
        if(sessionUser.contains("xs")){
            flag = "xs";
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * 跳转到文件管理页面；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toFileMgr")
    public ModelAndView toFileMgr(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("/fileMgr",map);
    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(HttpServletRequest request,HttpServletResponse response){
        String fileId = request.getParameter("fileId");
        fileService.deleteFile(Long.parseLong(fileId));
        return null;
    }
    /**
     * 修改密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/modifyPass")
    public ModelAndView modifyPass(HttpServletRequest request,HttpServletResponse response){
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String userId = request.getParameter("userId");

        User user = userService.getUserById(userId);
        if(user!=null){
            if(!(oldPass.equals(user.getUserPass()))){
                try {
                    response.getWriter().write("fail");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            user.setUserPass(newPass);
            userService.updateUser(user);
        }else{
            try {
                response.getWriter().write("fail");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        try {
            response.sendRedirect("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/toWelcome")
    public ModelAndView toWelcome(HttpServletResponse response,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");
        String[] sessionUserArr = sessionUser.split("_");
        String userName = sessionUserArr[0];
        Long userId = Long.parseLong(sessionUserArr[1]);
        map.put("userName",userName);
        if(userName.contains("ls")){
            // 查找当前老师没有处理的的留言数量；
            int commentNum = getUnDealComment(userId);
            map.put("commentNum",commentNum+"");
        }else{
            // 获得当前学生的家庭作业数；
            int homeworkNum = getHomeworkNum(userId);
            map.put("homeworkNum",homeworkNum+"");
            List<Message> messageList  =getMessageList();
            map.put("messageList",messageList);
        }
        return new ModelAndView("welcome",map);
    }

    /**
     * 获得学生作业数；
     * @param studentId
     * @return
     */
    private int getHomeworkNum(Long studentId) {
        int homeworkNum = 0;
        // 构造查询条件；- 已经发布的作业；
        Specification<Homework> specOfPubHomework = new Specification<Homework>() {
            @Override
            public Predicate toPredicate(Root<Homework> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> statusPath = null;
                statusPath = root.get("status");
                // 已发布的作业；
                Predicate predicate = criteriaBuilder.equal(statusPath, Constant.HOMEWORK_STATUS_PUB);
                return predicate;
            }
        };
        // 获得已经发布的作业；
        List<Homework> homeworkOfPub = homeworkService.findAll(specOfPubHomework);

        // 构造查询条件； - 用户已经处理的作业
        Specification<HomeworkAndUser> specOfUnDealHomework = new Specification<HomeworkAndUser>() {
            @Override
            public Predicate toPredicate(Root<HomeworkAndUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> studentIdPath = null;
                studentIdPath = root.get("studentId");
                // 已发布的作业；
                Predicate predicate = criteriaBuilder.equal(studentIdPath, studentId);
                return predicate;
            }
        };
        // 获得已经处理的作业；
        List<HomeworkAndUser> homeworkOfDeal = homeworkUserService.findAll(specOfUnDealHomework);
        homeworkNum = homeworkOfPub.size()-homeworkOfDeal.size();
        return homeworkNum;
    }

    /**
     *  获得老师没有处理的留言数量；
     * @param teacherId
     * @return
     */
    private int getUnDealComment(Long teacherId) {

        // 构造查询条件；
        Specification<Comment> spec = new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> idPath = null;
                Path<String> commentPath = null;
                idPath = root.get("teacherId");
                commentPath = root.get("content");
                // 当前老师
                Predicate predicateOfTeacherId = criteriaBuilder.equal(idPath,teacherId);
                // 评论为空
                //Predicate predicateOfComment = criteriaBuilder.equal(commentPath,"");
                Predicate predicateOfComment = criteriaBuilder.isNull(commentPath);
                Predicate predicate = criteriaBuilder.and(predicateOfComment,predicateOfTeacherId);
                return predicate;
            }
        };
        List<Comment> comments = commentService.findAll(spec);
        return comments.size();
    }


    /**
     * 登录操作
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("username");
        String userPass = request.getParameter("password");
        User user = userService.getUserByUserNameAndUserPass(userName,userPass);
        Map<String,Object> map = new HashMap<>();
        String responsePage = "studentIndex";
        if(user==null){
            responsePage = "login";
            map.put("result","false");
            map.put("msg","用户名或密码错误!");
        }else{
            if(userName.contains("ls")){
                responsePage="teacherIndex";
            }
            request.getSession().setAttribute("sessionUser",userName+"_"+user.getUserId());
            map.put("result","true");
            map.put("userName",userName);
            map.put("userPass",userPass);
            map.put("userId",user.getUserId());
        }
        return new ModelAndView(responsePage,map);
    }

    /**
     * 文件上传
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("/fileUpload")
    public @ResponseBody void fileUpload(HttpServletRequest request,HttpServletResponse response,
                                           @RequestParam("file")MultipartFile file){
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("contentType:"+contentType+",fileName:"+fileName);
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try{
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            File fileBean = new File();
            fileBean.setFileName(fileName);
            fileService.saveFile(fileBean);
            response.getWriter().write("<script>alert('sucess')</script>");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 文件下载
     * @param request
     * @param response
     */
    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    @ResponseBody
    public void fileDownload(HttpServletRequest request,HttpServletResponse response) {
        String fileId = request.getParameter("fileId");
        File file = fileService.findOne(Long.parseLong(fileId));
        String fileName = file.getFileName();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
            bis = new BufferedInputStream(new FileInputStream(new java.io.File(filePath+ fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try{
                    os.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }


    /**
     * 注册操作；
     * @return
     */
    @RequestMapping("/register")
    public String register(HttpServletRequest request,HttpServletResponse response){
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if(userName!=null && password!=null){
            User user = new User();
            user.setUserPass(password);
            user.setUserName(userName);
            userService.saveUser(user);
        }else{
            return "register";
        }

        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 获得消息列表；
     * @return
     */
    public List<Message> getMessageList() {
        return messageService.finAll();
    }
}
