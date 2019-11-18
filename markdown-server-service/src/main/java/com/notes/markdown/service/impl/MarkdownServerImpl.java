package com.notes.markdown.service.impl;

import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.notes.markdown.dao.Markdown;
import com.notes.markdown.mapper.MarkdownMapper;
import com.notes.markdown.service.MarkdownServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarkdownServerImpl implements MarkdownServer {

    @Autowired
    private MarkdownMapper markdownMapper;

    @Override
    @HystrixCommand(fallbackMethod = "viewListCallBack")
    public List<Markdown> viewList(Integer page, Integer size, String search) throws InterruptedException {
        Condition condition = new Condition(Markdown.class);
        StringBuffer sb = new StringBuffer();
        sb.append("`status` = 1 and (title like '%%");
        sb.append(search);
        sb.append("%%' or mk_context like '%%");
        sb.append(search);
        sb.append("%%')");
        condition.createCriteria().andCondition(sb.toString());
        PageHelper.startPage(page, size);
        List<Markdown> markdowns = markdownMapper.selectByCondition(condition);
        for (Markdown markdown:markdowns
             ) {
            String context = markdown.getMkContext();
            if (50 > context.length()) {
                markdown.setMkContext(context.substring(0, context.length()));
            } else {
                markdown.setMkContext(context.substring(0, 50));
            }
        }
        return markdowns;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDetailCallBack")
    public Markdown getDetail(Integer id) {
        return markdownMapper.selectByPrimaryKey(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "updateCallBack")
    public Integer edit(Markdown markdown, Integer id) {
        if (0 == id) {
            markdown.setStatus(1);
            markdownMapper.insert(markdown);
        } else {
            markdown.setId(id);
            markdownMapper.updateMarkdown(markdown);
        }
        return markdown.getId();
    }

    @Override
    @HystrixCommand(fallbackMethod = "deleteCallBack")
    public Integer remove(Integer id) {
        return markdownMapper.deleteMarkdown(id);
    }

    @Override
    public Map<String, Integer> pagenation(String search) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("status",1);
        params.put("search", search);
        int count = markdownMapper.countMarkdown(params);
        Map<String,Integer> result = new HashMap<String,Integer>();
        result.put("totalPage", (int) Math.ceil((float) count/10.0));
        result.put("currentPage",1);
        result.put("totalCount", count);
        return result;
    }

    public List<Markdown> viewListCallBack(Integer page, Integer size, String search) {
        Markdown markdown = new Markdown();
        markdown.setId(0);
        markdown.setStatus(0);
        markdown.setCreateAt(new Timestamp(System.currentTimeMillis()));
        markdown.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        markdown.setTitle("440:网络连接超时,服务降级！");
        markdown.setMkContext("440:网络连接超时,page:"+page+",size:"+size+",search:"+search);
        List<Markdown> markdowns = new ArrayList<>();
        markdowns.add(markdown);
        return markdowns;
    }

    public Markdown getDetailCallBack(Integer id) {
        Markdown markdown = new Markdown();
        markdown.setId(0);
        markdown.setStatus(0);
        markdown.setCreateAt(new Timestamp(System.currentTimeMillis()));
        markdown.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        markdown.setTitle("440:网络连接超时,服务降级！");
        markdown.setMkContext("440:网络连接超时,id:"+id);
        return markdown;
    }

    public Integer updateCallBack(Markdown markdown, Integer id) {
        return 0;
    }

    public Integer deleteCallBack(Integer id) {
        return 0;
    }
}
