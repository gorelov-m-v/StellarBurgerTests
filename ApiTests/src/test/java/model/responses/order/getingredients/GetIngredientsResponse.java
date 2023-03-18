package model.responses.order.getingredients;

import java.util.List;

public class GetIngredientsResponse {
    private String success;
    private List<Data> data;

    public String getSuccess() {
        return success;
    }
    public List<Data> getData() {
        return data;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
}
