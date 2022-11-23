package ui;

public class Response {
    private String response;
    private int target;

    public Response(String response, int target) {
        this.response = response;
        this.target = target;
    }

    public String getResponseText() {
        return response;
    }

    public int getTarget() {
        return target;
    }

}
