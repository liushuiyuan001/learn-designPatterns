package com.example.demo.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PageReq {
    
    @NotNull(message = "【页码】不能为空")
    private int page;
    
    @Max(value = 100000, message = "【每页条数】不能超过1000")
    private int size;
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
    
    public void setSize(int size) {
        this.size = size;
    }
}