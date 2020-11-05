package com.example.plugin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page {
    public Page(int currentPage,int pageSize){
        this.currentPage=currentPage;
        this.pageSize=pageSize;
    }

    private int totalNumber;// 当前表中总条目数量
    private int currentPage;// 当前页的位置

    private int totalPage;	// 总页数
    private int pageSize = 3;// 页面大小

    private int startIndex;	// 检索的起始位置
    private int totalSelect;// 检索的总数目

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        // 计算
        this.count();
    }

    public void count() {
        int totalPageTemp = this.totalNumber / this.pageSize;
        int plus = (this.totalNumber % this.pageSize) == 0 ? 0 : 1;
        totalPageTemp = totalPageTemp + plus;
        if (totalPageTemp <= 0) {
            totalPageTemp = 1;
        }
        this.totalPage = totalPageTemp;// 总页数

        if (this.totalPage < this.currentPage) {
            this.currentPage = this.totalPage;
        }
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
        this.startIndex = (this.currentPage - 1) * this.pageSize;// 起始位置等于之前所有页面输乘以页面大小
        this.totalSelect = this.pageSize;// 检索数量等于页面大小
    }
}
