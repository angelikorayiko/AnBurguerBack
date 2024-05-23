package objects;

public class OrderFD {

    private String Order_id;
    private String Store_id;
    private String Client_id;

    public OrderFD(String Order_id, String Store_id, String Client_id) {
        this.Order_id = Order_id;
        this.Store_id = Store_id;
        this.Client_id = Client_id;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(String Order_id) {
        this.Order_id = Order_id;
    }

    public String getStore_id() {
        return Store_id;
    }

    public void setStore_id(String Store_id) {
        this.Store_id = Store_id;
    }

    public String getClient_id() {
        return Client_id;
    }

    public void setClient_id(String Client_id) {
        this.Client_id = Client_id;
    }
}
