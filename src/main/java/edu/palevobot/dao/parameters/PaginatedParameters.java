package edu.palevobot.dao.parameters;

public class PaginatedParameters {

    private Integer pageNum;
    private Integer pageSize;
    public static final int DEFAULT_PAGE_SIZE = 100;


    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaginatedParameters other = (PaginatedParameters) obj;
        if (pageNum == null && other.pageNum != null)
            return false;
        else if (!pageNum.equals(other.pageNum))
            return false;
        if (pageSize == null && other.pageSize != null)
            return false;
         else if (!pageSize.equals(other.pageSize))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pageNum == null) ? 0 : pageNum.hashCode());
        result = prime * result
                + ((pageSize == null) ? 0 : pageSize.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PaginatedParameters [pageNum=" + pageNum + ", pageSize=" + pageSize+ "]";
    }
}
