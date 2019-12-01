package com.fh.pageutil;

import java.util.List;

public class PageBean<T> {

    private Integer start;

    private Integer length;

    private Integer drow=1;

    private Long recordTotal;

    private Long recordsFiltered;

    private List<T> data;


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDrow() {
        return drow;
    }

    public void setDrow(Integer drow) {
        this.drow = drow;
    }

    public Long getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(Long recordTotal) {
        this.recordTotal = recordTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
