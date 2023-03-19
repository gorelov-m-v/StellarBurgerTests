package model.responses.order.get;

import java.util.List;

public class Orders {

    private List<String> ingredients;
    private String _id;
    private String status;
    private int number;
    private String createdAt;
    private String updatedAt;

    public List<String> getIngredients() {
        return ingredients;
    }
    public String get_id() {
        return _id;
    }
    public String getStatus() {
        return status;
    }
    public int getNumber() {
        return number;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
