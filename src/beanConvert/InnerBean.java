package beanConvert;

public class InnerBean {

    private String beanName;

    private String beanNo;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanNo() {
        return beanNo;
    }

    public void setBeanNo(String beanNo) {
        this.beanNo = beanNo;
    }

    @Override
    public String toString() {
        return "InnerBean [beanName=" + beanName + ", beanNo=" + beanNo + "]";
    }

}