package com.notes.markdown.service;

import com.notes.markdown.dao.Markdown;

import java.util.List;
import java.util.Map;

public interface MarkdownServer {

    List<Markdown> viewList(Integer page, Integer size, String search) throws InterruptedException;

    Markdown getDetail(Integer id);

    Integer edit(Markdown markdown, Integer id);

    Integer remove(Integer id);

    Map<String,Integer> pagenation(String search);
}
