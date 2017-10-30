
package csitmnr.newsproject2;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsPojo implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("intro_text")
    @Expose
    private String introText;
    @SerializedName("created_by_id")
    @Expose
    private Integer createdById;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public NewsPojo(){

    }

    public NewsPojo(String title,String introText,String detail,String createdAt,String updatedAt){
        this.title = title;
        this.introText = introText;
        this.detail = detail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public NewsPojo(Integer id, String title, String introText, Integer createdById, String featuredImage, String detail, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.introText = introText;
        this.createdById = createdById;
        this.featuredImage = featuredImage;
        this.detail = detail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
