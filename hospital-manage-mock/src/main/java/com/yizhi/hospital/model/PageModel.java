package com.yizhi.hospital.model;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * The type Page model.
 *
 * @param <T> the type parameter
 */
public class PageModel<T> implements java.io.Serializable {

    // 总条数
    private long total;

    // 每页大小
    private int pageSize;

    // 总页数
    private int totalPage;

    // 第几页
    private int pageNum = 1;

    private int[] navigatepageNums;

    private JSONArray list;

    /**
     * Instantiates a new Page model.
     */
    public PageModel() {
    }

    /**
     * Instantiates a new Page model.
     *
     * @param list     the list
     * @param pageNum  the page num
     * @param total    the total
     * @param pageSize the page size
     */
    public PageModel(JSONArray list, int pageNum, long total, int pageSize) {
        this.list = list;
        this.pageNum = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        this.init();
    }

    /**
     * Init.
     */
    public void init() {
        // pageSize 默认为5
        if (pageSize <= 0) {
            pageSize = 5;
        }
        totalPage = (int) (total / pageSize);
        if (0 != total % pageSize) {
            totalPage += 1;
        }
        if (pageNum > totalPage) {
            pageNum = totalPage;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets total page.
     *
     * @return the total page
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Sets total page.
     *
     * @param totalPage the total page
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * Gets page num.
     *
     * @return the page num
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Sets page num.
     *
     * @param pageNum the page num
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public JSONArray getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(JSONArray list) {
        this.list = list;
    }

    /**
     * Get navigatepage nums int [ ].
     *
     * @return the int [ ]
     */
    public int[] getNavigatepageNums() {
        int beginPageIndex;//页码列表的开始索引
        int endPageIndex;//页码列表的结束索引
        if (totalPage <= 10) {
            beginPageIndex = 1;
            endPageIndex = totalPage;
        }
        //总页数多于10页，则显示当前页附近的共10个页码
        else {
            //当前页附近的共10个页码（前4个+当前页+后5个）
            beginPageIndex = pageNum - 4;
            endPageIndex = pageNum + 5;
            //当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            //当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > totalPage) {
                endPageIndex = totalPage;
                beginPageIndex = totalPage - 10 + 1;
            }
        }
        navigatepageNums = new int[endPageIndex - beginPageIndex + 1];
        int j = 0;
        for (int i = beginPageIndex; i <= endPageIndex; i++) {
            navigatepageNums[j] = i;
            j++;
        }
        return navigatepageNums;
    }

    /**
     * Sets navigatepage nums.
     *
     * @param navigatepageNums the navigatepage nums
     */
    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    @Override
    public String toString() {
        return "Page [total=" + total + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", pageNum="
                + pageNum + "]";
    }
}