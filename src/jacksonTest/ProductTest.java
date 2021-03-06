package jacksonTest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTest {

    private int productId;
    private String productName;
    private BrandName brandName;
    
    public ProductTest(int id, String Name, BrandName brandName){
    	this.productId = id;
    	this.productName = Name;
    	this.brandName = brandName;
    }

    @JsonProperty("id") // this should match the attribute in JSON file
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @JsonProperty("name")
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("brand")
    public BrandName getBrandName() {
        return brandName;
    }
    public void setBrandName(BrandName brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "ProductTest [productId=" + productId + ", productName=" + productName + ", brandName=" + brandName
                + "]";
    }



}
