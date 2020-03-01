package com.clearn.ctl;

import com.clearn.bean.*;
import com.clearn.services.IHomeworkUserService;
import com.clearn.services.IUserService;
import com.clearn.utils.UserHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2018.22:24
 */
@Controller
@RequestMapping("/score")
public class ScoreCtl {
    @Autowired
    private IHomeworkUserService homeworkUserService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到成绩管理界面
     * @return
     */
    @RequestMapping("/toScoreMgr")
    public ModelAndView toScoreMgr(){
        Map<String,Object> map = new HashMap<>();
        return new ModelAndView("scoreMgr",map);
    }

    /**
     * 成绩列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request, HttpServletResponse response){

        String currentPage = request.getParameter("currentPageNo");
        String pageSize = request.getParameter("pageTotalNum");
        String subjectName = request.getParameter("subjectName");

        if(currentPage==null){
            currentPage = "1";
        }
        if(pageSize==null){
            pageSize = "10";
        }
        String userName = UserHelp.getUserName(request);

        Long userId = UserHelp.getUserId(request);
        Map<String,Object> resultMap = getScoreList(subjectName);

        List<HomeworkDto> list = (List<HomeworkDto>) resultMap.get("homeworkList");
        Integer totalNum = (Integer) resultMap.get("totalNum");

        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("totalNum",totalNum);

        return map;


    }

    /**
     * 成绩列表
     */

    @PersistenceContext
    private EntityManager entityManager;

    private Map<String,Object> getScoreList(String subjectName) {
        Map<String,Object> map = new HashMap<>();
        Query query = entityManager.createNativeQuery("select SUM(hu.score) as totalScore,hu.student_id as studentId from homework_user as hu where hu.subject_name=?1 GROUP BY hu.student_id");
        query.setParameter(1,subjectName);
        List rows = query.getResultList();
        BigInteger studentId = null;
        Double totalScore = 0D;
        User user = null;
        ScoreListDto scoreListDto = null;
        List<ScoreListDto> list = new ArrayList<>();
        int i=0;
        for (Object row : rows) {
            Object[] cells = (Object[]) row;
            user = userService.getUserById(cells[1]+"");
            i++;
            if(user!=null && cells[0]!=null){
                scoreListDto = new ScoreListDto(subjectName,user.getUserName(),cells[0]+"",i+"");
                list.add(scoreListDto);
            }
        }
        map.put("homeworkList",list);
        map.put("totalNum",rows.size());
        return map;
    }


}
