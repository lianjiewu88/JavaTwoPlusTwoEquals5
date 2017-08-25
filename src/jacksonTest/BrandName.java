package jacksonTest;

public class BrandName {
// Jerry 2017-08-25 9:19PM: after I change id to id2, the deserialization from string
// to object can still work, which proves that the conversion is not done 
// based on ATTRIBUTE NAME
    private Integer id2; 
    private String content;
    public Integer getId() {
        return id2;
    }
    
    public BrandName(Integer id, String content){
    	this.id2 = id;
    	this.content = content;
    	
    }
// Exception in thread "main" com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "id" (class jacksonTest.BrandName), not marked as ignorable (2 known properties: , "pp", "content"])
        
//    public void setPP(Integer id) {
    public void setId(Integer id){
        this.id2 = id;
    }
    public String getcontent() {
        return content;
    }
    public void setcontent(String name) {
        this.content = name;
    }
    @Override
    public String toString() {
        return "BrandName [id=" + id2 + ", content=" + content + "]";
    }
}