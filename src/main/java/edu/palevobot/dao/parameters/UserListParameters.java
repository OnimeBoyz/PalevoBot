package edu.palevobot.dao.parameters;

import edu.palevobot.entities.User;

public class UserListParameters  extends PaginatedParameters{

    private String keyword;
    private String orderBy;
    private Order order;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj) || getClass() != obj.getClass())
            return false;

        UserListParameters other = (UserListParameters) obj;
        if (keyword == null && other.keyword != null)
            return false;
         else if (!keyword.equals(other.keyword))
            return false;
        if (order != other.order)
            return false;
        if (orderBy == null && other.orderBy != null)
            return false;
        else if (!orderBy.equals(other.orderBy))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "UserListParameters [keyword=" + keyword + ", orderBy="
                + orderBy + ", order=" + order + "]";
    }
}
