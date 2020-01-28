package com.example.membercenter.data;

import com.example.membercenter.data.network.services.MemberService;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }


    public MemberService getMemberService() {
        return MemberService.getInstance();
    }

}
