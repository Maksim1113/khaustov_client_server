import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    public String msg;
    private Date date;

    public Message(String msg){
        this.msg = msg;
        this.date = new Date();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
