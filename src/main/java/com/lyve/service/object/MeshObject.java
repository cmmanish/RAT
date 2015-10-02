package com.lyve.service.object;

import java.util.List;

/**
 * Created by mmadhusoodan on 3/3/15.
 */
public class MeshObject {

    private static MeshObject instance;

    private List<AgentObject> agentObjectList;

    public static synchronized MeshObject getInstance() {

        if (instance == null) {
            instance = new MeshObject();
        }
        return instance;
    }
}
