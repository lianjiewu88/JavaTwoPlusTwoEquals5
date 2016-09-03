package beanConvert;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapToBean {
    private String name;
    private String sex;

    // Jerry: get this attribute's type via reflection
    private List<InnerBean> innerList;
    private Map<String, InnerBean> innerMap;
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
    
    public void setInnerMap(HashMap<String, InnerBean> innerMap) {
        this.innerMap = innerMap;
    }
    @Override
    public String toString() {
        return "MapToBean [name=" + name + ", sex=" + sex + ", innerList=" + innerList + "]"
        		+ ", innerMap=" + innerMap;
    }

}
