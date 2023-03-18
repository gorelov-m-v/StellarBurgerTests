package model.responses.order.getingredients;

import java.util.List;

public class GetIngredientsResponse {
    private boolean success;
    private List<Data> data;

    public boolean getSuccess() {
        return success;
    }
    public List<Data> getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
}
