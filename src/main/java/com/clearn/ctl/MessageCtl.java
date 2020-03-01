package com.clearn.ctl;

import com.clearn.bean.Comment;
import com.clearn.bean.Message;
import com.clearn.services.IMessageService;
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
 * @Date 2018.22:45
 */
@Controller
@RequestMapping("/message")
public class MessageCtl {

    @Autowired
    private IMessageService messageService;

    @RequestMapping("/toMessageMgr")
    public ModelAndView toFileMgr(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("/messageMgr",map);
    }


    /**
     * 消息列表；
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        String currentPage = request.getParameter("currentPageNo");
        String pageSize = request.getParameter("pageTotalNum");
        if(currentPage==null){
            currentPage = "1";
        }
        if(pageSize==null){
            pageSize = "10";
        }

        Long userId = UserHelp.getUserId(request);

        // 构造查询条件；
        Specification<Message> spec = new Specification<Message>() {
            @Override
            public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> idPath = null;
                idPath = root.get("teacherId");
                Predicate predicate = criteriaBuilder.equal(idPath,userId);
                return predicate;
            }
        };

        Pageable pageable = new PageRequest(Integer.parseInt(currentPage)-1,Integer.parseInt(pageSize)-1);
        // 获得分页查询结果；
        Page<Message> page = messageService.findAll(spec,pageable);
        Iterator it = page.iterator();
        List<Message> list = new ArrayList<>();
        Message message = null;
        while(it.hasNext()){
            message = (Message) it.next();
            list.add(message);
        }
        // 获得记录总数
        int totalNum = messageService.findAll(spec).size();

        map.put("list",list);
        map.put("totalNum",totalNum);
        return map;
    }

    @RequestMapping("/createMessage")
    @ResponseBody
    public String createMessage(HttpServletRequest request,HttpServletResponse response){
        String messageContent = request.getParameter("messageContent");
        String userName = UserHelp.getUserName(request);
        Long userId = UserHelp.getUserId(request);
        Message message = new Message();
        message.setTeacherId(userId);
        message.setTeacherName(userName);
        message.setMessage(messageContent);
        message.setStatus(Constant.MESSAGE_STATUS_UNPUB);
        messageService.saveMessage(message);
        return "success";
    }





    @RequestMapping("/deleteMessage")
    @ResponseBody
    public String deleteMessage(HttpServletRequest request,HttpServletResponse response){
        String messageId = request.getParameter("messageId");
        messageService.deleteMessage(Long.parseLong(messageId));
        return "success";
    }

    @RequestMapping("/toNewMessage")
    public ModelAndView toNewMessage(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("newMessage",map);
    }






}
