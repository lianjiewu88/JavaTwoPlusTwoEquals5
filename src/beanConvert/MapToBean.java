package beanConvert;



import java.util.ArrayList;
import java.util.List;

public class MapToBean {
    private String name;
    private String sex;

    private List<InnerBean> innerList;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public List<InnerBean> getInnerList() {
        return innerList;
    }
    public void setInnerList(ArrayList<InnerBean> innerList) {
        this.innerList = innerList;
    }
    @Override
    public String toString() {
        return "MapToBean [name=" + name + ", sex=" + sex + ", innerList=" + innerList + "]";
    }

}
