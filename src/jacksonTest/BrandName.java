package jacksonTest;

public class BrandName {

    private Integer id;
    private String content;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getcontent() {
        return content;
    }
    public void setcontent(String name) {
        this.content = name;
    }
    @Override
    public String toString() {
        return "BrandName [id=" + id + ", content=" + content + "]";
    }




}