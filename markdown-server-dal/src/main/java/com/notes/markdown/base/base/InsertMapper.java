package com.notes.markdown.base.base;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * 基础添加功能mapper
 *
 * @author scott
 */
public interface InsertMapper<T> extends Marker,
        tk.mybatis.mapper.common.base.BaseInsertMapper<T>,
        InsertSelectiveMapper<T>,
        MySqlMapper<T> {

}
