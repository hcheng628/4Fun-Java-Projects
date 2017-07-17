package us.supercheng.safe1pass.entity;

import java.util.Vector;

/**
 * Created by hongyu on 7/16/17.
 */
public class Post {
    private Integer post_id;
    private Integer owner_id;
    private Vector<String> postFileFullPathList;

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Vector<String> getPostFileFullPathList() {
        return postFileFullPathList;
    }

    public void setPostFileFullPathList(Vector<String> postFileFullPathList) {
        this.postFileFullPathList = postFileFullPathList;
    }
}
