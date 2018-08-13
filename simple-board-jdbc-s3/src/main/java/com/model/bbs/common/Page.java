package com.model.bbs.common;
 
import org.springframework.stereotype.Component;
 
@Component
public class Page {
    private int startRow, endRow;
    private StringBuffer sb;
    
    public synchronized void paging(int pageNum, int totalCount, int pageSize, int pageBlock) {
       //
    }
    
    public StringBuffer getSb() {
        return sb;
    }
 
    public int getStartRow() {
        return startRow;
    }
 
    public int getEndRow() {
        return endRow;
    }
}

