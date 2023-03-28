package com.yizhi.hospital.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The type Page info.
 *
 * @param <T> the type parameter
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int[] navigatepageNums;
    private int navigateFirstPage;
    private int navigateLastPage;

    /**
     * Instantiates a new Page info.
     */
    public PageInfo() {
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
    }

    /**
     * Instantiates a new Page info.
     *
     * @param list the list
     */
    public PageInfo(List<T> list) {
        this(list, 8);
    }

    /**
     * Instantiates a new Page info.
     *
     * @param list          the list
     * @param navigatePages the navigate pages
     */
    public PageInfo(List<T> list, int navigatePages) {
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
        if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = 1;
            this.list = list;
            this.size = list.size();
            this.total = (long) list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
        if (list instanceof Collection) {
            this.navigatePages = navigatePages;
            this.calcNavigatepageNums();
            this.calcPage();
            this.judgePageBoudary();
        }
    }

    private void calcNavigatepageNums() {
        int i;
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];
            for (i = 0; i < this.pages; ++i) {
                this.navigatepageNums[i] = i + 1;
            }
        } else {
            this.navigatepageNums = new int[this.navigatePages];
            i = this.pageNum - this.navigatePages / 2;
            int endNum = this.pageNum + this.navigatePages / 2;
            //int i;
            if (i < 1) {
                i = 1;
                for (i = 0; i < this.navigatePages; ++i) {
                    this.navigatepageNums[i] = i++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;
                for (i = this.navigatePages - 1; i >= 0; --i) {
                    this.navigatepageNums[i] = endNum--;
                }
            } else {
                for (i = 0; i < this.navigatePages; ++i) {
                    this.navigatepageNums[i] = i++;
                }
            }
        }
    }

    private void calcPage() {
        if (this.navigatepageNums != null && this.navigatepageNums.length > 0) {
            this.navigateFirstPage = this.navigatepageNums[0];
            this.navigateLastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }
            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }
    }

    private void judgePageBoudary() {
        this.isFirstPage = this.pageNum == 1;
        this.isLastPage = this.pageNum == this.pages;
        this.hasPreviousPage = this.pageNum > 1;
        this.hasNextPage = this.pageNum < this.pages;
    }

    /**
     * Gets page num.
     *
     * @return the page num
     */
    public int getPageNum() {
        return this.pageNum;
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
     * Gets page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return this.pageSize;
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
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets start row.
     *
     * @return the start row
     */
    public int getStartRow() {
        return this.startRow;
    }

    /**
     * Sets start row.
     *
     * @param startRow the start row
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * Gets end row.
     *
     * @return the end row
     */
    public int getEndRow() {
        return this.endRow;
    }

    /**
     * Sets end row.
     *
     * @param endRow the end row
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public long getTotal() {
        return this.total;
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
     * Gets pages.
     *
     * @return the pages
     */
    public int getPages() {
        return this.pages;
    }

    /**
     * Sets pages.
     *
     * @param pages the pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<T> getList() {
        return this.list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Gets first page.
     *
     * @return the first page
     */
    @Deprecated
    public int getFirstPage() {
        return this.navigateFirstPage;
    }

    /**
     * Sets first page.
     *
     * @param firstPage the first page
     */
    @Deprecated
    public void setFirstPage(int firstPage) {
        this.navigateFirstPage = firstPage;
    }

    /**
     * Gets pre page.
     *
     * @return the pre page
     */
    public int getPrePage() {
        return this.prePage;
    }

    /**
     * Sets pre page.
     *
     * @param prePage the pre page
     */
    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    /**
     * Gets next page.
     *
     * @return the next page
     */
    public int getNextPage() {
        return this.nextPage;
    }

    /**
     * Sets next page.
     *
     * @param nextPage the next page
     */
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * Gets last page.
     *
     * @return the last page
     */
    @Deprecated
    public int getLastPage() {
        return this.navigateLastPage;
    }

    /**
     * Sets last page.
     *
     * @param lastPage the last page
     */
    @Deprecated
    public void setLastPage(int lastPage) {
        this.navigateLastPage = lastPage;
    }

    /**
     * Is is first page boolean.
     *
     * @return the boolean
     */
    public boolean isIsFirstPage() {
        return this.isFirstPage;
    }

    /**
     * Sets is first page.
     *
     * @param isFirstPage the is first page
     */
    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    /**
     * Is is last page boolean.
     *
     * @return the boolean
     */
    public boolean isIsLastPage() {
        return this.isLastPage;
    }

    /**
     * Sets is last page.
     *
     * @param isLastPage the is last page
     */
    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    /**
     * Is has previous page boolean.
     *
     * @return the boolean
     */
    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    /**
     * Sets has previous page.
     *
     * @param hasPreviousPage the has previous page
     */
    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    /**
     * Is has next page boolean.
     *
     * @return the boolean
     */
    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    /**
     * Sets has next page.
     *
     * @param hasNextPage the has next page
     */
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    /**
     * Gets navigate pages.
     *
     * @return the navigate pages
     */
    public int getNavigatePages() {
        return this.navigatePages;
    }

    /**
     * Sets navigate pages.
     *
     * @param navigatePages the navigate pages
     */
    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    /**
     * Get navigatepage nums int [ ].
     *
     * @return the int [ ]
     */
    public int[] getNavigatepageNums() {
        return this.navigatepageNums;
    }

    /**
     * Sets navigatepage nums.
     *
     * @param navigatepageNums the navigatepage nums
     */
    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    /**
     * Gets navigate first page.
     *
     * @return the navigate first page
     */
    public int getNavigateFirstPage() {
        return this.navigateFirstPage;
    }

    /**
     * Gets navigate last page.
     *
     * @return the navigate last page
     */
    public int getNavigateLastPage() {
        return this.navigateLastPage;
    }

    /**
     * Sets navigate first page.
     *
     * @param navigateFirstPage the navigate first page
     */
    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    /**
     * Sets navigate last page.
     *
     * @param navigateLastPage the navigate last page
     */
    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", startRow=").append(this.startRow);
        sb.append(", endRow=").append(this.endRow);
        sb.append(", total=").append(this.total);
        sb.append(", pages=").append(this.pages);
        sb.append(", list=").append(this.list);
        sb.append(", prePage=").append(this.prePage);
        sb.append(", nextPage=").append(this.nextPage);
        sb.append(", isFirstPage=").append(this.isFirstPage);
        sb.append(", isLastPage=").append(this.isLastPage);
        sb.append(", hasPreviousPage=").append(this.hasPreviousPage);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", navigatePages=").append(this.navigatePages);
        sb.append(", navigateFirstPage").append(this.navigateFirstPage);
        sb.append(", navigateLastPage").append(this.navigateLastPage);
        sb.append(", navigatepageNums=");
        if (this.navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < this.navigatepageNums.length; ++i) {
                sb.append(i == 0 ? "" : ", ").append(this.navigatepageNums[i]);
            }
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
