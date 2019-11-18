package com.notes.markdown.mapper;

import com.notes.markdown.dao.Markdown;
import com.notes.markdown.base.base.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository //也可使用component 仅仅是为了表示为bean
@Mapper
public interface MarkdownMapper extends CrudMapper<Markdown> {
    //可定义接口。
    int deleteMarkdown(int id);

    int updateMarkdown(Markdown markdown);

    int deleteMarkdownBack(int id);

    int countMarkdown(@Param("params") Map<String,Object> params);
}
