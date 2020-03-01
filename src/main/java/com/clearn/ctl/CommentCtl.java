package com.clearn.ctl;

import com.clearn.bean.Comment;
import com.clearn.bean.File;
import com.clearn.bean.User;
import com.clearn.services.ICommentService;
import com.clearn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
 * @Date 2018.20:57
 * @Description 评论控制层
 */
@Controller
@RequestMapping("/comment")
public class CommentCtl {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到评论页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toCommentMgr")
    public ModelAndView toFileMgr(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("/commentMgr",map);
    }

    /**
     * 跳转到新建留言页面
     * @return
     */
    @RequestMapping("/toNewComment")
    public ModelAndView toNewComment(){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("/newComment",map);
    }

    /**
     * 跳转到回复留言页面
     * @return
     */
    @RequestMapping("/toAnswerComment")
    public ModelAndView toAnswerComment(HttpServletRequest request,HttpServletResponse response){
        String commentId = request.getParameter("commentId");
        Map<String,Object> map = new HashMap<>();
        map.put("commentId",commentId);
        return new ModelAndView("/answerComment",map);
    }


    /**
     * 答复留言
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/answerComment")
    @ResponseBody
    public String answerComment(HttpServletRequest request,HttpServletResponse response){
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");
        String userId = sessionUser.split("_")[1];
//        commentId="+commentId+"&commentContent="+commentContent,
        String commentId = request.getParameter("commentId");
        String commentContent = request.getParameter("commentContent");
//        Comment comment = new Comment();
        Comment comment  = commentService.findById(Long.parseLong(commentId));
        comment.setContent(commentContent);
        commentService.updateComment(comment);
        return "sucess";
    }



    /**
     * 创建评论
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createComment")
    @ResponseBody
    public String createComment(HttpServletRequest request,HttpServletResponse response){
        // teacherId="+lsId+"&commentContent=
        String teacherIdStr = request.getParameter("teacherId");
        String commentContent = request.getParameter("commentContent");
        String kcName = request.getParameter("kcName");

        User teacher = userService.getUserById(teacherIdStr);
        Comment comment = new Comment();
        comment.setTitle(commentContent);
        comment.setCourseName(kcName);
        // "sessionUser",userName+"_"+user.getUserId()
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");
        String[] sessionUserArr = sessionUser.split("_");
        comment.setStudentId(Long.parseLong(sessionUserArr[1]));
        comment.setStudentName(sessionUserArr[0]);
        comment.setTeacherId(teacher.getUserId());
        comment.setTeacherName(teacher.getUserName());

        commentService.saveComment(comment);

        return "success";
    }

    /**
     * 删除评论；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteComment")
    @ResponseBody
    public String deleteComment(HttpServletRequest request,HttpServletResponse response){
        String commentId = request.getParameter("commentId");
        Comment comment = new Comment();
        comment.setId(Long.parseLong(commentId));
        commentService.deleteComment(comment);
        return "success";
    }


    /**
     * 获取评论列表；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> commentList(HttpServletRequest request, HttpServletResponse response){

        // "sessionUser",userName+"_"+user.getUserId()
        String sessionUser = (String) request.getSession().getAttribute("sessionUser");

        String currentPage = request.getParameter("currentPageNo");
        String pageSize = request.getParameter("pageTotalNum");
        if(currentPage==null){
            currentPage = "1";
        }

        if(pageSize==null){
            pageSize = "10";
        }
        String userId = sessionUser.split("_")[1];

        // 构造查询条件；
        Specification<Comment> spec = new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> idPath = null;
                if(sessionUser.contains("ls")){
                    idPath = root.get("teacherId");
                }else{
                    idPath = root.get("studentId");
                }
                Predicate predicate = criteriaBuilder.equal(idPath,userId);
                return predicate;
            }
        };

        Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1,Integer.parseInt(pageSize)-1);
        // 获得分页查询结果；
        Page<Comment> page = commentService.findAll(spec,pageable);
        Iterator it = page.iterator();
        List<Comment> list = new ArrayList<>();
        Comment comment = null;
        while(it.hasNext()){
            comment = (Comment) it.next();
            list.add(comment);
        }
        // 获得记录总数
        int totalNum = commentService.findAll(spec).size();

        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("totalNum",totalNum);

        String flag = "ls";
        if(sessionUser.contains("xs")){
            flag = "xs";
        }
        map.put("flag",flag);
        return map;

    }



}
