package ui;

public class Response {
    private String textResponse;
    private int target;

    public Response(String textResponse, int target) {
        this.textResponse = textResponse;
        this.target = target;
    }

    public String getResponseText() {
        return textResponse;
    }

    public int getTarget() {
        return target;
    }

}
