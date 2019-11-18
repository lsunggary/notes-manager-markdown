package com.notes.markdown.control;

import com.notes.markdown.common.BizException;
import com.notes.markdown.dao.Markdown;
import com.notes.markdown.service.MarkdownServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/markdown")
public class MarkdownServerControl {

    @Autowired
    private MarkdownServer markdownServer;

    @GetMapping("/list")
    public List<Markdown> view(String page, String size, String search) throws InterruptedException {
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        if (StringUtil.isEmpty(size)) {
            size = "10";
        }
        return markdownServer.viewList(Integer.parseInt(page), Integer.parseInt(size), search);
    }

    @GetMapping("/one")
    public Markdown getMarkdown(String id) throws BizException {
        if (StringUtil.isEmpty(id)) {
            throw new BizException("内容不能为空！");
        }
        return markdownServer.getDetail(Integer.parseInt(id));
    }

    @PostMapping("/create")
    public Integer createMarkdown(String id, String title, String context) throws BizException {
        if (StringUtil.isEmpty(title)) {
            throw new BizException("标题不能为空！");
        }

        Markdown markdown = new Markdown();
        int markdownId = 0;
        if (!"".equals(id)) {
            markdownId = Integer.parseInt(id);
        }
        markdown.setTitle(title);
        markdown.setMkContext(context);
        return markdownServer.edit(markdown, markdownId);
    }

    // 修改md内容
    @PostMapping("/update/context")
    public Integer editMarkdownContext(String id, String mkContext) throws BizException {
        Markdown markdown = new Markdown();
        int markdownId = 0;
        if (!"".equals(id)) {
            markdownId = Integer.parseInt(id);
        }
        markdown.setId(markdownId);
        markdown.setMkContext(mkContext);
        return markdownServer.edit(markdown, markdownId);
    }

    // 修改md标题
    @PostMapping("/update/title")
    public Integer editMarkdownTile(String id, String title) throws BizException {
        if (StringUtil.isEmpty(title)) {
            throw new BizException("标题不能为空！");
        }
        Markdown markdown = new Markdown();
        int markdownId = 0;
        if (!"".equals(id)) {
            markdownId = Integer.parseInt(id);
        }
        markdown.setId(markdownId);
        markdown.setTitle(title);
        return markdownServer.edit(markdown, markdownId);
    }

    @PostMapping("/delete")
    public Integer removeMarkdown(String id) throws BizException {
        if (StringUtil.isEmpty(id)) {
            throw new BizException("删除内容不能为空！");
        }
        return markdownServer.remove(Integer.parseInt(id));
    }

    @GetMapping("/pageinfo")
    public Map<String,Integer> getPagenation(String search) {
        return markdownServer.pagenation(search);
    }

    @ExceptionHandler
    public String exceptionHandler(Throwable e) {
        if (e instanceof BizException) {
            return e.getMessage();
        } else {
            return e.getMessage();
        }
    }
}
