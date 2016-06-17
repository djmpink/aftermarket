package cn.no7player.common.bean;

/**
 * Created by zhouli on 2016/6/17.
 */
public class QueryBean {
    /**
     * 每页条数
     */
    private Integer pageSize = 10;
    /**
     * 当前页
     */
    private Integer currentPage = 1;
    /**
     * 起始条数
     */
    private Integer start=0;

    /**
     * 结束条数
     */
    private Integer end=0;

    /**
     * total count
     */
    private Integer totalCount = -1;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方式 升序降序
     */
    private String orderType;

    public QueryBean() {
    }

    public QueryBean(Integer currentPage, Integer pageSize) {
        this(currentPage, pageSize, null, "DESC");
    }

    public QueryBean(Integer currentPage, Integer pageSize, String orderBy, String orderType) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.setOrderType(orderType);

        if (currentPage == 1) {
            this.start = 0;
        } else {
            this.start = (currentPage - 1) * pageSize;
        }

        this.end = this.start + pageSize - 1;
    }

    public QueryBean(String orderBy, String orderType, Integer start, Integer pageSize) {

        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.setOrderType(orderType);
        ;
        if (start < 0) {
            start = 0;
        }
        this.start = start;

        this.currentPage = this.start % this.pageSize == 0 ? this.start / this.pageSize : (this.start / this.pageSize + 1);

        this.end = this.start + pageSize - 1;
    }


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        // 分页查询最大行数为5000
        if (pageSize > 5000) {
            pageSize = 5000;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        start = (currentPage - 1) * pageSize;
        return start;
    }

    public Integer getEnd() {
        if (totalCount > 0) {
            return Math.min(end, totalCount - 1);
        }
        return end;
    }

    public Integer getCount() {
        Integer count = getEnd() - getStart();
        return count < 0 ? 0 : count + 1;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {

        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "QueryObject{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
