package com.pythoncat.listdemo;

/**
 * Created by pythonCat on 2016/7/6.
 */
public class Bean {

    public String roomId;
    public String roomName;
    public String roomType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bean bean = (Bean) o;

        return roomId != null ? roomId.equals(bean.roomId) : bean.roomId == null;

    }

    @Override
    public int hashCode() {
        return roomId != null ? roomId.hashCode() : 0;
    }
}
