package com.notes.markdown.base.base;

public interface CrudMapper<T> extends  InsertMapper<T>,
        DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T>{
}
