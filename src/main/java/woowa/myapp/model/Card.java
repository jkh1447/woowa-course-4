package woowa.myapp.model;

import java.io.Serializable;

public class Card implements Serializable {
    private String front;
    private String back;
    private boolean target;
    private String lastViewedDate;

    public Card(String front, String back) {
        this.front = front;
        this.back = back;
        this.target = true;
        this.lastViewedDate = "";
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

}
